package cn.meshed.cloud.iam.domain.gateway;

import cn.meshed.cloud.iam.domain.dto.AccountDTO;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface AccountGateway {

    /**
     * 根据登入ID获取账号消息  todo 解决敏感信息RPC调用
     * @param loginId 登入ID也就是登入名称
     * @return {@link AccountDTO}
     */
    AccountDTO getAccountByLoginId(String loginId);

}
