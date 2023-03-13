package cn.meshed.cloud.iam.account.remote;

import cn.meshed.cloud.iam.account.AccountServiceRpc;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.data.GrantedAuthorityDTO;
import cn.meshed.cloud.iam.account.executor.query.AccountByLoginIdQryExe;
import cn.meshed.cloud.iam.account.executor.query.GrantedAuthorityQryExe;
import cn.meshed.cloud.iam.account.query.AccountByLoginIdQry;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@DubboService
@RequiredArgsConstructor
public class AccountServiceApiImpl implements AccountServiceRpc {

    private final AccountByLoginIdQryExe accountByLoginIdQryExe;
    private final GrantedAuthorityQryExe grantedAuthorityQryExe;

    /**
     * 获取登入用户账号信息
     *
     * @param accountByLoginIdQry 请求对象
     * @return {@link SingleResponse<AccountByLoginIdQry>}
     */
    @Override
    public SingleResponse<AccountDTO> getAccountByLoginId(AccountByLoginIdQry accountByLoginIdQry) {
        SingleResponse<Account> response = accountByLoginIdQryExe.execute(accountByLoginIdQry);
        return ResultUtils.convertResponse(response, AccountDTO.class);
    }

    /**
     * @param grantedAuthorityQry 获取权限请求对象
     * @return {@link SingleResponse<Set<GrantedAuthorityDTO>>}
     */
    @Override
    public SingleResponse<Set<GrantedAuthorityDTO>> getGrantedAuthority(
            GrantedAuthorityQry grantedAuthorityQry) {
        if (grantedAuthorityQry.getAccountId() == null) {
            return SingleResponse.buildFailure("400", "账号不能为空");
        }
        SingleResponse<Set<PermissionDTO>> response = grantedAuthorityQryExe.execute(grantedAuthorityQry);
        if (!response.isSuccess()) {
            return ResultUtils.fail(response);
        }
        return ResultUtils.copySet(response.getData(), GrantedAuthorityDTO::new);
    }
}
