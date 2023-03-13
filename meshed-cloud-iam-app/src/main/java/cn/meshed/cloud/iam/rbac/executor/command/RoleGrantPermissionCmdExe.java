package cn.meshed.cloud.iam.rbac.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.iam.rbac.command.RoleGrantPermissionCmd;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>角色授权处理器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RoleGrantPermissionCmdExe implements CommandExecute<RoleGrantPermissionCmd, Response> {

    private final RoleGateway roleGateway;

    /**
     * @param roleGrantPermissionCmd
     * @return
     */
    @Override
    public Response execute(RoleGrantPermissionCmd roleGrantPermissionCmd) {

        Boolean grantPermission = roleGateway.grantPermission(roleGrantPermissionCmd.getRoleId(),
                roleGrantPermissionCmd.getPermissionIds());
        return ResultUtils.of(grantPermission,"保存失败");
    }
}
