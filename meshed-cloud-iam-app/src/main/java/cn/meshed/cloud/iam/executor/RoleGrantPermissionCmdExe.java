package cn.meshed.cloud.iam.executor;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.AssertUtils;
import cn.meshed.base.utils.ResultUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleGrantPermissionCmd;
import cn.meshed.cloud.iam.domain.gateway.RoleGateway;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
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
        //校验参数
        AssertUtils.isTrue(!roleGrantPermissionCmd.verifySelf(),"未指定角色进行授权");

        Boolean grantPermission = roleGateway.grantPermission(roleGrantPermissionCmd.getRoleId(),
                roleGrantPermissionCmd.getPermissionIds());
        return ResultUtils.of(grantPermission,"保存失败");
    }
}
