package cn.meshed.cloud.iam.executor.query;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.PermissionSetQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.gateway.RoleGateway;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PermissionSetQryExe implements CommandExecute<PermissionSetQry, SingleResponse<Set<PermissionVO>>> {

    private final RoleGateway roleGateway;

    /**
     * @param permissionSetQry
     * @return
     */
    @Override
    public SingleResponse<Set<PermissionVO>>execute(PermissionSetQry permissionSetQry) {
        if (!permissionSetQry.verifySelf()){
            return SingleResponse.buildFailure("400","未指定角色查询");
        }
        Set<PermissionVO> permissionSet = roleGateway.getPermissionSet(permissionSetQry.getRoleIds());
        return SingleResponse.of(permissionSet);
    }
}
