package cn.meshed.cloud.iam.rbac.remote;

import cn.meshed.cloud.constant.Status;
import cn.meshed.cloud.iam.domain.rbac.gateway.PermissionGateway;
import cn.meshed.cloud.iam.rbac.PermissionRpc;
import cn.meshed.cloud.iam.rbac.data.IdentityAuthenticationDTO;
import cn.meshed.cloud.iam.rbac.enums.AccessModeEnum;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
import cn.meshed.cloud.utils.ResultUtils;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@DubboService
@RequiredArgsConstructor
public class PermissionRemote implements PermissionRpc {

    private final PermissionGateway permissionGateway;
    private final List<AccessModeEnum> accessModes = new ArrayList<AccessModeEnum>() {{
        add(AccessModeEnum.ANONYMOUS);
        add(AccessModeEnum.EMPOWER);
    }};

    /**
     * @return
     */
    @Override
    public MultiResponse<IdentityAuthenticationDTO> getIdentityAuthentications() {
        PermissionQry permissionQry = new PermissionQry();
        permissionQry.setAccessModes(accessModes);
        permissionQry.setStatus(Status.VALID);
        return ResultUtils.copyMulti(permissionGateway.searchList(permissionQry), IdentityAuthenticationDTO::new);
    }
}
