package cn.meshed.cloud.iam.account.web;

import cn.dev33.satoken.oauth2.exception.SaOAuth2Exception;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Util;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.meshed.cloud.exception.security.AuthenticationException;
import cn.meshed.cloud.iam.account.LoginAdapter;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.query.UserByOneQry;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.ability.AccountAbility;
import cn.meshed.cloud.iam.domain.account.ability.SystemAbility;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Controller
public class LoginWebAdapter implements LoginAdapter {

    private final SystemAbility systemAbility;
    private final AccountAbility accountAbility;

    @Value("${oauth2.host}")
    private String OAUTH2_HOST;

    private final String OAUTH2_AUTHORIZE = "%s/oauth2/authorize?response_type=%s&client_id=%s&redirect_uri=%s&scope=%s";
    private final String ERROR_REDIRECT = "%s?error=%s";

    private final List<String> TYPES = Arrays.asList("code", "token");

    /**
     * 执行登入 (重定向模式)
     *
     * @param type     登入模式
     * @param key      系统标识
     * @param redirect 重定向
     * @param response 响应
     */
    @Override
    public String oauth2Login(String type, String key, String redirect, HttpServletResponse response) {
        if (!TYPES.contains(type)) {
            return "error";
        }

        if (StringUtils.isNotBlank(redirect)) {
            SingleResponse<System> systemResponse = systemAbility.getSystemByKey(key.toUpperCase());
            try {
                if (systemResponse.isSuccess()) {
                    System system = systemResponse.getData();
                    String loginUrl = String.format(OAUTH2_AUTHORIZE, OAUTH2_HOST, type, system.getClientId(),
                            redirect, system.getScope());
                    response.sendRedirect(loginUrl);
                } else {
                    String errorUrl = String.format(ERROR_REDIRECT, redirect, "SYSTEM_NOT_FOUND");
                    response.sendRedirect(errorUrl);
                }
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "error";
    }

    /**
     * 获取当前登入用户信息
     *
     * @return {@link SingleResponse<UserDTO>}
     */
    @Override
    public SingleResponse<UserDTO> userInfo() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (!tokenInfo.isLogin) {
            throw new AuthenticationException("未登入");
        }
        // 校验 Access-Token 是否具有权限: userinfo
        try {
            SaOAuth2Util.checkScope(tokenInfo.tokenValue, "userinfo");
        } catch (SaOAuth2Exception exception) {
            throw new AuthenticationException(exception.getMessage());
        }
        UserByOneQry userByOneQry = new UserByOneQry();
        userByOneQry.setHasGrantedAuthority(true);
        userByOneQry.setId(Long.parseLong((String) tokenInfo.getLoginId()));
        return accountAbility.queryUserById(userByOneQry);
    }

    /**
     * @return
     */
    @Override
    public Response logout() {
        StpUtil.logout();
        return ResultUtils.ok();
    }

}
