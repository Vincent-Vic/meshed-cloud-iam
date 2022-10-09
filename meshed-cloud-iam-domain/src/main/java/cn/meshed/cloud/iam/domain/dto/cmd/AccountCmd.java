package cn.meshed.cloud.iam.domain.dto.cmd;

import cn.meshed.base.cqrs.Command;
import cn.meshed.base.utils.AssertUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author by Vincent Vic
 * @since 2022-10-04
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountCmd implements Command {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;


    /**
     * 账号登入主名称
     */
    private String loginId;

    /**
     * 账号手机号
     */
    private String phone;

    /**
     * 账号邮箱
     */
    private String email;


    /**
     * 账号加密后的密码
     */
    private String secretKey;


    /**
     * @return
     */
    @Override
    public Boolean verifySelf() {
        //账号用户名暂不支持更新
        if (id != null){
            //直接无修改
            loginId = null;
            //密码需要单独更新
            secretKey = null;
        } else {
            //新增的情况
            AssertUtils.isTrue(StringUtils.isNotBlank(loginId),"登入名称不能为空");
            AssertUtils.isTrue(StringUtils.isNotBlank(secretKey),"登入名称不能为空");
        }
        return true;
    }
}
