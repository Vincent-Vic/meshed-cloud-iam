package cn.meshed.cloud.iam.account.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.command.SystemCmd;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.gateway.SystemGateway;
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
public class SystemCmdExe implements CommandExecute<SystemCmd, Response> {

    private final SystemGateway systemGateway;

    /**
     * <h1>执行器</h1>
     *
     * @param systemCmd 系统操作
     * @return {@link Response}
     */
    @Override
    public Response execute(SystemCmd systemCmd) {
        System system = new System(systemCmd.getName(), systemCmd.getKey(),
                systemCmd.getType(), systemCmd.getDescription());
        return ResultUtils.of(systemGateway.save(system));
    }
}
