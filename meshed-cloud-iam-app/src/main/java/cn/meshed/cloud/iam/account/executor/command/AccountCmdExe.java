package cn.meshed.cloud.iam.account.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.command.AccountAddCmd;
import cn.meshed.cloud.iam.account.command.PasswordBuildCmd;
import cn.meshed.cloud.iam.account.enums.AccountStatusEnum;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.ability.EncryptionService;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.CopyUtils;
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
public class AccountCmdExe implements CommandExecute<AccountAddCmd, Response> {

    private final AccountGateway accountGateway;
    private final EncryptionService encryptionService;

    /**
     * @param accountAddCmd
     * @return
     */
    @Override
    public Response execute(AccountAddCmd accountAddCmd) {

        Account account = CopyUtils.copy(accountAddCmd, Account.class);
        Boolean op = false;
        if (account.getId() != null) {
            accountAddCmd.setSecretKey(null);
            op = accountGateway.update(account);
        } else {
            PasswordBuildCmd passwordBuildCmd = new PasswordBuildCmd();
            passwordBuildCmd.setUnencrypted(accountAddCmd.getSecretKey());
            accountAddCmd.setSecretKey(encryptionService.encode(accountAddCmd.getSecretKey()));
            account.setStatus(AccountStatusEnum.VALID);
            op = accountGateway.save(account);
        }
        return ResultUtils.of(op, "保存失败");
    }
}
