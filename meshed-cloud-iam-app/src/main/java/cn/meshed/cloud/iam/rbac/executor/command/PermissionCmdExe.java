package cn.meshed.cloud.iam.rbac.executor.command;

import cn.meshed.cloud.constant.Status;
import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.rbac.command.PermissionCmd;
import cn.meshed.cloud.utils.CopyUtils;
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
public class PermissionCmdExe implements CommandExecute<PermissionCmd, Response> {

    private final PermissionGateway permissionGateway;

    /**
     * @param permissionCmd
     * @return
     */
    @Override
    public Response execute(PermissionCmd permissionCmd) {
        Permission permission = CopyUtils.copy(permissionCmd, Permission.class);
        Boolean op = false;
        if (permission.getId() != null) {
            permissionCmd.setStatus(null);
            op = permissionGateway.update(permission);
        } else {
            permissionCmd.setStatus(Status.VALID);
            op = permissionGateway.save(permission);
        }
        return ResultUtils.of(op, "保存失败");
    }
}
