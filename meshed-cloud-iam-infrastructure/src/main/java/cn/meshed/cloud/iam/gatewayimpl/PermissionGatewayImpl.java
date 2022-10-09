package cn.meshed.cloud.iam.gatewayimpl;

import cn.meshed.base.utils.CopyUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.PermissionCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.PermissionQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.gateway.PermissionGateway;
import cn.meshed.cloud.iam.gatewayimpl.database.dataobject.Permission;
import cn.meshed.cloud.iam.gatewayimpl.database.mapper.PermissionMapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public PermissionVO searchPageList(PermissionQry permissionQry) {
        return null;
    }

    /**
     * @param permissionCmd
     * @return
     */
    @Override
    public Boolean save(PermissionCmd permissionCmd) {
        Permission permission = CopyUtils.copy(permissionCmd, Permission.class);
        permission.setCreated(LocalDateTime.now());
        permission.setUpdated(LocalDateTime.now());
        return permissionMapper.insert(permission) > 0;
    }

    /**
     * @param permissionCmd
     * @return
     */
    @Override
    public Boolean update(PermissionCmd permissionCmd) {
        Permission permission = CopyUtils.copy(permissionCmd, Permission.class);
        return permissionMapper.updateById(permission) > 0;
    }

    /**
     * 根据角色集合获取权限集合
     *
     * @param permissionIds
     * @return
     */
    @Override
    public Set<PermissionVO> getPermissionSet(Set<Long> permissionIds) {
        List<Permission> permissions = permissionMapper.selectBatchIds(permissionIds);
        if (CollectionUtils.isEmpty(permissions)){
            return Sets.newHashSet();
        }
        return permissions.stream().map(this::buildPermissionVO).collect(Collectors.toSet());

    }

    private PermissionVO buildPermissionVO(Permission permission) {
        return CopyUtils.copy(permission, PermissionVO.class);
    }
}
