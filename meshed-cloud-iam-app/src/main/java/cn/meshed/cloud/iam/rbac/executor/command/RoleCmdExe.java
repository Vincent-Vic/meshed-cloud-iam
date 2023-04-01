package cn.meshed.cloud.iam.rbac.executor.command;

import cn.meshed.cloud.constant.Status;
import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.Role;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.iam.rbac.command.RoleCmd;
import cn.meshed.cloud.utils.CopyUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
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
        Role role = CopyUtils.copy(roleCmd, Role.class);
        Boolean op = false;
        if (role.getId() != null) {
            op = roleGateway.update(role);
        } else {
            roleCmd.setStatus(Status.VALID);
            op = roleGateway.save(role);
        }
        return ResultUtils.of(op, "保存失败");
    }
}
