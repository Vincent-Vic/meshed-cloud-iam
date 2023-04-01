package cn.meshed.cloud.iam.domain.account.ability;

import cn.meshed.cloud.core.IDelete;
import cn.meshed.cloud.core.IPageList;
import cn.meshed.cloud.core.IQuery;
import cn.meshed.cloud.core.ISave;
import cn.meshed.cloud.iam.account.command.AccountAddCmd;
import cn.meshed.cloud.iam.account.command.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.account.command.AccountLockCmd;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountPageQry;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface AccountAbility
        extends IPageList<AccountPageQry, PageResponse<AccountDTO>>, ISave<AccountAddCmd, Response>,
        IQuery<Long, SingleResponse<AccountDTO>>, IDelete<Long, Response> {

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
     * @param id 账号ID
     * @return 处理结果
     */
    MultiResponse<Long> getAccountRoles(Long id);

    /**
     * 账号的锁定和解锁
     *
     * @param accountLockCmd 操作参数
     * @return 处理结果
     */
    Response operateLock(AccountLockCmd accountLockCmd);
}
