package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PermissionByIdQryExe implements QueryExecute<Long, SingleResponse<PermissionDTO>> {

    private final PermissionGateway permissionGateway;


    /**
     * <h1>查询执行器</h1>
     *
     * @param permissionId 权限编码
     * @return {@link SingleResponse<PermissionDTO>}
     */
    @Override
    public SingleResponse<PermissionDTO> execute(Long permissionId) {
        AssertUtils.isTrue(permissionId != null, "权限编码不能为空");
        return ResultUtils.copy(permissionGateway.query(permissionId), PermissionDTO.class);
    }
}
