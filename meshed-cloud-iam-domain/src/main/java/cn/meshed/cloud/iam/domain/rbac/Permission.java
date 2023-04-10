package cn.meshed.cloud.iam.domain.rbac;

import cn.meshed.cloud.constant.Status;
import cn.meshed.cloud.iam.rbac.enums.AccessModeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author by Vincent Vic
 * @since 2022-10-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父权限
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限所属系统
     */
    private Integer ownerId;

    /**
     * 授权路径
     */
    private String uri;

    /**
     * 授权模式
     */
    private AccessModeEnum accessMode;

    /**
     * 权限授权码
     */
    private String access;

    /**
     * 状态
     */
    private Status status;

    /**
     * 备注
     */
    private String description;


}
