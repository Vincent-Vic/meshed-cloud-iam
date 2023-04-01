package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import com.alibaba.cola.dto.MultiResponse;
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
public class RolePermissionByIdQryExe implements QueryExecute<Long, MultiResponse<Long>> {

    private final RoleGateway roleGateway;

    /**
     * @param roleId
     * @return
     */
    @Override
    public MultiResponse<Long> execute(Long roleId) {
        Set<Permission> permissionSet = roleGateway.getPermissionSet(Collections.singleton(roleId));
        if (CollectionUtils.isEmpty(permissionSet)) {
            return MultiResponse.buildSuccess();
        }
        Set<Long> permissionIdSet = permissionSet.stream().map(Permission::getId).collect(Collectors.toSet());
        return MultiResponse.of(permissionIdSet);
    }
}
