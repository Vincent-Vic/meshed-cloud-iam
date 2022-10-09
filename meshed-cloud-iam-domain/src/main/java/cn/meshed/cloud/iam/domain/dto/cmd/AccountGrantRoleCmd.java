package cn.meshed.cloud.iam.domain.dto.cmd;

import cn.meshed.base.cqrs.Command;
import lombok.Data;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class AccountGrantRoleCmd implements Command {

    private Long accountId;
    private Set<Long> roleIds;

    /**
     * @return
     */
    @Override
    public Boolean verifySelf() {
        return accountId != null;
    }
}
