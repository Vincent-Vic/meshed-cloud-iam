package cn.meshed.cloud.iam.gatewayimpl;

import cn.meshed.base.utils.AssertUtils;
import cn.meshed.base.utils.CopyUtils;
import cn.meshed.cloud.iam.domain.dto.AccountDTO;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.AccountQry;
import cn.meshed.cloud.iam.domain.dto.vo.AccountVO;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
import cn.meshed.cloud.iam.domain.gateway.RoleGateway;
import cn.meshed.cloud.iam.gatewayimpl.database.dataobject.Account;
import cn.meshed.cloud.iam.gatewayimpl.database.dataobject.AccountRole;
import cn.meshed.cloud.iam.gatewayimpl.database.dataobject.Permission;
import cn.meshed.cloud.iam.gatewayimpl.database.mapper.AccountMapper;
import cn.meshed.cloud.iam.gatewayimpl.database.mapper.AccountRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class AccountGatewayImpl implements AccountGateway {

    private final AccountMapper accountMapper;
    private final AccountRoleMapper accountRoleMapper;

    private final RoleGateway roleGateway;

    /**
     * 根据登入ID获取账号消息
     *
     * @param loginId 登入ID也就是登入名称
     * @return {@link AccountDTO}
     */
    @Override
    public AccountDTO getAccountByLoginId(String loginId) {
        if (StringUtils.isBlank(loginId)){
            return null;
        }
        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Account::getLoginId,loginId);
        Account account = accountMapper.selectOne(lqw);
        if (account == null){
            return null;
        }
        return CopyUtils.copy(account, AccountDTO.class);
    }

    /**
     * 授权用户角色
     *
     * @param accountId 账号ID
     * @param roleIds   角色ID
     * @return 处理结果
     */
    @Override
    public Boolean grantRole(Long accountId, Set<Long> roleIds) {
        //先删除旧的
        LambdaQueryWrapper<AccountRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AccountRole::getAccountId,accountId);
        accountRoleMapper.delete(lqw);
        //如果不存在，说明删除权限
        if (CollectionUtils.isEmpty(roleIds)){
            return true;
        }
        //构建新的对象
        List<AccountRole> rolePermissions = roleIds.stream()
                .map(roleId -> buildAccountRole(accountId,roleId))
                .collect(Collectors.toList());
        //批量添加
        return accountRoleMapper.insertBatch(rolePermissions) > 0;
    }

    /**
     * 获取账号权限
     *
     * @param accountId 权限账号
     * @return 权限字符集
     */
    @Override
    public Set<PermissionVO> getGrantedAuthority(Long accountId) {
        AssertUtils.isTrue(accountId != null,"账号ID不能为空");
        LambdaQueryWrapper<AccountRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AccountRole::getAccountId,accountId);
        List<AccountRole> accountRoles = accountRoleMapper.selectList(lqw);
        if (CollectionUtils.isEmpty(accountRoles)){
            return Sets.newHashSet();
        }
        Set<Long> roleIds = accountRoles.stream().map(AccountRole::getRoleId).collect(Collectors.toSet());
        return roleGateway.getPermissionSet(roleIds);
    }

    private AccountRole buildAccountRole(Long accountId, Long roleId) {
        AccountRole accountRole = new AccountRole();
        accountRole.setAccountId(accountId);
        accountRole.setRoleId(roleId);
        return accountRole;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean delete(Long id) {
        AssertUtils.isTrue(id != null,"Id不能为空");
        return accountMapper.deleteById(id) > 0;
    }

    /**
     * @param accountQry
     * @return
     */
    @Override
    public AccountVO searchPageList(AccountQry accountQry) {
        return null;
    }

    /**
     * @param accountCmd
     * @return
     */
    @Override
    public Boolean save(AccountCmd accountCmd) {
        Account account = CopyUtils.copy(accountCmd, Account.class);
        account.setCreateTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());
        return accountMapper.insert(account) > 0;
    }

    /**
     * @param accountCmd
     * @return
     */
    @Override
    public Boolean update(AccountCmd accountCmd) {
        Account account = CopyUtils.copy(accountCmd, Account.class);
        account.setUpdateTime(LocalDateTime.now());
        return accountMapper.updateById(account) > 0;
    }
}
