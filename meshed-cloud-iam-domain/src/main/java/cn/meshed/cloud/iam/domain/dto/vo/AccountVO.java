package cn.meshed.cloud.iam.domain.dto.vo;

import cn.meshed.cloud.iam.domain.dto.enums.IAMStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class AccountVO implements Serializable {

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
    private Boolean validPhone;

    /**
     * 账号有效是否有效  0 否 1 是
     */
    private Boolean validEmail;


    /**
     * 是否过期 0 否 1 是
     */
    private Boolean expired;

    /**
     * 是否锁定 0 否 1 是
     */
    private Boolean locked;

    /**
     * 状态
     */
    private IAMStatus status;

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
