package cn.meshed.cloud.iam.domain.dto.cmd.qry;

import cn.meshed.base.cqrs.Command;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class PermissionSetQry implements Command {

    private Set<Long> roleIds;

    /**
     * @return
     */
    @Override
    public Boolean verifySelf() {
        return CollectionUtils.isNotEmpty(roleIds);
    }
}
