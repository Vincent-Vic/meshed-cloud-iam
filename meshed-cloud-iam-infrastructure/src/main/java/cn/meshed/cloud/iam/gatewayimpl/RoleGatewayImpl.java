package cn.meshed.cloud.iam.gatewayimpl;

import cn.meshed.base.utils.CopyUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.RoleQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.dto.vo.RoleVO;
import cn.meshed.cloud.iam.domain.gateway.PermissionGateway;
import cn.meshed.cloud.iam.domain.gateway.RoleGateway;
import cn.meshed.cloud.iam.gatewayimpl.database.dataobject.Role;
import cn.meshed.cloud.iam.gatewayimpl.database.dataobject.RolePermission;
import cn.meshed.cloud.iam.gatewayimpl.database.mapper.RoleMapper;
import cn.meshed.cloud.iam.gatewayimpl.database.mapper.RolePermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>角色处理网关</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class RoleGatewayImpl implements RoleGateway {

    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;
    private final PermissionGateway permissionGateway;

    /**
     * @param id role id
     * @return {@link Boolean}
     */
    @Override
    public Boolean delete(Long id) {
        if (id == null){
            return false;
        }
        return roleMapper.deleteById(id) > 0;
    }

    /**
     * @param roleQry
     * @return
     */
    @Override
    public RoleVO searchPageList(RoleQry roleQry) {
        return null;
    }

    /**
     * @param roleCmd
     * @return
     */
    @Override
    public Boolean save(RoleCmd roleCmd) {
        Role role = CopyUtils.copy(roleCmd, Role.class);
        role.setCreated(LocalDateTime.now());
        role.setUpdated(LocalDateTime.now());
        return roleMapper.insert(role) > 0;
    }

    /**
     * @param roleCmd
     * @return
     */
    @Override
    public Boolean update(RoleCmd roleCmd) {
        Role role = CopyUtils.copy(roleCmd, Role.class);
        role.setUpdated(LocalDateTime.now());
        return roleMapper.updateById(role) > 0;
    }

    /**
     * 授予角色权限
     *
     * @param roleId        被授权角色ID
     * @param permissionIds 授予的权限列表
     * @return 处理情况
     */
    @Override
    public Boolean grantPermission(Long roleId, Set<Long> permissionIds) {
        //先删除旧的
        LambdaQueryWrapper<RolePermission> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RolePermission::getRoleId,roleId);
        rolePermissionMapper.delete(lqw);
        //如果不存在，说明删除权限
        if (CollectionUtils.isEmpty(permissionIds)){
            return true;
        }

        //构建新的对象
        List<RolePermission> rolePermissions = permissionIds.stream()
                .map(permissionId -> buildRolePermission(roleId,permissionId))
                .collect(Collectors.toList());

        //批量添加
        return rolePermissionMapper.insertBatch(rolePermissions) > 0;
    }

    /**
     * 根据角色集合获取权限集合
     *
     * @param roleIds
     * @return
     */
    @Override
    public Set<PermissionVO> getPermissionSet(Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)){
            return Sets.newHashSet();
        }
        LambdaQueryWrapper<RolePermission> lqw = new LambdaQueryWrapper<>();
        lqw.in(RolePermission::getRoleId,roleIds);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(lqw);
        if (CollectionUtils.isEmpty(rolePermissions)){
            return Sets.newHashSet();
        }
        Set<Long> permissions = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
        return permissionGateway.getPermissionSet(permissions);
    }

    /**
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    private  RolePermission buildRolePermission(Long roleId,Long permissionId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        return rolePermission;
    }
}
