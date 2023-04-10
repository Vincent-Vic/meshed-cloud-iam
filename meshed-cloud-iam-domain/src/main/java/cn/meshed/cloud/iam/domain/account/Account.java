package cn.meshed.cloud.iam.domain.account;

import cn.meshed.cloud.iam.account.enums.AccountStatusEnum;
import lombok.Data;

/**
 * <h1>账号</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class Account {

    /**
     * ID
     */
    private Long id;

    /**
     * 账号登入主名称
     */
    private String loginId;

    /**
     * 账号名称
     */
    private String realName;

    /**
     * 账号手机号
     */
    private String phone;

    /**
     * 账号邮箱
     */
    private String email;

    /**
     * 账号手机号是否有效
     */
    private Boolean validPhone;

    /**
     * 账号有效是否有效
     */
    private Boolean validEmail;

    /**
     * 账号加密后的密码
     */
    private String secretKey;

    /**
     * 是否锁定
     */
    private AccountStatusEnum status;

}
