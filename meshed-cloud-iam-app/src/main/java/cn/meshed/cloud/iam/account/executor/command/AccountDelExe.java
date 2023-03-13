package cn.meshed.cloud.iam.account.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import cn.meshed.cloud.utils.ResultUtils;
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
public class AccountDelExe implements CommandExecute<Long, Response> {

    private final AccountGateway accountGateway;

    /**
     * @param id
     * @return
     */
    @Override
    public Response execute(Long id) {
        return ResultUtils.of(accountGateway.delete(id),"删除账号失败");
    }
}
