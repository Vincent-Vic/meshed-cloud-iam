package cn.meshed.cloud.iam.rbac.executor;

import cn.meshed.cloud.iam.domain.rbac.ability.RoleAbility;
import cn.meshed.cloud.iam.rbac.command.RoleCmd;
import cn.meshed.cloud.iam.rbac.command.RoleGrantPermissionCmd;
import cn.meshed.cloud.iam.rbac.data.RoleDTO;
import cn.meshed.cloud.iam.rbac.data.RoleOptionDTO;
import cn.meshed.cloud.iam.rbac.executor.command.RoleCmdExe;
import cn.meshed.cloud.iam.rbac.executor.command.RoleDelExe;
import cn.meshed.cloud.iam.rbac.executor.command.RoleGrantPermissionCmdExe;
import cn.meshed.cloud.iam.rbac.executor.query.RoleByIdQryExe;
import cn.meshed.cloud.iam.rbac.executor.query.RoleBySelectQryExe;
import cn.meshed.cloud.iam.rbac.executor.query.RoleListQryExe;
import cn.meshed.cloud.iam.rbac.executor.query.RolePermissionByIdQryExe;
import cn.meshed.cloud.iam.rbac.query.RoleBySelectQry;
import cn.meshed.cloud.iam.rbac.query.RoleQry;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h1>角色操作能力</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RoleAbilityImpl implements RoleAbility {

    private final RoleCmdExe roleCmdExe;
    private final RoleDelExe roleDelExe;
    private final RoleListQryExe roleListQryExe;
    private final RoleByIdQryExe roleByIdQryExe;
    private final RoleBySelectQryExe roleBySelectQryExe;
    private final RoleGrantPermissionCmdExe roleGrantPermissionCmdExe;
    private final RolePermissionByIdQryExe rolePermissionByIdQryExe;

    /**
     * @param roleQry
     * @return
     */
    @Override
    public MultiResponse<RoleDTO> searchList(RoleQry roleQry) {
        return roleListQryExe.execute(roleQry);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 删除结果
     */
    @Override
    public Response delete(Long id) {
        return roleDelExe.execute(id);
    }

    /**
     * 保存
     *
     * @param roleCmd 保存对象
     * @return 操作结果
     */
    @Override
    public Response save(RoleCmd roleCmd) {
        return roleCmdExe.execute(roleCmd);
    }

    /**
     * 授予角色权限
     *
     * @param roleGrantPermissionCmd 被授权角色ID
     * @return 处理情况
     */
    @Override
    public Response grantPermission(RoleGrantPermissionCmd roleGrantPermissionCmd) {
        return roleGrantPermissionCmdExe.execute(roleGrantPermissionCmd);
    }

    /**
     * 角色权限列表
     *
     * @param roleId 角色编码
     * @return 权限编码列表
     */
    @Override
    public MultiResponse<Long> getPermissionIds(Long roleId) {
        return rolePermissionByIdQryExe.execute(roleId);
    }


    /**
     * 角色选择列表
     *
     * @param roleBySelectQry 角色选择参数
     * @return
     */
    @Override
    public MultiResponse<RoleOptionDTO> select(RoleBySelectQry roleBySelectQry) {
        return roleBySelectQryExe.execute(roleBySelectQry);
    }

    /**
     * @param roleId
     * @return
     */
    @Override
    public SingleResponse<RoleDTO> query(Long roleId) {
        return roleByIdQryExe.execute(roleId);
    }


}
