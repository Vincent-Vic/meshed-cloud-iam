package cn.meshed.cloud.iam.account.executor.query;


import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountByLoginIdQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class AccountByLoginIdQryExe implements QueryExecute<AccountByLoginIdQry, SingleResponse<Account>> {

    private final AccountGateway accountGateway;

    /**
     * <h2>查询执行器</h2>
     *
     * @param accountByLoginIdQry 请求对象
     * @return {@link AccountDTO}
     */
    @Override
    public SingleResponse<Account> execute(AccountByLoginIdQry accountByLoginIdQry) {
        String loginId = accountByLoginIdQry.getLoginId();
        AssertUtils.isTrue(StringUtils.isNotBlank(loginId), "登入账号不能为空");
        Account account = accountGateway.getAccountByLoginId(loginId);
        if (account == null) {
            return ResultUtils.fail("404", "账号不存在");
        }

        return ResultUtils.of(account);
    }

}
