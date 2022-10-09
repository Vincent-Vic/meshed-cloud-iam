package cn.meshed.cloud.iam.domain.gateway;

import cn.meshed.base.standard.ISingle;
import cn.meshed.cloud.iam.domain.dto.AccountDTO;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.AccountQry;
import cn.meshed.cloud.iam.domain.dto.vo.AccountVO;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface AccountGateway extends ISingle<AccountQry, AccountVO, AccountCmd, Long, Boolean> {

    /**
     * 根据登入ID获取账号消息  todo 解决敏感信息RPC调用
     *
     * @param loginId 登入ID也就是登入名称
     * @return {@link AccountDTO}
     */
    AccountDTO getAccountByLoginId(String loginId);

    /**
     * 授权用户角色
     *
     * @param accountId 账号ID
     * @param roleIds   角色ID
     * @return 处理结果
     */
    Boolean grantRole(Long accountId, Set<Long> roleIds);


    /**
     * 获取账号权限
     *
     * @param accountId 权限账号
     * @return 权限字符集
     */
    Set<PermissionVO> getGrantedAuthority(Long accountId);

}
