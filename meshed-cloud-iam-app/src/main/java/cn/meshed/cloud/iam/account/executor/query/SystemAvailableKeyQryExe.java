package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.domain.account.gateway.SystemGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class SystemAvailableKeyQryExe implements QueryExecute<String, Response> {

    private final SystemGateway systemGateway;

    /**
     * <h1>查询执行器</h1>
     *
     * @param key 唯一标识
     * @return {@link Response}
     */
    @Override
    public Response execute(String key) {
        AssertUtils.isTrue(StringUtils.isNotBlank(key), "标识不能为空");
        return ResultUtils.of(!systemGateway.existFormKey(key));
    }
}
