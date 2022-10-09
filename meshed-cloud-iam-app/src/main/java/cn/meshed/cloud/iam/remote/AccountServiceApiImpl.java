package cn.meshed.cloud.iam.remote;

import cn.meshed.base.utils.CopyUtils;
import cn.meshed.cloud.iam.api.AccountServiceApi;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.dto.AccountByLoginIdRequest;
import cn.meshed.cloud.iam.dto.AccountResponse;
import cn.meshed.cloud.iam.dto.GrantedAuthorityRequest;
import cn.meshed.cloud.iam.dto.GrantedAuthorityResponse;
import cn.meshed.cloud.iam.executor.query.AccountByLoginIdQryExe;
import cn.meshed.cloud.iam.executor.query.GrantedAuthorityQryExe;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@DubboService
@RequiredArgsConstructor
public class AccountServiceApiImpl implements AccountServiceApi {

    private final AccountByLoginIdQryExe accountByLoginIdQryExe;
    private final GrantedAuthorityQryExe grantedAuthorityQryExe;

    /**
     * 获取登入用户账号信息
     * @param accountByLoginIdRequest 请求对象
     * @return {@link SingleResponse<AccountResponse>}
     */
    @Override
    public SingleResponse<AccountResponse> getAccountByLoginId(AccountByLoginIdRequest accountByLoginIdRequest) {
        return accountByLoginIdQryExe.execute(accountByLoginIdRequest);
    }

    /**
     * @param grantedAuthorityRequest 获取权限请求对象
     * @return {@link SingleResponse<Set<GrantedAuthorityResponse>>}
     */
    @Override
    public SingleResponse<Set<GrantedAuthorityResponse>> getGrantedAuthority(
            GrantedAuthorityRequest grantedAuthorityRequest) {
        Long accountId = grantedAuthorityRequest.getAccountId();
        if (accountId == null){
            return SingleResponse.buildFailure("400","账号不能为空");
        }
        GrantedAuthorityQry grantedAuthorityQry = new GrantedAuthorityQry();
        grantedAuthorityQry.setAccountId(accountId);
        Set<PermissionVO> voSet = grantedAuthorityQryExe.execute(grantedAuthorityQry);
        Set<GrantedAuthorityResponse> grantedAuthoritySet = voSet.stream().map(this::buildGrantedAuthorityResponse).collect(Collectors.toSet());
        return SingleResponse.of(grantedAuthoritySet);
    }

    /**
     * 构建权限返回对象
     * @param permissionVO 权限VO
     * @return {@link GrantedAuthorityResponse}
     */
    private GrantedAuthorityResponse buildGrantedAuthorityResponse(PermissionVO permissionVO) {
        return CopyUtils.copy(permissionVO,GrantedAuthorityResponse.class);
    }
}
