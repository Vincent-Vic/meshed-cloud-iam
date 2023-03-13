package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.Role;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.iam.rbac.data.RoleDTO;
import cn.meshed.cloud.iam.rbac.query.RoleByIdQry;
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
public class RoleByIdQryExe implements CommandExecute<RoleByIdQry, SingleResponse<RoleDTO>> {

    private final RoleGateway roleGateway;

    /**
     * @param roleByIdQry
     * @return
     */
    @Override
    public SingleResponse<RoleDTO> execute(RoleByIdQry roleByIdQry) {
        Role role = roleGateway.query(roleByIdQry.getId());
        if (role == null){
            return ResultUtils.fail("角色不存在");
        }
        return ResultUtils.copy(role,RoleDTO.class);
    }
}
