package cn.meshed.cloud.iam;

import cn.meshed.cloud.iam.domain.ability.AccountService;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.AccountQry;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.AccountQry;
import cn.meshed.cloud.iam.domain.dto.vo.AccountVO;
import cn.meshed.cloud.iam.executor.AccountCmdExe;
import cn.meshed.cloud.iam.executor.AccountDelExe;
import cn.meshed.cloud.iam.executor.AccountGrantRoleCmdExe;
import cn.meshed.cloud.iam.executor.query.AccountQryExe;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    
    private final AccountCmdExe accountCmdExe;
    private final AccountQryExe accountQryExe;
    private final AccountDelExe accountDelExe;
    private final AccountGrantRoleCmdExe accountGrantRoleCmdExe;

    /**
     * @param id id
     * @return 操作结果
     */
    @Override
    public Response delete(Long id) {
        return accountDelExe.execute(id);
    }

    /**
     * @param accountQry 账号查询对象
     * @return 操作结果
     */
    @Override
    public MultiResponse<AccountVO> searchPageList(AccountQry accountQry) {
        return accountQryExe.execute(accountQry);
    }

    /**
     * @param accountCmd 账号保存对象
     * @return 操作结果
     */
    @Override
    public Response save(AccountCmd accountCmd) {
        return accountCmdExe.execute(accountCmd);
    }

    /**
     * 授权用户角色
     *
     * @param accountGrantRoleCmd 授权请求对象
     * @return 处理结果
     */
    @Override
    public Response grantAccount(AccountGrantRoleCmd accountGrantRoleCmd) {
        return accountGrantRoleCmdExe.execute(accountGrantRoleCmd);
    }
}
