package cn.meshed.cloud.iam.rbac.gatewayimpl.database.dataobject;

import cn.meshed.cloud.constant.Status;
import cn.meshed.cloud.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("m_permission")
public class PermissionDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
     * 权限英文名称
     */
    private String enname;


    /**
     * 权限英文名称
     */
    private String access;

    /**
     * 授权路径
     */
    private String uri;

    /**
     * 状态
     */
    private Status status;

    /**
     * 备注
     */
    private String description;


}
