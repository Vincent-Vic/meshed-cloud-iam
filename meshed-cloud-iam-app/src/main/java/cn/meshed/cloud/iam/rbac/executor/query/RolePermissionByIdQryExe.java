package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.iam.rbac.query.RolePermissionByIdQry;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RolePermissionByIdQryExe implements CommandExecute<RolePermissionByIdQry, SingleResponse<Set<Long>>> {

    private final RoleGateway roleGateway;

    /**
     * @param rolePermissionByIdQry
     * @return
     */
    @Override
    public SingleResponse<Set<Long>> execute(RolePermissionByIdQry rolePermissionByIdQry) {
        Set<Permission> permissionSet = roleGateway.getPermissionSet(Collections.singleton(rolePermissionByIdQry.getId()));
        if (CollectionUtils.isEmpty(permissionSet)){
            return ResultUtils.ok();
        }
        Set<Long> permissionIdSet = permissionSet.stream().map(Permission::getId).collect(Collectors.toSet());
        return ResultUtils.of(permissionIdSet);
    }
}
