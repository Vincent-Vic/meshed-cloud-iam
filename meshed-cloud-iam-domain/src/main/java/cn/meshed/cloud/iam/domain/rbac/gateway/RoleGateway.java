package cn.meshed.cloud.iam.domain.rbac.gateway;


import cn.meshed.cloud.core.IList;
import cn.meshed.cloud.gateway.BaseGateway;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.Role;
import cn.meshed.cloud.iam.rbac.query.RoleQry;

import java.util.List;
import java.util.Set;

/**
 * <h1>角色处理网关</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface RoleGateway extends BaseGateway<Role, Role, Long,Long, Boolean,Role>,
        IList<RoleQry, List<Role>> {

    /**
     * 授予角色权限
     *
     * @param roleId        被授权角色ID
     * @param permissionIds 授予的权限列表
     * @return 处理情况
     */
    Boolean grantPermission(Long roleId, Set<Long> permissionIds);

    /**
     * 根据角色集合获取权限集合
     *
     * @param roleIds 角色列表
     * @return 权限集合
     */
    Set<Permission> getPermissionSet(Set<Long> roleIds);


    /**
     * 根据角色集合获取角色集合
     *
     * @param roleIds 角色列表
     * @return 权限集合
     */
    Set<Role> getRoleSet(Set<Long> roleIds);



}
