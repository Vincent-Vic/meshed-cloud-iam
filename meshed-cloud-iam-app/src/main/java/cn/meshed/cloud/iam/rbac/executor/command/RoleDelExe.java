package cn.meshed.cloud.iam.rbac.executor.command;

import cn.meshed.cloud.cqrs.CommandExecute;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>角色删除处理器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RoleDelExe implements CommandExecute<Long, Response> {

    private final RoleGateway roleGateway;

    /**
     * @param id
     * @return
     */
    @Override
    public Response execute(Long id) {
        //参数校验
        AssertUtils.isTrue(id != null,"参数缺失");
        return ResultUtils.of(roleGateway.delete(id));
    }
}
