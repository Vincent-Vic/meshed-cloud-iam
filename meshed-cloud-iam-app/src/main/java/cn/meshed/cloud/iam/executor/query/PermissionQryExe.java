package cn.meshed.cloud.iam.executor.query;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.PermissionQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.gateway.PermissionGateway;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class PermissionQryExe implements CommandExecute<PermissionQry, MultiResponse<PermissionVO>> {

    private final PermissionGateway permissionGateway;

    /**
     * @param permissionQry
     * @return
     */
    @Override
    public MultiResponse<PermissionVO> execute(PermissionQry permissionQry) {
        return null;
    }
}
