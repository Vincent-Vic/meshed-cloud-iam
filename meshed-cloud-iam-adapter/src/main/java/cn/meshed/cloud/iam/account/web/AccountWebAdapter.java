package cn.meshed.cloud.iam.account.web;

import cn.meshed.cloud.iam.account.AccountAdapter;
import cn.meshed.cloud.iam.account.command.AccountAddCmd;
import cn.meshed.cloud.iam.account.command.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.account.command.AccountLockCmd;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountPageQry;
import cn.meshed.cloud.iam.domain.account.ability.AccountAbility;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
public class AccountWebAdapter implements AccountAdapter {

    private final AccountAbility accountAbility;

    /**
     * 分页列表
     *
     * @param pageQry 分页参数
     * @return {@link PageResponse<AccountDTO>}
     */
    @Override
    public PageResponse<AccountDTO> list(AccountPageQry pageQry) {
        return accountAbility.searchPageList(pageQry);
    }

    /**
     * 保存
     *
     * @param accountAddCmd 新增操作
     * @return {@link Response}
     */
    @Override
    public Response save(@Valid AccountAddCmd accountAddCmd) {
        return accountAbility.save(accountAddCmd);
    }

    /**
     * 删除
     *
     * @param id 账号ID
     * @return {@link Response}
     */
    @Override
    public Response delete(Long id) {
        return accountAbility.delete(id);
    }

    /**
     * 详情
     *
     * @param id 账号ID
     * @return {@link SingleResponse<AccountDTO>}
     */
    @Override
    public SingleResponse<AccountDTO> details(Long id) {
        return accountAbility.query(id);
    }

    /**
     * 账号角色编码列表
     *
     * @param id 账号ID
     * @return {@link MultiResponse <Long>}
     */
    @Override
    public MultiResponse<Long> getAccountRoleIds(Long id) {
        return accountAbility.getAccountRoles(id);
    }

    /**
     * 账号授权
     *
     * @param accountGrantRoleCmd 账号授权
     * @return {@link Response}
     */
    @Override
    public Response grantAccount(@Valid AccountGrantRoleCmd accountGrantRoleCmd) {
        return accountAbility.grantAccount(accountGrantRoleCmd);
    }

    /**
     * 锁定账号
     *
     * @param accountLockCmd 锁定命令
     * @return {@link Response}
     */
    @Override
    public Response lock(@Valid AccountLockCmd accountLockCmd) {
        accountLockCmd.setOperate(true);
        return accountAbility.operateLock(accountLockCmd);
    }

    /**
     * 解锁账号
     *
     * @param accountLockCmd 解锁命令
     * @return {@link Response}
     */
    @Override
    public Response unlock(@Valid AccountLockCmd accountLockCmd) {
        accountLockCmd.setOperate(false);
        return accountAbility.operateLock(accountLockCmd);
    }
}
