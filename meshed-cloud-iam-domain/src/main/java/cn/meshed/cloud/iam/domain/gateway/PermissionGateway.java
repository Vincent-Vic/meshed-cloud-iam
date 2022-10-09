package cn.meshed.cloud.iam.domain.gateway;


import cn.meshed.base.standard.ISingle;
import cn.meshed.cloud.iam.domain.dto.cmd.PermissionCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.PermissionQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;

import java.util.Set;

/**
 * <h1>权限处理网关</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface PermissionGateway extends ISingle<PermissionQry, PermissionVO, PermissionCmd, Long, Boolean> {

    /**
     * 根据角色集合获取权限集合
     *
     * @param permissionIds 权限列表
     * @return 权限集合
     */
    Set<PermissionVO> getPermissionSet(Set<Long> permissionIds);
}
