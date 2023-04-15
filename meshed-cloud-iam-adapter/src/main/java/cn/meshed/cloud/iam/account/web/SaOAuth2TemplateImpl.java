package cn.meshed.cloud.iam.account.web;

import cn.dev33.satoken.oauth2.logic.SaOAuth2Template;
import cn.dev33.satoken.oauth2.model.SaClientModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.ability.SystemAbility;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Sa-Token OAuth2.0 整合实现
 *
 * @author kong
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SaOAuth2TemplateImpl extends SaOAuth2Template {

    private final SystemAbility systemAbility;

    // 根据 id 获取 Client 信息
    @Override
    public SaClientModel getClientModel(String clientId) {

        SingleResponse<System> response = systemAbility.getSystemByClientId(clientId);
        System system = response.getData();
        if (!response.isSuccess() || system == null) {
            return null;
        }
        return new SaClientModel()
                .setClientId(system.getClientId())
                .setClientSecret(system.getClientSecret())
                .setAllowUrl(system.getAllowUrl())
                .setContractScope(system.getScope())
                .setIsAutoMode(true);
    }

    // 根据ClientId 和 LoginId 获取openid
    @Override
    public String getOpenid(String clientId, Object loginId) {
        // 此为模拟数据，真实环境需要从数据库查询
        return "gr_SwoIN0MC1ewxHX_vfCW3BothWDZMMtx__";
    }

    // 重写 Access-Token 生成策略：复用登录会话的Token
    @Override
    public String randomAccessToken(String clientId, Object loginId, String scope) {
        return StpUtil.createLoginSession(loginId);
    }

}
