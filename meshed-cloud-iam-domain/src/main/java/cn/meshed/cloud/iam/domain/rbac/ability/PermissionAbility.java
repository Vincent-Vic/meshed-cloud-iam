package cn.meshed.cloud.iam.domain.rbac.ability;

import cn.meshed.cloud.core.IDelete;
import cn.meshed.cloud.core.IList;
import cn.meshed.cloud.core.IQuery;
import cn.meshed.cloud.core.ISave;
import cn.meshed.cloud.core.ISelect;
import cn.meshed.cloud.iam.rbac.command.PermissionCmd;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.iam.rbac.data.PermissionOptionDTO;
import cn.meshed.cloud.iam.rbac.query.PermissionBySelectQry;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface PermissionAbility extends IList<PermissionQry, MultiResponse<PermissionDTO>>,
        ISelect<PermissionBySelectQry, MultiResponse<PermissionOptionDTO>>, IQuery<Long, SingleResponse<PermissionDTO>>,
        ISave<PermissionCmd, Response>, IDelete<Long, Response> {


}
