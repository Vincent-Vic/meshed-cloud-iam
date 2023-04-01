package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.utils.ResultUtils;
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
public class GrantedAuthorityQryExe implements QueryExecute<GrantedAuthorityQry, SingleResponse<Set<PermissionDTO>>> {

    private final AccountGateway accountGateway;

    /**
     * @param grantedAuthorityQry 权限查询对象
     * @return
     */
    @Override
    public SingleResponse<Set<PermissionDTO>> execute(GrantedAuthorityQry grantedAuthorityQry) {
        Set<Permission> grantedAuthority = accountGateway.getGrantedAuthority(grantedAuthorityQry.getAccountId());
        return ResultUtils.copySet(grantedAuthority, PermissionDTO::new);
    }
}
