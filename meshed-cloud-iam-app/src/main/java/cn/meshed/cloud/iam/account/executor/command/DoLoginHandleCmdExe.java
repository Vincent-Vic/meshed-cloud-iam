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
        //查询登入信息
        SingleResponse<Account> response = accountByLoginIdQryExe.execute(accountByLoginIdQry);
        if (!response.isSuccess() || !isLogin(doLoginHandleCmd, response.getData())) {
            return SingleResponse.buildFailure("LOGON_FAIL", "账号不存在或密码不正确");
        }
        //成功信息处理（以便扩展）
        return loginSuccessCmdExe.execute(CopyUtils.copy(response.getData(), LoginSuccessCmd.class));
    }

    /**
     * 密码校验
     *
     * @param doLoginHandleCmd 登入处理请求命令
     * @param account          账号信息
     * @return
     */
    private Boolean isLogin(DoLoginHandleCmd doLoginHandleCmd, Account account) {
        return encryptionService.matches(doLoginHandleCmd.getPassword(), account.getSecretKey());
    }
}
