package cn.meshed.cloud.iam.account.web;

import cn.meshed.cloud.iam.account.LoginAdapter;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.ability.SystemAbility;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    private final String OAUTH2_AUTHORIZE = "http://localhost:7989/iam/oauth2/authorize?response_type=code&client_id=%s&redirect_uri=%s&scope=%s";
    private final String ERROR_REDIRECT = "%s?error=%s";

    /**
     * 执行登入 (重定向模式)
     *
     * @param key      系统标识
     * @param redirect 重定向
     * @param request  请求
     * @param response 响应
     */
    @Override
    public String codeLogin(String key, String redirect, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(redirect)) {
            SingleResponse<System> systemResponse = systemAbility.getSystemByKey(key);
            try {
                if (systemResponse.isSuccess()) {
                    System system = systemResponse.getData();
                    String loginUrl = String.format(OAUTH2_AUTHORIZE, system.getClientId(), redirect, system.getScope());
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

}
