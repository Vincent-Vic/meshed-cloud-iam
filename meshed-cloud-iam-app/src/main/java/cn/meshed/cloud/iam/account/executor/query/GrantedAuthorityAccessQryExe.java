package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class GrantedAuthorityAccessQryExe implements QueryExecute<GrantedAuthorityQry, MultiResponse<String>> {

    private final AccountGateway accountGateway;

    /**
     * @param grantedAuthorityQry 权限查询对象
     * @return
     */
    @Override
    public MultiResponse<String> execute(GrantedAuthorityQry grantedAuthorityQry) {
        Set<Permission> grantedAuthoritys = accountGateway.getGrantedAuthority(grantedAuthorityQry.getAccountId());
        Set<String> accessSet = grantedAuthoritys.stream().map(Permission::getAccess).collect(Collectors.toSet());
        return MultiResponse.of(accessSet);
    }
}
