package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.gateway.SystemGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <h1>根据key查询</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SystemByKeyQryExe implements QueryExecute<String, SingleResponse<System>> {

    private final SystemGateway systemGateway;

    /**
     * <h1>查询执行器</h1>
     *
     * @param key 唯一标识
     * @return {@link Response}
     */
    @Override
    public SingleResponse<System> execute(String key) {
        AssertUtils.isTrue(StringUtils.isNotBlank(key), "标识不能为空");
        return ResultUtils.of(systemGateway.getSystemByKey(key));
    }
}
