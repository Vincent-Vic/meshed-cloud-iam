package cn.meshed.cloud.iam.rbac.executor;

import cn.meshed.cloud.iam.domain.rbac.ability.PermissionAbility;
import cn.meshed.cloud.iam.rbac.command.PermissionCmd;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.iam.rbac.data.PermissionOptionDTO;
import cn.meshed.cloud.iam.rbac.executor.command.PermissionCmdExe;
import cn.meshed.cloud.iam.rbac.executor.command.PermissionDelExe;
import cn.meshed.cloud.iam.rbac.executor.query.PermissionByIdQryExe;
import cn.meshed.cloud.iam.rbac.executor.query.PermissionBySelectQryExe;
import cn.meshed.cloud.iam.rbac.executor.query.PermissionListQryExe;
import cn.meshed.cloud.iam.rbac.query.PermissionBySelectQry;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h1>权限操作能力</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PermissionAbilityImpl implements PermissionAbility {

    private final PermissionCmdExe permissionCmdExe;
    private final PermissionDelExe permissionDelExe;
    private final PermissionBySelectQryExe permissionBySelectQryExe;
    private final PermissionListQryExe permissionListQryExe;
    private final PermissionByIdQryExe permissionByIdQryExe;


    /**
     * 删除
     *
     * @param id id
     * @return 操作结果
     */
    @Override
    public Response delete(Long id) {
        return permissionDelExe.execute(id);
    }

    /**
     * 保存
     *
     * @param permissionCmd 保存对象
     * @return 操作结果
     */
    @Override
    public Response save(PermissionCmd permissionCmd) {
        return permissionCmdExe.execute(permissionCmd);
    }

    /**
     * @param permissionQry
     * @return
     */
    @Override
    public MultiResponse<PermissionDTO> searchList(PermissionQry permissionQry) {
        return permissionListQryExe.execute(permissionQry);
    }

    /**
     * 选择列表
     *
     * @param permissionBySelectQry 查询
     * @return
     */
    @Override
    public MultiResponse<PermissionOptionDTO> select(PermissionBySelectQry permissionBySelectQry) {
        return permissionBySelectQryExe.execute(permissionBySelectQry);
    }

    /**
     * 查询
     *
     * @param permissionId 权限ID
     * @return {@link SingleResponse<PermissionDTO>}
     */
    @Override
    public SingleResponse<PermissionDTO> query(Long permissionId) {
        return permissionByIdQryExe.execute(permissionId);
    }
}
