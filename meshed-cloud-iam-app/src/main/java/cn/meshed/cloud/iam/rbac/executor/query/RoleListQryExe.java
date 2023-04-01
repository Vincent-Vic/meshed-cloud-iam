package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.rbac.Role;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.iam.rbac.data.RoleDTO;
import cn.meshed.cloud.iam.rbac.query.RoleQry;
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
public class RoleListQryExe implements QueryExecute<RoleQry, MultiResponse<RoleDTO>> {

    private final RoleGateway roleGateway;

    /**
     * @param roleQry
     * @return
     */
    @Override
    public MultiResponse<RoleDTO> execute(RoleQry roleQry) {
        List<Role> roles = roleGateway.searchList(roleQry);
        return ResultUtils.copyMulti(roles, RoleDTO::new);
    }
}
