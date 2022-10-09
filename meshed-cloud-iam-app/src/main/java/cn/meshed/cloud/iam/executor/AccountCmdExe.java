package cn.meshed.cloud.iam.executor;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.AssertUtils;
import cn.meshed.base.utils.ResultUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountCmd;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
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
public class AccountCmdExe implements CommandExecute<AccountCmd, Response> {

    private final AccountGateway accountGateway;

    /**
     * @param accountCmd
     * @return
     */
    @Override
    public Response execute(AccountCmd accountCmd) {
        //参数校验
        accountCmd.verifySelf();

        Boolean op = false;
        if (accountCmd.getId() != null) {
            op = accountGateway.update(accountCmd);
        } else {
            op = accountGateway.save(accountCmd);
        }
        return ResultUtils.of(op,"保存失败");
    }
}
