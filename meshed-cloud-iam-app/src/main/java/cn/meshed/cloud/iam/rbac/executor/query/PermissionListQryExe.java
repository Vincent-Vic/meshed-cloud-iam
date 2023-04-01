package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.MultiResponse;
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
public class PermissionListQryExe implements QueryExecute<PermissionQry, MultiResponse<PermissionDTO>> {

    private final PermissionGateway permissionGateway;

    /**
     * @param permissionQry
     * @return
     */
    @Override
    public MultiResponse<PermissionDTO> execute(PermissionQry permissionQry) {
        List<Permission> permissionVOList = permissionGateway.searchList(permissionQry);
        return ResultUtils.copyMulti(permissionVOList, PermissionDTO::new);
    }
}
