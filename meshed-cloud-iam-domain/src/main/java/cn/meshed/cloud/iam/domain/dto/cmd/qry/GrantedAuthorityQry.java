package cn.meshed.cloud.iam.domain.dto.cmd.qry;


import cn.meshed.base.cqrs.Command;
import lombok.Data;

import java.io.Serializable;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class GrantedAuthorityQry implements Command {

    private Long accountId;
    /**
     * @return
     */
    @Override
    public Boolean verifySelf() {
        return accountId != null;
    }
}
