package cn.meshed.cloud.iam.domain.account.ability;

import cn.meshed.cloud.ability.BaseAbility;
import cn.meshed.cloud.core.IPageList;
import cn.meshed.cloud.iam.account.command.AccountAddCmd;
import cn.meshed.cloud.iam.account.command.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.account.command.AccountLockCmd;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountByIdQry;
import cn.meshed.cloud.iam.account.query.AccountQry;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface AccountService
        extends BaseAbility<AccountAddCmd, AccountAddCmd, Long, AccountByIdQry, Response, SingleResponse<AccountDTO>>,
        IPageList<AccountQry, PageResponse<AccountDTO>> {

    /**
     * 授权用户角色
     *
     * @param accountGrantRoleCmd 授权请求对象
     * @return 处理结果
     */
    Response grantAccount(AccountGrantRoleCmd accountGrantRoleCmd);

    /**
     * 授权用户角色
     *
     * @param accountByIdQry 账号ID查询对象
     * @return 处理结果
     */
    SingleResponse<Set<Long>> getAccountRoles(AccountByIdQry accountByIdQry);

    /**
     * 账号的锁定和解锁
     *
     * @param accountLockCmd 操作参数
     * @return 处理结果
     */
    Response operateLock(AccountLockCmd accountLockCmd);
}
