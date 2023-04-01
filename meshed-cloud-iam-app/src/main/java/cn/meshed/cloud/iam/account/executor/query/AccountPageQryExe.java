package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountPageQry;
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
public class AccountPageQryExe implements QueryExecute<AccountPageQry, PageResponse<AccountDTO>> {

    private final AccountGateway accountGateway;

    /**
     * 分页列表
     *
     * @param pageQry 分页参数
     * @return {@link PageResponse<AccountDTO>}
     */
    @Override
    public PageResponse<AccountDTO> execute(AccountPageQry pageQry) {
        PageResponse<Account> response = accountGateway.searchPageList(pageQry);
        return ResultUtils.copyPage(response, AccountDTO::new);
    }
}
