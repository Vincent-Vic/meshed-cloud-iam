package cn.meshed.cloud.iam.rbac.gatewayimpl;

import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.Role;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.iam.rbac.gatewayimpl.database.dataobject.RoleDO;
import cn.meshed.cloud.iam.rbac.gatewayimpl.database.dataobject.RolePermissionDO;
import cn.meshed.cloud.iam.rbac.gatewayimpl.database.mapper.RoleMapper;
import cn.meshed.cloud.iam.rbac.gatewayimpl.database.mapper.RolePermissionMapper;
import cn.meshed.cloud.iam.rbac.query.RoleQry;
import cn.meshed.cloud.utils.CopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

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
    public List<Role> searchList(RoleQry roleQry) {
        LambdaQueryWrapper<RoleDO> lqw = new LambdaQueryWrapper<>();
        String keyword = roleQry.getKeyword();
        if (StringUtils.isNotBlank(keyword)){
            lqw.or().like(RoleDO::getAccess, keyword);
            lqw.or().like(RoleDO::getName, keyword);
            lqw.or().like(RoleDO::getDescription, keyword);
        }
        lqw.eq(roleQry.getStatus() != null, RoleDO::getStatus,roleQry.getStatus());
        return CopyUtils.copyListProperties(roleMapper.selectList(lqw),Role.class);
    }

    /**
     * @param role
     * @return
     */
    @Override
    public Boolean save(Role role) {
        RoleDO roleDO = CopyUtils.copy(role, RoleDO.class);
        return roleMapper.insert(roleDO) > 0;
    }

    /**
     * @param role
     * @return
     */
    @Override
    public Boolean update(Role role) {
        RoleDO roleDO = CopyUtils.copy(role, RoleDO.class);
        return roleMapper.updateById(roleDO) > 0;
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
        LambdaQueryWrapper<RolePermissionDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RolePermissionDO::getRoleId,roleId);
        rolePermissionMapper.delete(lqw);
        //如果不存在，说明删除权限
        if (CollectionUtils.isEmpty(permissionIds)){
            return true;
        }

        //构建新的对象
        List<RolePermissionDO> rolePermissions = permissionIds.stream()
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
    public Set<Permission> getPermissionSet(Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)){
            return Sets.newHashSet();
        }
        LambdaQueryWrapper<RolePermissionDO> lqw = new LambdaQueryWrapper<>();
        lqw.in(RolePermissionDO::getRoleId,roleIds);
        List<RolePermissionDO> rolePermissions = rolePermissionMapper.selectList(lqw);
        if (CollectionUtils.isEmpty(rolePermissions)){
            return Sets.newHashSet();
        }
        Set<Long> permissions = rolePermissions.stream().map(RolePermissionDO::getPermissionId).collect(Collectors.toSet());
        return permissionGateway.getPermissionSet(permissions);
    }

    /**
     * 根据角色集合获取角色集合
     *
     * @param roleIds 角色列表
     * @return 权限集合
     */
    @Override
    public Set<Role> getRoleSet(Set<Long> roleIds) {
        return null;
    }

    /**
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    private RolePermissionDO buildRolePermission(Long roleId, Long permissionId) {
        RolePermissionDO rolePermissionDO = new RolePermissionDO();
        rolePermissionDO.setRoleId(roleId);
        rolePermissionDO.setPermissionId(permissionId);
        return rolePermissionDO;
    }

    /**
     * 查询角色
     *
     * @param roleId 角色ID
     * @return {@link Role}
     */
    @Override
    public Role query(Long roleId) {
        return CopyUtils.copy(roleMapper.selectById(roleId), Role.class);
    }

}
