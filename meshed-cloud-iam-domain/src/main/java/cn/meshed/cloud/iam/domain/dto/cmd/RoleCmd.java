package cn.meshed.cloud.iam.domain.dto.cmd;

import cn.meshed.base.cqrs.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

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
public class RoleCmd implements Command {

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
     * 角色英文名称
     */
    private String enname;


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
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(enname);
    }
}
