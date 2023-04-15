package cn.meshed.cloud.iam.account.gatewayimpl;

import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.enums.AccountStatusEnum;
import cn.meshed.cloud.iam.account.gatewayimpl.database.dataobject.AccountDO;
import cn.meshed.cloud.iam.account.gatewayimpl.database.dataobject.AccountRoleDO;
import cn.meshed.cloud.iam.account.gatewayimpl.database.mapper.AccountMapper;
import cn.meshed.cloud.iam.account.gatewayimpl.database.mapper.AccountRoleMapper;
import cn.meshed.cloud.iam.account.query.AccountPageQry;
import cn.meshed.cloud.iam.account.query.UserQry;
import cn.meshed.cloud.iam.account.query.UserSelectQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.iam.domain.rbac.Permission;
import cn.meshed.cloud.iam.domain.rbac.Role;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.CopyUtils;
import cn.meshed.cloud.utils.PageUtils;
import com.alibaba.cola.dto.PageResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>账号设施网关</h1>
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
     * @return {@link Account}
     */
    @Override
    public Account getAccountByLoginId(String loginId) {
        if (StringUtils.isBlank(loginId)) {
            return null;
        }
        LambdaQueryWrapper<AccountDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AccountDO::getLoginId, loginId);
        AccountDO accountDO = accountMapper.selectOne(lqw);
        if (accountDO == null) {
            return null;
        }
        return CopyUtils.copy(accountDO, Account.class);
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
        LambdaQueryWrapper<AccountRoleDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AccountRoleDO::getAccountId, accountId);
        accountRoleMapper.delete(lqw);
        //如果不存在，说明删除权限
        if (CollectionUtils.isEmpty(roleIds)) {
            return true;
        }
        //构建新的对象
        List<AccountRoleDO> rolePermissions = roleIds.stream()
                .map(roleId -> buildAccountRole(accountId, roleId))
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
    public Set<Permission> getGrantedAuthority(Long accountId) {
        AssertUtils.isTrue(accountId != null, "账号ID不能为空");
        Set<Long> roleIds = getAccountRoleIdSet(accountId);
        return roleGateway.getPermissionSet(roleIds);
    }


    /**
     * 获取账号的角色ID列表
     *
     * @param accountId 账号ID
     * @return
     */
    public Set<Long> getAccountRoleIdSet(Long accountId) {
        AssertUtils.isTrue(accountId != null, "账号ID不能为空");
        LambdaQueryWrapper<AccountRoleDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AccountRoleDO::getAccountId, accountId);
        List<AccountRoleDO> accountRoleDOS = accountRoleMapper.selectList(lqw);
        if (CollectionUtils.isEmpty(accountRoleDOS)) {
            return Sets.newHashSet();
        }
        return accountRoleDOS.stream().map(AccountRoleDO::getRoleId).collect(Collectors.toSet());
    }

    /**
     * 根据账号获取角色集合
     *
     * @param accountId 账号
     * @return 权限集合
     */
    @Override
    public Set<Role> getAccountRoleSet(Long accountId) {
        Set<Long> roleIds = getAccountRoleIdSet(accountId);
        return roleGateway.getRoleSet(roleIds);
    }


    /**
     * @param id
     * @return
     */
    @Override
    public Boolean delete(Long id) {
        AssertUtils.isTrue(id != null, "Id不能为空");
        return accountMapper.deleteById(id) > 0;
    }

    /**
     * @param accountQry
     * @return
     */
    @Override
    public PageResponse<Account> searchPageList(AccountPageQry accountQry) {
        Page<Object> page = PageUtils.startPage(accountQry.getPageIndex(), accountQry.getPageSize());
        LambdaQueryWrapper<AccountDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(accountQry.getStatus() != null, AccountDO::getStatus, accountQry.getStatus());
        lqw.like(StringUtils.isNotBlank(accountQry.getLoginId()), AccountDO::getLoginId, accountQry.getLoginId());
        lqw.like(StringUtils.isNotBlank(accountQry.getPhone()), AccountDO::getPhone, accountQry.getPhone());
        lqw.like(StringUtils.isNotBlank(accountQry.getEmail()), AccountDO::getEmail, accountQry.getEmail());
        return PageUtils.of(accountMapper.selectList(lqw), page, Account::new);
    }

    /**
     * @param account
     * @return
     */
    @Override
    public Boolean save(Account account) {
        AccountDO accountDO = CopyUtils.copy(account, AccountDO.class);
        return accountMapper.insert(accountDO) > 0;
    }

    /**
     * @param account
     * @return
     */
    @Override
    public Boolean update(Account account) {
        AccountDO accountDO = accountMapper.selectById(account.getId());
        AssertUtils.isTrue(accountDO != null, "账号不存在");
        //邮箱重置有效
        if (StringUtils.isBlank(accountDO.getEmail()) || !accountDO.getEmail().equals(account.getEmail())) {
            //邮箱不存在或者旧邮箱存在但是和新邮箱不匹配，无论新邮箱是否是清空操作都将有效置空
            accountDO.setEmail(account.getEmail());
            accountDO.setValidEmail(false);
        }
        //邮箱重置有效
        if (StringUtils.isBlank(accountDO.getPhone()) || !accountDO.getPhone().equals(account.getPhone())) {
            //手机号不存在或者旧手机号存在但是和新手机号不匹配，无论新手机号是否是清空操作都将有效置空
            accountDO.setPhone(account.getPhone());
            accountDO.setValidPhone(false);
        }
        accountDO.setRealName(accountDO.getRealName());
        return accountMapper.updateById(accountDO) > 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Account query(Long id) {
        AssertUtils.isTrue(id != null, "账号ID不能为空");
        AccountDO accountDO = accountMapper.selectById(id);
        //数据层防止密钥数据暴露
        accountDO.setSecretKey(null);
        return CopyUtils.copy(accountDO, Account.class);
    }


    // build
    private AccountRoleDO buildAccountRole(Long accountId, Long roleId) {
        AccountRoleDO accountRoleDO = new AccountRoleDO();
        accountRoleDO.setAccountId(accountId);
        accountRoleDO.setRoleId(roleId);
        return accountRoleDO;
    }

    /**
     * <h1>列表</h1>
     *
     * @param userQry 查询参数
     * @return {@link List<UserDTO>}
     */
    @Override
    public List<Account> searchList(UserQry userQry) {
        AssertUtils.isTrue(CollectionUtils.isNotEmpty(userQry.getIds()), "查询id列表不能为空");
        LambdaQueryWrapper<AccountDO> lqw = new LambdaQueryWrapper<>();
        lqw.in(AccountDO::getId, userQry.getIds());
        return CopyUtils.copyListProperties(accountMapper.selectList(lqw), Account::new);
    }

    /**
     * @param userSelectQry
     * @return
     */
    @Override
    public List<Account> select(UserSelectQry userSelectQry) {
        LambdaQueryWrapper<AccountDO> lqw = new LambdaQueryWrapper<>();
        lqw.select(AccountDO::getId, AccountDO::getLoginId, AccountDO::getRealName)
                .eq(AccountDO::getStatus, AccountStatusEnum.VALID);
        return CopyUtils.copyListProperties(accountMapper.selectList(lqw), Account::new);
    }
}
