package cn.meshed.cloud.iam.account.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.command.AccountLockCmd;
import cn.meshed.cloud.iam.account.enums.AccountStatusEnum;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>账号添加</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AccountLockCmdExe implements CommandExecute<AccountLockCmd, Response> {

    private final AccountGateway accountGateway;

    /**
     * @param accountLockCmd
     * @return
     */
    @Override
    public Response execute(AccountLockCmd accountLockCmd) {
        //参数校验
        Account account = accountGateway.query(accountLockCmd.getId());
        if (accountLockCmd.isOperate()) {
            account.setStatus(AccountStatusEnum.LOCK);
        } else {
            account.setStatus(AccountStatusEnum.VALID);
        }

        return ResultUtils.of(accountGateway.update(account));
    }
}
