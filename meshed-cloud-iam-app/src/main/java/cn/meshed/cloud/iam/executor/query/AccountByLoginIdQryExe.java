package cn.meshed.cloud.iam.executor.query;


import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.CopyUtils;
import cn.meshed.cloud.iam.domain.dto.AccountDTO;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
import cn.meshed.cloud.iam.dto.AccountByLoginIdRequest;
import cn.meshed.cloud.iam.dto.AccountResponse;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Component
public class AccountByLoginIdQryExe implements CommandExecute<AccountByLoginIdRequest, SingleResponse<AccountResponse>> {

    private final AccountGateway accountGateway;
    private final GrantedAuthorityQryExe grantedAuthorityQryExe;

    /**
     * <h2>查询执行器</h2>
     * @param accountByLoginIdRequest 请求对象
     * @return {@link AccountResponse}
     */
    @Override
    public SingleResponse<AccountResponse> execute(AccountByLoginIdRequest accountByLoginIdRequest) {
        String loginId = accountByLoginIdRequest.getLoginId();
        if (StringUtils.isBlank(loginId)){
            return null;
        }
        AccountDTO account = accountGateway.getAccountByLoginId(loginId);
        if (account == null){
            return null;
        }
        AccountResponse response = CopyUtils.copy(account, AccountResponse.class);

        //查询权限
        Set<String> permissions = getPermissions(account);
        response.setGrantedAuthority(permissions);
        return SingleResponse.of(response);
    }

    private Set<String> getPermissions(AccountDTO account) {
        GrantedAuthorityQry grantedAuthorityQry = new GrantedAuthorityQry();
        grantedAuthorityQry.setAccountId(account.getId());
        Set<PermissionVO> permissionSet = grantedAuthorityQryExe.execute(grantedAuthorityQry);
        return permissionSet.stream().map(PermissionVO::getEnname).collect(Collectors.toSet());
    }
}
