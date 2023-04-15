package cn.meshed.cloud.iam.account.executor;

import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.executor.query.UserSelectQryExe;
import cn.meshed.cloud.iam.account.query.UserSelectQry;
import cn.meshed.cloud.iam.domain.account.ability.UserAbility;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserAbilityImpl implements UserAbility {

    private final UserSelectQryExe userSelectQryExe;

    /**
     * @param userSelectQry
     * @return
     */
    @Override
    public MultiResponse<UserDTO> select(UserSelectQry userSelectQry) {
        return userSelectQryExe.execute(userSelectQry);
    }
}
