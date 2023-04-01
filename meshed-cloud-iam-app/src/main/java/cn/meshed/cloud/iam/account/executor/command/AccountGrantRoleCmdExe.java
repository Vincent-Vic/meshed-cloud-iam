package cn.meshed.cloud.iam.account.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.command.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
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
public class AccountGrantRoleCmdExe implements CommandExecute<AccountGrantRoleCmd, Response> {

    private final AccountGateway accountGateway;

    /**
     * @param accountGrantRoleCmd
     * @return
     */
    @Override
    public Response execute(AccountGrantRoleCmd accountGrantRoleCmd) {
        Boolean grantRole = accountGateway.grantRole(accountGrantRoleCmd.getAccountId(),
                accountGrantRoleCmd.getRoleIds());
        return ResultUtils.of(grantRole, "授权角色失败");
    }
}
