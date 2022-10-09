package cn.meshed.cloud.iam.domain.ability;

import cn.meshed.base.standard.ISingle;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.AccountQry;
import cn.meshed.cloud.iam.domain.dto.vo.AccountVO;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface AccountService  extends ISingle<AccountQry, MultiResponse<AccountVO>, AccountCmd, Long, Response> {

    /**
     * 授权用户角色
     *
     * @param accountGrantRoleCmd 授权请求对象
     * @return 处理结果
     */
    Response grantAccount(AccountGrantRoleCmd accountGrantRoleCmd);
}
