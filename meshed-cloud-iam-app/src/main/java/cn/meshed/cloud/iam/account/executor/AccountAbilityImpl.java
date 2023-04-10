package cn.meshed.cloud.iam.account.executor;

import cn.meshed.cloud.iam.account.command.AccountAddCmd;
import cn.meshed.cloud.iam.account.command.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.account.command.AccountLockCmd;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.executor.command.AccountCmdExe;
import cn.meshed.cloud.iam.account.executor.command.AccountDelExe;
import cn.meshed.cloud.iam.account.executor.command.AccountGrantRoleCmdExe;
import cn.meshed.cloud.iam.account.executor.command.AccountLockCmdExe;
import cn.meshed.cloud.iam.account.executor.query.AccountByIdQryExe;
import cn.meshed.cloud.iam.account.executor.query.AccountPageQryExe;
import cn.meshed.cloud.iam.account.executor.query.AccountRoleIdsQryExe;
import cn.meshed.cloud.iam.account.executor.query.UserByOneQryExe;
import cn.meshed.cloud.iam.account.query.AccountPageQry;
import cn.meshed.cloud.iam.account.query.UserByOneQry;
import cn.meshed.cloud.iam.domain.account.ability.AccountAbility;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
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
public class AccountAbilityImpl implements AccountAbility {

    private final AccountCmdExe accountCmdExe;
    private final AccountPageQryExe accountPageQryExe;
    private final AccountDelExe accountDelExe;
    private final AccountGrantRoleCmdExe accountGrantRoleCmdExe;
    private final AccountLockCmdExe accountLockCmdExe;
    private final AccountByIdQryExe accountByIdQryExe;
    private final AccountRoleIdsQryExe accountRoleIdsQryExe;
    private final UserByOneQryExe userByOneQryExe;

    /**
     * @param id id
     * @return 操作结果
     */
    @Override
    public Response delete(Long id) {
        return accountDelExe.execute(id);
    }

    /**
     * 分页列表
     *
     * @param pageQry 分页参数
     * @return 操作结果
     */
    @Override
    public PageResponse<AccountDTO> searchPageList(AccountPageQry pageQry) {
        return accountPageQryExe.execute(pageQry);
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
     * 查询用户
     *
     * @param userByOneQry 用户查询
     * @return {@link SingleResponse< UserDTO >}
     */
    @Override
    public SingleResponse<UserDTO> queryUserById(UserByOneQry userByOneQry) {
        return userByOneQryExe.execute(userByOneQry);
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
     * @param id 账号ID
     * @return 处理结果
     */
    @Override
    public MultiResponse<Long> getAccountRoles(Long id) {
        return accountRoleIdsQryExe.execute(id);
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
     * 查询
     *
     * @param accountId 账号ID
     * @return {@link SingleResponse<AccountDTO>}
     */
    @Override
    public SingleResponse<AccountDTO> query(Long accountId) {
        return accountByIdQryExe.execute(accountId);
    }
}
