package cn.meshed.cloud.iam.executor;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.AssertUtils;
import cn.meshed.base.utils.ResultUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleCmd;
import cn.meshed.cloud.iam.domain.gateway.RoleGateway;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>角色新增/更新处理器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RoleCmdExe implements CommandExecute<RoleCmd, Response> {

    private final RoleGateway roleGateway;

    /**
     * @param roleCmd
     * @return
     */
    @Override
    public Response execute(RoleCmd roleCmd) {
        //参数校验
        AssertUtils.isTrue(!roleCmd.verifySelf(),"参数缺失");

        Boolean op = false;
        if (roleCmd.getId() != null) {
            op = roleGateway.update(roleCmd);
        } else {
            op = roleGateway.save(roleCmd);
        }
        return ResultUtils.of(op,"保存失败");
    }
}
