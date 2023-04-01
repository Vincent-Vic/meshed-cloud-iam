package cn.meshed.cloud.iam.account.executor.query;


import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountByLoginIdQry;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import com.alibaba.cola.dto.SingleResponse;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class AccountByLoginIdQryExe implements QueryExecute<AccountByLoginIdQry, SingleResponse<Account>> {

    private final AccountGateway accountGateway;
    private final GrantedAuthorityQryExe grantedAuthorityQryExe;

    /**
     * <h2>查询执行器</h2>
     *
     * @param accountByLoginIdQry 请求对象
     * @return {@link AccountDTO}
     */
    @Override
    public SingleResponse<Account> execute(AccountByLoginIdQry accountByLoginIdQry) {
        String loginId = accountByLoginIdQry.getLoginId();
        if (StringUtils.isBlank(loginId)) {
            return null;
        }
        Account account = accountGateway.getAccountByLoginId(loginId);
        if (account == null) {
            return null;
        }

        //查询权限
        Set<String> permissions = getPermissions(account);
        account.setGrantedAuthority(permissions);
        return SingleResponse.of(account);
    }

    private Set<String> getPermissions(Account account) {
        GrantedAuthorityQry grantedAuthorityQry = new GrantedAuthorityQry();
        grantedAuthorityQry.setAccountId(account.getId());
        SingleResponse<Set<PermissionDTO>> response = grantedAuthorityQryExe.execute(grantedAuthorityQry);
        if (!response.isSuccess() || CollectionUtils.isEmpty(response.getData())) {
            return Sets.newHashSet();
        }
        return response.getData().stream().map(PermissionDTO::getEnname).collect(Collectors.toSet());
    }
}
