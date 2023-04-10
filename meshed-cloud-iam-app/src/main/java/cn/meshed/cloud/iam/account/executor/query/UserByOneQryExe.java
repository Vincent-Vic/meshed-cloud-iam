package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.iam.account.query.UserByOneQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.CopyUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class UserByOneQryExe implements QueryExecute<UserByOneQry, SingleResponse<UserDTO>> {

    private final AccountGateway accountGateway;
    private final GrantedAuthorityAccessQryExe grantedAuthorityAccessQryExe;

    /**
     * <h1>查询执行器</h1>
     *
     * @param userByOneQry 用户信息
     * @return {@link SingleResponse<UserDTO>}
     */
    @Override
    public SingleResponse<UserDTO> execute(UserByOneQry userByOneQry) {
        AssertUtils.isTrue(userByOneQry.getId() != null, "账号ID不能为空");
        Account account = accountGateway.query(userByOneQry.getId());
        AssertUtils.isTrue(account != null, "账号不存在");
        UserDTO userDTO = CopyUtils.copy(account, UserDTO.class);
        assert account != null;
        userDTO.setName(account.getRealName());
        //是否查询用户权限
        if (userByOneQry.getHasGrantedAuthority()) {
            userDTO.setGrantedAuthority(getPermissions(userByOneQry.getId()));
        }
        return ResultUtils.of(userDTO);
    }

    private Set<String> getPermissions(Long id) {
        GrantedAuthorityQry grantedAuthorityQry = new GrantedAuthorityQry();
        grantedAuthorityQry.setAccountId(id);
        MultiResponse<String> response = grantedAuthorityAccessQryExe.execute(grantedAuthorityQry);
        if (!response.isSuccess() || CollectionUtils.isEmpty(response.getData())) {
            return Sets.newHashSet();
        }
        return new HashSet<>(response.getData());
    }
}
