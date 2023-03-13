package cn.meshed.cloud.iam.domain.rbac.gateway;


import cn.meshed.cloud.core.IList;
import cn.meshed.cloud.gateway.BaseGateway;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;

import java.util.List;
import java.util.Set;

/**
 * <h1>权限处理网关</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface PermissionGateway extends BaseGateway<Permission, Permission, Long,Long, Boolean,Permission>,
        IList<PermissionQry, List<Permission>> {

    /**
     * 根据角色集合获取权限集合
     *
     * @param permissionIds 权限列表
     * @return 权限集合
     */
    Set<Permission> getPermissionSet(Set<Long> permissionIds);

}
