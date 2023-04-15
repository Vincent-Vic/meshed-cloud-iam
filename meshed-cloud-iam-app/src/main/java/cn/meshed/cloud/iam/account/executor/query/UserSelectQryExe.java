package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.query.UserSelectQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class UserSelectQryExe implements QueryExecute<UserSelectQry, MultiResponse<UserDTO>> {

    private final AccountGateway accountGateway;

    /**
     * @param userSelectQry
     * @return
     */
    @Override
    public MultiResponse<UserDTO> execute(UserSelectQry userSelectQry) {
        List<Account> accounts = accountGateway.select(userSelectQry);
        if (CollectionUtils.isEmpty(accounts)) {
            return MultiResponse.buildSuccess();
        }
        List<UserDTO> users = accounts.stream().map(this::toUser).collect(Collectors.toList());
        return MultiResponse.of(users);
    }

    private UserDTO toUser(Account account) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(account.getId());
        userDTO.setLoginId(account.getLoginId());
        userDTO.setName(account.getRealName());
        return userDTO;
    }
}
