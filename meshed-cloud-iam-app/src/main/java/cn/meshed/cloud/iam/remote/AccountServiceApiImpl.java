package cn.meshed.cloud.iam.remote;

import cn.meshed.cloud.iam.api.AccountServiceApi;
import cn.meshed.cloud.iam.dto.AccountByLoginIdRequest;
import cn.meshed.cloud.iam.dto.AccountResponse;
import cn.meshed.cloud.iam.executor.query.AccountByLoginIdQryExe;
import lombok.RequiredArgsConstructor;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
public class AccountServiceApiImpl implements AccountServiceApi {

    private final AccountByLoginIdQryExe accountByLoginIdQryExe;

    @Override
    public AccountResponse getAccountByLoginId(AccountByLoginIdRequest accountByLoginIdRequest) {
        return accountByLoginIdQryExe.execute(accountByLoginIdRequest);
    }
}
