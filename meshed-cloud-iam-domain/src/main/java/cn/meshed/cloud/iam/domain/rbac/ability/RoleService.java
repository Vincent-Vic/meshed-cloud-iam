package cn.meshed.cloud.iam.domain.rbac.ability;

import cn.meshed.cloud.ability.BaseAbility;
import cn.meshed.cloud.core.IList;
import cn.meshed.cloud.iam.rbac.command.RoleCmd;
import cn.meshed.cloud.iam.rbac.command.RoleGrantPermissionCmd;
import cn.meshed.cloud.iam.rbac.data.RoleDTO;
import cn.meshed.cloud.iam.rbac.data.RoleOptionDTO;
import cn.meshed.cloud.iam.rbac.query.RoleByIdQry;
import cn.meshed.cloud.iam.rbac.query.RoleBySelectQry;
import cn.meshed.cloud.iam.rbac.query.RolePermissionByIdQry;
import cn.meshed.cloud.iam.rbac.query.RoleQry;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

import java.util.List;
import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface RoleService extends BaseAbility<RoleCmd,RoleCmd, Long,RoleByIdQry, Response, SingleResponse<RoleDTO>> ,
        IList<RoleQry, SingleResponse<List<RoleDTO>>> {

    /**
     * 授予角色权限
     *
     * @param roleGrantPermissionCmd  授权操作对象
     * @return 处理情况
     */
    Response grantPermission(RoleGrantPermissionCmd roleGrantPermissionCmd);

    /**
     * 角色权限列表
     * @param rolePermissionByIdQry 角色权限列表参数
     * @return
     */
    SingleResponse<Set<Long>> permissions(RolePermissionByIdQry rolePermissionByIdQry);

    /**
     * 角色选择列表
     *
     * @param roleBySelectQry 角色选择参数
     * @return
     */
    SingleResponse<List<RoleOptionDTO>> select(RoleBySelectQry roleBySelectQry);
}
