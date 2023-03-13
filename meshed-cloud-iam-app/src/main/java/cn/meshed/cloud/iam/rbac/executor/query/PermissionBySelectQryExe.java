package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.constant.Status;
import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.rbac.data.PermissionOptionDTO;
import cn.meshed.cloud.iam.rbac.query.PermissionBySelectQry;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PermissionBySelectQryExe implements CommandExecute<PermissionBySelectQry, SingleResponse<List<PermissionOptionDTO>>> {

    private final PermissionGateway permissionGateway;

    /**
     * @param permissionBySelectQry
     * @return
     */
    @Override
    public SingleResponse<List<PermissionOptionDTO>> execute(PermissionBySelectQry permissionBySelectQry) {
        PermissionQry permissionQry = new PermissionQry();
        permissionQry.setStatus(Status.VALID);
        permissionQry.setAccessMode(permissionBySelectQry.getAccessMode());
        return ResultUtils.copyList(permissionGateway.searchList(permissionQry),PermissionOptionDTO::new);
    }
}
