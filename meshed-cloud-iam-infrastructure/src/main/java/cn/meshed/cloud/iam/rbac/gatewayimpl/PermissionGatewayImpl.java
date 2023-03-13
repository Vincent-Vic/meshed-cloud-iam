package cn.meshed.cloud.iam.rbac.gatewayimpl;

import cn.meshed.cloud.context.SecurityContext;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.rbac.gatewayimpl.database.dataobject.PermissionDO;
import cn.meshed.cloud.iam.rbac.gatewayimpl.database.mapper.PermissionMapper;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
import cn.meshed.cloud.utils.CopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.meshed.cloud.constant.CommonConstant.DEFAULT_PARENT_NODE;

/**
 * <h1>权限处理网关</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class PermissionGatewayImpl implements PermissionGateway {

    private final PermissionMapper permissionMapper;


    /**
     * 删除权限
     * @param id
     * @return
     */
    @Override
    public Boolean delete(Long id) {
        if (id == null){
            return false;
        }
        return permissionMapper.deleteById(id) > 0;
    }
    /**
     * @param permissionQry
     * @return
     */
    @Override
    public List<Permission> searchList(PermissionQry permissionQry) {
        LambdaQueryWrapper<PermissionDO> lqw = new LambdaQueryWrapper<>();
        Long parentId = permissionQry.getParentId();


        String keyword = permissionQry.getKeyword();
        if (StringUtils.isNotBlank(keyword)){
            //如果查询存在就就不考虑层级
            parentId = null;
            lqw.or().like(PermissionDO::getUri,keyword);
            lqw.or().like(PermissionDO::getName,keyword);
            lqw.or().like(PermissionDO::getEnname,keyword);
            lqw.or().like(PermissionDO::getDescription,keyword);
        } else if (parentId == null || parentId < 0){
            parentId = DEFAULT_PARENT_NODE;
        }
        lqw.eq(parentId != null, PermissionDO::getParentId, parentId);
        lqw.eq(permissionQry.getStatus()!= null, PermissionDO::getStatus, permissionQry.getStatus());
        List<PermissionDO> list = permissionMapper.selectList(lqw);
        return CopyUtils.copyListProperties(list,Permission::new);
    }

    /**
     * @param permission
     * @return
     */
    @Override
    public Boolean save(Permission permission) {
        PermissionDO permissionDO = CopyUtils.copy(permission, PermissionDO.class);
        permissionDO.setCreateBy(SecurityContext.getOperatorString());
        permissionDO.setUpdateBy(SecurityContext.getOperatorString());
        permissionDO.setCreateTime(LocalDateTime.now());
        permissionDO.setUpdateTime(LocalDateTime.now());
        return permissionMapper.insert(permissionDO) > 0;
    }

    /**
     * @param permission
     * @return
     */
    @Override
    public Boolean update(Permission permission) {
        PermissionDO permissionDO = CopyUtils.copy(permission, PermissionDO.class);
        permissionDO.setUpdateBy(SecurityContext.getOperatorString());
        permissionDO.setUpdateTime(LocalDateTime.now());
        return permissionMapper.updateById(permissionDO) > 0;
    }

    /**
     * 根据角色集合获取权限集合
     *
     * @param permissionIds
     * @return
     */
    @Override
    public Set<Permission> getPermissionSet(Set<Long> permissionIds) {
        List<PermissionDO> permissions = permissionMapper.selectBatchIds(permissionIds);
        if (CollectionUtils.isEmpty(permissions)){
            return Sets.newHashSet();
        }
        return permissions.stream().map(this::buildPermissionVO).collect(Collectors.toSet());

    }


    private Permission buildPermissionVO(PermissionDO permissionDO) {
        return CopyUtils.copy(permissionDO, Permission.class);
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public Permission query(Long aLong) {
        return null;
    }
}
