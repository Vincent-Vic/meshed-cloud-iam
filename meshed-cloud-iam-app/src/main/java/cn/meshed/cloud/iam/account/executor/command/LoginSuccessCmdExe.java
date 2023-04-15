package cn.meshed.cloud.iam.account.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.command.LoginSuccessCmd;
import cn.meshed.cloud.iam.account.data.LoginSuccessDTO;
import cn.meshed.cloud.iam.account.executor.query.GrantedAuthorityAccessQryExe;
import cn.meshed.cloud.iam.account.executor.query.GrantedRoleAccessQryExe;
import cn.meshed.cloud.iam.account.query.GrantedAuthorityQry;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class LoginSuccessCmdExe implements CommandExecute<LoginSuccessCmd, SingleResponse<LoginSuccessDTO>> {

    private final GrantedAuthorityAccessQryExe grantedAuthorityAccessQryExe;
    private final GrantedRoleAccessQryExe grantedRoleAccessQryExe;

    /**
     * @param loginSuccessCmd
     * @return
     */
    @Override
    public SingleResponse<LoginSuccessDTO> execute(LoginSuccessCmd loginSuccessCmd) {
        //登入成功获取角色和权限
        setGrantedAuthority(loginSuccessCmd);
        setGrantedRole(loginSuccessCmd);

        return ResultUtils.copy(loginSuccessCmd, LoginSuccessDTO.class);
    }

    private void setGrantedAuthority(LoginSuccessCmd loginSuccessCmd) {
        MultiResponse<String> grantedAuthorityResp = grantedAuthorityAccessQryExe.execute(getGrantedAuthorityQry(loginSuccessCmd));
        if (grantedAuthorityResp.isNotEmpty()) {
            loginSuccessCmd.setGrantedAuthority(new HashSet<>(grantedAuthorityResp.getData()));
        }
    }

    private void setGrantedRole(LoginSuccessCmd loginSuccessCmd) {
        MultiResponse<String> grantedAuthorityResp = grantedRoleAccessQryExe.execute(getGrantedAuthorityQry(loginSuccessCmd));
        if (grantedAuthorityResp.isNotEmpty()) {
            loginSuccessCmd.setGrantedRole(new HashSet<>(grantedAuthorityResp.getData()));
        }
    }

    private GrantedAuthorityQry getGrantedAuthorityQry(LoginSuccessCmd loginSuccessCmd) {
        GrantedAuthorityQry grantedAuthorityQry = new GrantedAuthorityQry();
        grantedAuthorityQry.setAccountId(loginSuccessCmd.getId());
        return grantedAuthorityQry;
    }
}
