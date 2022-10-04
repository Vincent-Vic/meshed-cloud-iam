package cn.meshed.cloud.iam.executor.query;


import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.CopyUtils;
import cn.meshed.cloud.iam.domain.dto.AccountDTO;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
import cn.meshed.cloud.iam.dto.AccountByLoginIdRequest;
import cn.meshed.cloud.iam.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class AccountByLoginIdQryExe implements CommandExecute<AccountByLoginIdRequest, AccountResponse> {

    private final AccountGateway accountGateway;

    /**
     * <h2>查询执行器</h2>
     * @param accountByLoginIdRequest 请求对象
     * @return {@link AccountResponse}
     */
    @Override
    public AccountResponse execute(AccountByLoginIdRequest accountByLoginIdRequest) {
        String loginId = accountByLoginIdRequest.getLoginId();
        if (StringUtils.isBlank(loginId)){
            return null;
        }
        AccountDTO account = accountGateway.getAccountByLoginId(loginId);
        if (account == null){
            return null;
        }
        return CopyUtils.copy(account,AccountResponse.class);
    }
}
