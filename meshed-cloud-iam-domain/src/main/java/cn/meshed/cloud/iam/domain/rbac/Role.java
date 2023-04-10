package cn.meshed.cloud.iam.domain.rbac;

import cn.meshed.cloud.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author by Vincent Vic
 * @since 2022-10-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父角色
     */
    private Long parentId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 权限授权码
     */
    private String access;

    /**
     * 所属系统
     */
    private Integer ownerId;

    /**
     * 状态
     */
    private Status status;

    /**
     * 备注
     */
    private String description;


}
