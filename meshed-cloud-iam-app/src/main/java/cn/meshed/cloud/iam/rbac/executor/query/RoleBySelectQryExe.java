package cn.meshed.cloud.iam.rbac.executor.query;

import cn.meshed.cloud.constant.Status;
import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.iam.rbac.data.RoleOptionDTO;
import cn.meshed.cloud.iam.rbac.query.RoleBySelectQry;
import cn.meshed.cloud.iam.rbac.query.RoleQry;
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
public class RoleBySelectQryExe implements CommandExecute<RoleBySelectQry, SingleResponse<List<RoleOptionDTO>>> {

    private final RoleGateway roleGateway;

    /**
     * @param roleBySelectQry
     * @return
     */
    @Override
    public SingleResponse<List<RoleOptionDTO>> execute(RoleBySelectQry roleBySelectQry) {
        RoleQry roleQry = new RoleQry();
        roleQry.setStatus(Status.VALID);
        return ResultUtils.copyList(roleGateway.searchList(roleQry),RoleOptionDTO::new);
    }
}
