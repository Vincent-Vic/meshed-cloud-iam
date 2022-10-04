package cn.meshed.cloud.iam.gatewayimpl;

import cn.meshed.base.utils.CopyUtils;
import cn.meshed.cloud.iam.domain.dto.AccountDTO;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
import cn.meshed.cloud.iam.gatewayimpl.database.dataobject.Account;
import cn.meshed.cloud.iam.gatewayimpl.database.mapper.AccountMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

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
        return CopyUtils.copy(account,AccountDTO.class);
    }
}
