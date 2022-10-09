package cn.meshed.cloud.iam.executor;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.AssertUtils;
import cn.meshed.base.utils.ResultUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
import com.alibaba.cola.dto.Response;
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
public class AccountGrantRoleCmdExe implements CommandExecute<AccountGrantRoleCmd, Response> {

    private final AccountGateway accountGateway;

    /**
     * @param accountGrantRoleCmd
     * @return
     */
    @Override
    public Response execute(AccountGrantRoleCmd accountGrantRoleCmd) {
        AssertUtils.isTrue(!accountGrantRoleCmd.verifySelf(),"授权账号不存在");
        Boolean grantRole = accountGateway.grantRole(accountGrantRoleCmd.getAccountId(),
                accountGrantRoleCmd.getRoleIds());
        return ResultUtils.of(grantRole,"授权角色失败");
    }
}
