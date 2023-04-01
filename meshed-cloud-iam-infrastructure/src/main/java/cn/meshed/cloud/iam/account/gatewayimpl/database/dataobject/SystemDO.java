package cn.meshed.cloud.iam.account.gatewayimpl.database.dataobject;

import cn.meshed.cloud.entity.BaseEntity;
import cn.meshed.cloud.iam.account.enums.SystemStatusEnum;
import cn.meshed.cloud.iam.account.enums.SystemTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author by Vincent Vic
 * @since 2023-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_system")
public class SystemDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 系统ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 系统标识
     */
    @TableField("`key`")
    private String key;

    /**
     * 系统客户端ID
     */
    private String clientId;

    /**
     * 系统客户端密钥
     */
    private String clientSecret;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private SystemStatusEnum status;

    /**
     * 类型
     */
    private SystemTypeEnum type;

    /**
     * 允许URL
     */
    private String allowUrl;

    /**
     * 作用域
     */
    private String scope;


}
