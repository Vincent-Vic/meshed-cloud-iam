package cn.meshed.cloud.iam.executor.query;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.RoleQry;
import cn.meshed.cloud.iam.domain.dto.vo.RoleVO;
import cn.meshed.cloud.iam.domain.gateway.RoleGateway;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1></h1>
 *
 * @version 1.0
 * @author Vincent Vic
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RoleQryExe implements CommandExecute<RoleQry, MultiResponse<RoleVO>> {

    private final RoleGateway roleGateway;

    /**
     * @param roleQry
     * @return
     */
    @Override
    public MultiResponse<RoleVO> execute(RoleQry roleQry) {
        return null;
    }
}
