package cn.meshed.cloud.iam.domain.account;

import cn.meshed.cloud.iam.account.enums.SystemStatusEnum;
import cn.meshed.cloud.iam.account.enums.SystemTypeEnum;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.IdUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * <h1>系统</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class System {

    private static final long serialVersionUID = 1L;

    /**
     * 系统ID
     */
    private Integer id;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 系统标识
     */
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

    public System() {
        this.status = SystemStatusEnum.RUN;
        this.allowUrl = "*";
        this.scope = "userinfo";
        this.clientId = IdUtils.simpleUUID();
        this.clientSecret = IdUtils.simpleUUID();
    }

    public System(String name, String key, SystemTypeEnum type, String description) {
        this();
        AssertUtils.isTrue(StringUtils.isNotBlank(name), "系统名称不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(key), "系统标识不能为空");
        this.name = name;
        this.key = key.toUpperCase();
        this.description = description;
        this.type = type;
    }

    public System(String name, String key, SystemTypeEnum type) {
        this(name, key, type, name);
    }

    public System(String name, String key) {
        this(name, key, SystemTypeEnum.SERVICE, name);
    }

    public System(String name, String key, String description) {
        this(name, key, SystemTypeEnum.SERVICE, description);
    }


}
