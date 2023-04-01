package cn.meshed.cloud.iam.account.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.iam.account.data.SystemDTO;
import cn.meshed.cloud.iam.domain.account.gateway.SystemGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>id查询</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SystemByIdQryExe implements QueryExecute<Integer, SingleResponse<SystemDTO>> {

    private final SystemGateway systemGateway;

    /**
     * <h1>查询执行器</h1>
     *
     * @param systemId 系统ID
     * @return {@link Response}
     */
    @Override
    public SingleResponse<SystemDTO> execute(Integer systemId) {
        AssertUtils.isTrue(systemId != null, "系统编码不能为空");
        return ResultUtils.copy(systemGateway.query(systemId), SystemDTO.class);
    }
}
