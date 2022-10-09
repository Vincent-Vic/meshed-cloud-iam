package cn.meshed.cloud.iam.domain.dto.cmd;

import cn.meshed.base.cqrs.Command;
import lombok.Data;

import java.util.Set;

/**
 * <h1>角色授权权限</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class RoleGrantPermissionCmd implements Command {

    private Long roleId;
    private Set<Long> permissionIds;

    /**
     * @return
     */
    @Override
    public Boolean verifySelf() {
        return roleId != null;
    }
}
