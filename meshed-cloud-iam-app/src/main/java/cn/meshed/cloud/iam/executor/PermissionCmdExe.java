package cn.meshed.cloud.iam.executor;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.AssertUtils;
import cn.meshed.base.utils.ResultUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.PermissionCmd;
import cn.meshed.cloud.iam.domain.gateway.PermissionGateway;
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
public class PermissionCmdExe implements CommandExecute<PermissionCmd, Response> {

    private final PermissionGateway permissionGateway;

    /**
     * @param permissionCmd
     * @return
     */
    @Override
    public Response execute(PermissionCmd permissionCmd) {
        //参数校验
        AssertUtils.isTrue(!permissionCmd.verifySelf(),"参数缺失");

        Boolean op = false;
        if (permissionCmd.getId() != null) {
            op = permissionGateway.update(permissionCmd);
        } else {
            op = permissionGateway.save(permissionCmd);
        }
        return ResultUtils.of(op,"保存失败");
    }
}
