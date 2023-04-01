package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import com.alibaba.cola.dto.MultiResponse;
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
public class AccountRoleIdsQryExe implements QueryExecute<Long, MultiResponse<Long>> {

    private final AccountGateway accountGateway;

    /**
     * 查询用户权限
     *
     * @param accountId 账号编码
     * @return
     */
    @Override
    public MultiResponse<Long> execute(Long accountId) {
        Set<Long> roleIdSet = accountGateway.getAccountRoleIdSet(accountId);
        return MultiResponse.of(roleIdSet);
    }
}
