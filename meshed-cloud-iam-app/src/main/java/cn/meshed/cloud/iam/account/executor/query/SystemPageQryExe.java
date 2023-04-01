package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.SystemDTO;
import cn.meshed.cloud.iam.account.query.SystemPageQry;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.gateway.SystemGateway;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>分页查询</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SystemPageQryExe implements QueryExecute<SystemPageQry, PageResponse<SystemDTO>> {

    private final SystemGateway systemGateway;

    /**
     * <h1>查询执行器</h1>
     *
     * @param systemPageQry 执行器 {@link SystemPageQry}
     * @return {@link PageResponse<SystemDTO>}
     */
    @Override
    public PageResponse<SystemDTO> execute(SystemPageQry systemPageQry) {
        PageResponse<System> response = systemGateway.searchList(systemPageQry);
        return ResultUtils.copyPage(response, SystemDTO::new);
    }
}
