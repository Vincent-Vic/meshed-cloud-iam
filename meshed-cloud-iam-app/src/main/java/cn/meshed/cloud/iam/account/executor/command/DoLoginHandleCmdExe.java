package cn.meshed.cloud.iam.account.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.command.DoLoginHandleCmd;
import cn.meshed.cloud.iam.account.command.LoginSuccessCmd;
import cn.meshed.cloud.iam.account.data.LoginSuccessDTO;
import cn.meshed.cloud.iam.account.executor.query.AccountByLoginIdQryExe;
import cn.meshed.cloud.iam.account.query.AccountByLoginIdQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.ability.EncryptionService;
import cn.meshed.cloud.utils.CopyUtils;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DoLoginHandleCmdExe implements CommandExecute<DoLoginHandleCmd, Response> {

    private final AccountByLoginIdQryExe accountByLoginIdQryExe;
    private final EncryptionService encryptionService;
    private final LoginSuccessCmdExe loginSuccessCmdExe;
    /**
     * @param doLoginHandleCmd
     * @return
     */
    @Override
    public SingleResponse<LoginSuccessDTO> execute(DoLoginHandleCmd doLoginHandleCmd) {
        AccountByLoginIdQry accountByLoginIdQry = new AccountByLoginIdQry();
        accountByLoginIdQry.setLoginId(doLoginHandleCmd.getLoginName());
        SingleResponse<Account> response = accountByLoginIdQryExe.execute(accountByLoginIdQry);
        if (response == null || !response.isSuccess() || !isLogin(doLoginHandleCmd, response.getData())){
            return SingleResponse.buildFailure("400","账号或密码不正确");
        }
        return loginSuccessCmdExe.execute(CopyUtils.copy(response.getData(), LoginSuccessCmd.class));
    }

    private Boolean isLogin(DoLoginHandleCmd doLoginHandleCmd, Account account) {
        return encryptionService.matches(doLoginHandleCmd.getPassword(),account.getSecretKey());
    }
}
