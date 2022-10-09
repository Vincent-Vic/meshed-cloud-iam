package cn.meshed.cloud.iam;

import cn.meshed.cloud.iam.domain.ability.PermissionService;
import cn.meshed.cloud.iam.domain.dto.cmd.PermissionCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.PermissionQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.executor.PermissionCmdExe;
import cn.meshed.cloud.iam.executor.PermissionDelExe;
import cn.meshed.cloud.iam.executor.RoleCmdExe;
import cn.meshed.cloud.iam.executor.RoleDelExe;
import cn.meshed.cloud.iam.executor.query.PermissionQryExe;
import cn.meshed.cloud.iam.executor.query.RoleQryExe;
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
public class PermissionServiceImpl implements PermissionService {

    private final PermissionCmdExe roleCmdExe;
    private final PermissionDelExe roleDelExe;
    private final PermissionQryExe roleQryExe;


    /**
     * 删除
     * @param id id
     * @return 操作结果
     */
    @Override
    public Response delete(Long id) {
        return roleDelExe.execute(id);
    }

    /**
     * 分页查询
     * @param permissionQry 查询权限对象
     * @return 分页列表
     */
    @Override
    public MultiResponse<PermissionVO> searchPageList(PermissionQry permissionQry) {
        return roleQryExe.execute(permissionQry);
    }

    /**
     * 保存
     * @param permissionCmd 保存对象
     * @return 操作结果
     */
    @Override
    public Response save(PermissionCmd permissionCmd) {
        return roleCmdExe.execute(permissionCmd);
    }
}
