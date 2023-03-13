package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.PageResponse;
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
public class AccountListQryExe implements CommandExecute<AccountQry, PageResponse<AccountDTO>> {

    private final AccountGateway accountGateway;

    /**
     * @param accountQry
     * @return
     */
    @Override
    public PageResponse<AccountDTO> execute(AccountQry accountQry) {
        PageResponse<Account> response = accountGateway.searchPageList(accountQry);
        return ResultUtils.copyPage(response,AccountDTO::new);
    }
}
