package cn.meshed.cloud.iam.executor.query;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.base.utils.AssertUtils;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.GrantedAuthorityQry;
import cn.meshed.cloud.iam.domain.dto.vo.PermissionVO;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
import com.alibaba.cola.dto.SingleResponse;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class GrantedAuthorityQryExe implements CommandExecute<GrantedAuthorityQry, Set<PermissionVO>> {

    private final AccountGateway accountGateway;

    /**
     * @param grantedAuthorityQry 权限查询对象
     * @return
     */
    @Override
    public Set<PermissionVO> execute(GrantedAuthorityQry grantedAuthorityQry) {
        AssertUtils.isTrue(grantedAuthorityQry.verifySelf(),"参数错误");
        return accountGateway.getGrantedAuthority(grantedAuthorityQry.getAccountId());
    }
}
