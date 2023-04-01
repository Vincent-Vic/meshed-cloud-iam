package cn.meshed.cloud.iam.domain.rbac.ability;

import cn.meshed.cloud.core.IDelete;
import cn.meshed.cloud.core.IList;
import cn.meshed.cloud.core.IQuery;
import cn.meshed.cloud.core.ISave;
import cn.meshed.cloud.core.ISelect;
import cn.meshed.cloud.iam.rbac.command.RoleCmd;
import cn.meshed.cloud.iam.rbac.command.RoleGrantPermissionCmd;
import cn.meshed.cloud.iam.rbac.data.RoleDTO;
import cn.meshed.cloud.iam.rbac.data.RoleOptionDTO;
import cn.meshed.cloud.iam.rbac.query.RoleBySelectQry;
import cn.meshed.cloud.iam.rbac.query.RoleQry;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface RoleAbility extends IList<RoleQry, MultiResponse<RoleDTO>>, IQuery<Long, SingleResponse<RoleDTO>>,
        ISelect<RoleBySelectQry, MultiResponse<RoleOptionDTO>>, ISave<RoleCmd, Response>, IDelete<Long, Response> {

    /**
     * 授予角色权限
     *
     * @param roleGrantPermissionCmd 授权操作对象
     * @return 处理情况
     */
    Response grantPermission(RoleGrantPermissionCmd roleGrantPermissionCmd);

    /**
     * 角色权限列表
     *
     * @param id 角色编码
     * @return 权限编码列表
     */
    MultiResponse<Long> getPermissionIds(Long id);

}
