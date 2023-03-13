package cn.meshed.cloud.iam.rbac.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>权限删除处理器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PermissionDelExe implements CommandExecute<Long, Response> {

    private final PermissionGateway permissionGateway;

    /**
     * @param id 权限ID
     * @return
     */
    @Override
    public Response execute(Long id) {
        //参数校验
        AssertUtils.isTrue(id != null,"参数缺失");
        return ResultUtils.of(permissionGateway.delete(id));
    }
}
