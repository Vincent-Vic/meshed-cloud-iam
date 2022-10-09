package cn.meshed.cloud.iam;

import cn.meshed.cloud.iam.domain.ability.RoleService;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleGrantPermissionCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.RoleQry;
import cn.meshed.cloud.iam.domain.dto.vo.RoleVO;
import cn.meshed.cloud.iam.executor.RoleCmdExe;
import cn.meshed.cloud.iam.executor.RoleDelExe;
import cn.meshed.cloud.iam.executor.RoleGrantPermissionCmdExe;
import cn.meshed.cloud.iam.executor.query.RoleQryExe;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <h1>角色操作能力</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleCmdExe roleCmdExe;
    private final RoleDelExe roleDelExe;
    private final RoleQryExe roleQryExe;
    private final RoleGrantPermissionCmdExe roleGrantPermissionCmdExe;

    /**
     * 删除
     * @param id id
     * @return 删除结果
     */
    @Override
    public Response delete(Long id) {
        return roleDelExe.execute(id);
    }

    /**
     * 分页列表查询
     * @param roleQry 角色查询
     * @return 分页查询
     */
    @Override
    public MultiResponse<RoleVO> searchPageList(RoleQry roleQry) {
        return roleQryExe.execute(roleQry);
    }

    /**
     * 保存
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
}
