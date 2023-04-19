package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.iam.domain.rbac.Role;
import cn.meshed.cloud.utils.AssertUtils;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
public class GrantedRoleAccessQryExe implements QueryExecute<GrantedAuthorityQry, MultiResponse<String>> {

    private final AccountGateway accountGateway;

    /**
     * @param grantedAuthorityQry 权限查询对象
     * @return
     */
    @Override
    public MultiResponse<String> execute(GrantedAuthorityQry grantedAuthorityQry) {
        AssertUtils.isTrue(grantedAuthorityQry != null, "授权参数不能为空");
        Set<Role> grantedAuthoritys = accountGateway.getAccountRoleSet(grantedAuthorityQry.getAccountId());
        if (CollectionUtils.isEmpty(grantedAuthoritys)) {
            return MultiResponse.buildSuccess();
        }
        Set<String> accessSet = grantedAuthoritys.stream().map(Role::getAccess).collect(Collectors.toSet());
        return MultiResponse.of(accessSet);
    }
}
