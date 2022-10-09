package cn.meshed.cloud.iam.domain.ability;

import cn.meshed.base.standard.ISingle;
import cn.meshed.cloud.iam.domain.dto.cmd.PermissionCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.PermissionQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface PermissionService extends ISingle<PermissionQry, MultiResponse<PermissionVO>, PermissionCmd, Long, Response> {
}
