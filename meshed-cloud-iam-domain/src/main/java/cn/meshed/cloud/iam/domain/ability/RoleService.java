package cn.meshed.cloud.iam.domain.ability;

import cn.meshed.base.standard.ISingle;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.RoleGrantPermissionCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.RoleQry;
import cn.meshed.cloud.iam.domain.dto.vo.RoleVO;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface RoleService extends ISingle<RoleQry, MultiResponse<RoleVO>, RoleCmd, Long, Response> {

    /**
     * 授予角色权限
     *
     * @param roleGrantPermissionCmd  授权操作对象
     * @return 处理情况
     */
    Response grantPermission(RoleGrantPermissionCmd roleGrantPermissionCmd);
}
