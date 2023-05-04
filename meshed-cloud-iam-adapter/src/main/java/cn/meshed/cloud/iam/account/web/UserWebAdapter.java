package cn.meshed.cloud.iam.account.web;

import cn.meshed.cloud.iam.account.UserAdapter;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.query.UserSelectQry;
import cn.meshed.cloud.iam.domain.account.ability.UserAbility;
import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>用户Web适配器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
public class UserWebAdapter implements UserAdapter {

    private final UserAbility userAbility;

    /**
     * 选项列表
     *
     * @param userSelectQry 选项参数
     * @return {@link MultiResponse < UserDTO >}
     */
    @Override
    public MultiResponse<UserDTO> list(UserSelectQry userSelectQry) {
        return userAbility.select(userSelectQry);
    }
}
