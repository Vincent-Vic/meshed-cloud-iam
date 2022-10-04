package cn.meshed.cloud.iam.gatewayimpl.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author by Vincent Vic
 * @since 2022-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_account")
public class Account implements Serializable {

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
     * 账号手机号是否有效 0 否 1 是
     */
    @TableId("is_valid_phone")
    private Boolean validPhone;

    /**
     * 账号有效是否有效  0 否 1 是
     */
    @TableId("is_valid_email")
    private Boolean validEmail;

    /**
     * 账号加密后的密码
     */
    private String secretKey;


    /**
     * 是否过期 0 否 1 是
     */
    @TableId("is_expired")
    private Boolean expired;

    /**
     * 是否锁定 0 否 1 是
     */
    @TableId("is_locked")
    private Boolean locked;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
