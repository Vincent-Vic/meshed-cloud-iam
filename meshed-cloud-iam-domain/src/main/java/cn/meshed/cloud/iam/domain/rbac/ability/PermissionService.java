package cn.meshed.cloud.iam.domain.rbac.ability;

import cn.meshed.cloud.ability.BaseAbility;
import cn.meshed.cloud.core.IList;
import cn.meshed.cloud.iam.rbac.command.PermissionCmd;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.iam.rbac.data.PermissionOptionDTO;
import cn.meshed.cloud.iam.rbac.query.PermissionByIdQry;
import cn.meshed.cloud.iam.rbac.query.PermissionBySelectQry;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface PermissionService
        extends BaseAbility<PermissionCmd, PermissionCmd, Long, PermissionByIdQry, Response, SingleResponse<PermissionDTO>>,
        IList<PermissionQry, SingleResponse<List<PermissionDTO>>> {

    /**
     * 选择列表
     *
     * @param permissionBySelectQry 查询
     * @return
     */
    SingleResponse<List<PermissionOptionDTO>> select(PermissionBySelectQry permissionBySelectQry);
}
