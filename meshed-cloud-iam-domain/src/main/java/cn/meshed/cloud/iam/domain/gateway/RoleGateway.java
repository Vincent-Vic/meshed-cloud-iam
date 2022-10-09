package cn.meshed.cloud.iam.domain.gateway;


import cn.meshed.base.standard.ISingle;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.RoleQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.dto.vo.RoleVO;

import java.util.Set;

/**
 * <h1>角色处理网关</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface RoleGateway extends ISingle<RoleQry, RoleVO, RoleCmd, Long, Boolean> {

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
    Set<PermissionVO> getPermissionSet(Set<Long> roleIds);
}
