package cn.meshed.cloud.iam.domain.account.ability;

import cn.meshed.cloud.core.ISelect;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.query.UserSelectQry;
import com.alibaba.cola.dto.MultiResponse;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface UserAbility extends ISelect<UserSelectQry, MultiResponse<UserDTO>> {
}
