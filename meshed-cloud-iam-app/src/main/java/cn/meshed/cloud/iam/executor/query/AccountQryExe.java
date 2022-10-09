package cn.meshed.cloud.iam.executor.query;

import cn.meshed.base.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.dto.cmd.qry.AccountQry;
import cn.meshed.cloud.iam.domain.dto.vo.AccountVO;
import cn.meshed.cloud.iam.domain.gateway.AccountGateway;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AccountQryExe implements CommandExecute<AccountQry, MultiResponse<AccountVO>> {

    private final AccountGateway accountGateway;

    /**
     * @param accountQry
     * @return
     */
    @Override
    public MultiResponse<AccountVO> execute(AccountQry accountQry) {
        return null;
    }
}
