package cn.meshed.cloud.iam.account.executor;

import cn.meshed.cloud.iam.account.command.AccountAddCmd;
import cn.meshed.cloud.iam.account.command.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.account.command.AccountLockCmd;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.executor.command.AccountCmdExe;
import cn.meshed.cloud.iam.account.executor.command.AccountDelExe;
import cn.meshed.cloud.iam.account.executor.command.AccountGrantRoleCmdExe;
import cn.meshed.cloud.iam.account.executor.command.AccountLockCmdExe;
import cn.meshed.cloud.iam.account.executor.query.AccountByIdQryExe;
import cn.meshed.cloud.iam.account.executor.query.AccountListQryExe;
import cn.meshed.cloud.iam.account.executor.query.AccountRoleIdListQryExe;
import cn.meshed.cloud.iam.account.query.AccountByIdQry;
import cn.meshed.cloud.iam.account.query.AccountQry;
import cn.meshed.cloud.iam.domain.account.ability.AccountService;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    private final AccountListQryExe accountListQryExe;
    private final AccountDelExe accountDelExe;
    private final AccountGrantRoleCmdExe accountGrantRoleCmdExe;
    private final AccountLockCmdExe accountLockCmdExe;
    private final AccountByIdQryExe accountByIdQryExe;
    private final AccountRoleIdListQryExe accountRoleIdListQryExe;

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
    public PageResponse<AccountDTO> searchPageList(AccountQry accountQry) {
        return accountListQryExe.execute(accountQry);
    }

    /**
     * @param accountAddCmd 账号保存对象
     * @return 操作结果
     */
    @Override
    public Response save(AccountAddCmd accountAddCmd) {
        return accountCmdExe.execute(accountAddCmd);
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

    /**
     * 授权用户角色
     *
     * @param accountByIdQry 账号ID查询对象
     * @return 处理结果
     */
    @Override
    public SingleResponse<Set<Long>> getAccountRoles(AccountByIdQry accountByIdQry) {
        return accountRoleIdListQryExe.execute(accountByIdQry);
    }

    /**
     * 账号的锁定和解锁
     *
     * @param accountLockCmd 操作参数
     * @return 处理结果
     */
    @Override
    public Response operateLock(AccountLockCmd accountLockCmd) {
        return accountLockCmdExe.execute(accountLockCmd);
    }


    /**
     * @param accountByIdQry
     * @return
     */
    @Override
    public SingleResponse<AccountDTO> query(AccountByIdQry accountByIdQry) {
        return null;
    }
}
