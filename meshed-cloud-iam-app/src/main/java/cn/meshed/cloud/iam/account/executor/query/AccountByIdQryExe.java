package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AccountByIdQryExe implements QueryExecute<Long, SingleResponse<AccountDTO>> {

    private final AccountGateway accountGateway;

    /**
     * 查询账号ID列表
     *
     * @param accountId 账号ID
     * @return {@link SingleResponse<AccountDTO>}
     */
    @Override
    public SingleResponse<AccountDTO> execute(Long accountId) {
        Account account = accountGateway.query(accountId);
        //消除密码
        account.setSecretKey(null);
        return ResultUtils.copy(account, AccountDTO.class);
    }
}
