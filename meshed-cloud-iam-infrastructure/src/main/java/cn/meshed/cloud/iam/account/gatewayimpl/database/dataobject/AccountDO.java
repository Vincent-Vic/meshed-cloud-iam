package cn.meshed.cloud.iam.account.gatewayimpl.database.dataobject;

import cn.meshed.cloud.entity.BaseEntity;
import cn.meshed.cloud.iam.account.enums.AccountStatusEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@TableName("m_account")
public class AccountDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
     * 账号手机号是否有效 0 否 1 是
     */
    @TableField("is_valid_phone")
    private Boolean validPhone;

    /**
     * 账号有效是否有效  0 否 1 是
     */
    @TableField("is_valid_email")
    private Boolean validEmail;

    /**
     * 账号加密后的密码
     */
    private String secretKey;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private AccountStatusEnum status;

}
