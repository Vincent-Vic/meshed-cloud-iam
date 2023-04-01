package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.account.data.SystemDTO;
import cn.meshed.cloud.iam.account.query.SystemSelectQry;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.gateway.SystemGateway;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h1>选项查询</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SystemSelectQryExe implements CommandExecute<SystemSelectQry, MultiResponse<SystemDTO>> {

    private final SystemGateway systemGateway;

    /**
     * <h1>执行器</h1>
     *
     * @param systemSelectQry 执行器 {@link SystemSelectQry}
     * @return {@link MultiResponse<SystemDTO>}
     */
    @Override
    public MultiResponse<SystemDTO> execute(SystemSelectQry systemSelectQry) {
        List<System> list = systemGateway.select(systemSelectQry);
        return ResultUtils.copyMulti(list, SystemDTO::new);
    }
}
