package cn.meshed.cloud.iam.domain.dto.cmd;

import cn.meshed.base.cqrs.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

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
public class PermissionCmd implements Command {

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
     * 权限英文名称
     */
    private String enname;

    /**
     * 授权路径
     */
    private String uri;

    /**
     * 备注
     */
    private String description;


    /**
     * @return
     */
    @Override
    public Boolean verifySelf() {
        //如果没有传就是第一层权限
        if (parentId == null){
            parentId = 0L;
        }
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(enname) && StringUtils.isNotBlank(uri);
    }
}
