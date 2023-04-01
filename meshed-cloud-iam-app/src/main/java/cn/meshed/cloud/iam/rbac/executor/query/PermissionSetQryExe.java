package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.iam.rbac.query.PermissionSetQry;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PermissionSetQryExe implements QueryExecute<PermissionSetQry, SingleResponse<Set<PermissionDTO>>> {

    private final PermissionGateway permissionGateway;

    /**
     * @param permissionSetQry
     * @return
     */
    @Override
    public SingleResponse<Set<PermissionDTO>> execute(PermissionSetQry permissionSetQry) {
        Set<Permission> permissionSet = permissionGateway.getPermissionSet(permissionSetQry.getRoleIds());
        return ResultUtils.copySet(permissionSet, PermissionDTO::new);
    }
}
