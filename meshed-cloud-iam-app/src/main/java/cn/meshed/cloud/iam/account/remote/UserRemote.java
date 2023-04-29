package cn.meshed.cloud.iam.account.remote;

import cn.meshed.cloud.iam.account.UserRpc;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.executor.query.AccountPageQryExe;
import cn.meshed.cloud.iam.account.executor.query.GrantedAuthorityAccessQryExe;
import cn.meshed.cloud.iam.account.executor.query.GrantedRoleAccessQryExe;
import cn.meshed.cloud.iam.account.executor.query.UserByOneQryExe;
import cn.meshed.cloud.iam.account.query.AccountPageQry;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.iam.account.query.UserByOneQry;
import cn.meshed.cloud.iam.account.query.UserQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.CopyUtils;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@DubboService
@RequiredArgsConstructor
public class UserRemote implements UserRpc {

    private final UserByOneQryExe userByOneQryExe;
    private final AccountPageQryExe accountPageQryExe;
    private final GrantedAuthorityAccessQryExe grantedAuthorityAccessQryExe;
    private final GrantedRoleAccessQryExe grantedRoleAccessQryExe;
    private final AccountGateway accountGateway;


    /**
     * 获取用户权限
     *
     * @param grantedAuthorityQry 用户ID
     * @return 权限字符集
     */
    @Override
    public MultiResponse<String> getUserGrantedAuthority(GrantedAuthorityQry grantedAuthorityQry) {
        return grantedAuthorityAccessQryExe.execute(grantedAuthorityQry);
    }

    /**
     * 获取用户角色
     *
     * @param grantedAuthorityQry 用户权限查询
     * @return 角色字符集
     */
    @Override
    public MultiResponse<String> getUserGrantedRole(GrantedAuthorityQry grantedAuthorityQry) {
        return grantedRoleAccessQryExe.execute(grantedAuthorityQry);
    }

    /**
     * 获取用户信息
     *
     * @param userByOneQry 用户查询
     * @return 用户信息
     */
    @Override
    public SingleResponse<UserDTO> getUserInfo(UserByOneQry userByOneQry) {
        return userByOneQryExe.execute(userByOneQry);
    }

    /**
     * 批量获取用户信息
     *
     * @param userQry 用户查询
     * @return 用户信息列表
     */
    @Override
    public MultiResponse<UserDTO> getUserList(UserQry userQry) {
        AssertUtils.isTrue(CollectionUtils.isNotEmpty(userQry.getIds()), "查询id列表不能为空");
        List<Account> accounts = accountGateway.searchList(userQry);
        if (CollectionUtils.isNotEmpty(accounts)) {

            List<UserDTO> list = accounts.stream().map(account -> {
                UserDTO userDTO = CopyUtils.copy(account, UserDTO.class);
                userDTO.setName(account.getRealName());
                return userDTO;
            }).collect(Collectors.toList());
            return MultiResponse.of(list);
        }
        return MultiResponse.buildSuccess();
    }

    /**
     * @param accountPageQry
     * @return
     */
    @Override
    public PageResponse<AccountDTO> list(AccountPageQry accountPageQry) {
        return accountPageQryExe.execute(accountPageQry);
    }
}
