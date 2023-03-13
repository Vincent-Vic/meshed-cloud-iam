package cn.meshed.cloud.iam.domain.account.ability;

import cn.meshed.cloud.iam.account.data.LoginSuccessDTO;
import com.alibaba.cola.dto.SingleResponse;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface DoLoginHandle {

    /**
     * 登入处理器
     * @param loginName
     * @param password
     * @return
     */
    SingleResponse<LoginSuccessDTO> loginHandle(String loginName, String password);
}
