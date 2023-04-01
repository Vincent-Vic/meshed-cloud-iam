package cn.meshed.cloud.iam.rbac.web;

import cn.meshed.cloud.iam.domain.rbac.ability.RoleAbility;
import cn.meshed.cloud.iam.rbac.RoleAdapter;
import cn.meshed.cloud.iam.rbac.command.RoleCmd;
import cn.meshed.cloud.iam.rbac.command.RoleGrantPermissionCmd;
import cn.meshed.cloud.iam.rbac.data.RoleDTO;
import cn.meshed.cloud.iam.rbac.data.RoleOptionDTO;
import cn.meshed.cloud.iam.rbac.query.RoleBySelectQry;
import cn.meshed.cloud.iam.rbac.query.RoleQry;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
public class RoleWebAdapter implements RoleAdapter {

    private final RoleAbility roleAbility;


    /**
     * 分页列表
     *
     * @param roleQry 参数
     * @return {@link MultiResponse <RoleDTO>}
     */
    @Override
    public MultiResponse<RoleDTO> list(RoleQry roleQry) {
        return roleAbility.searchList(roleQry);
    }

    /**
     * 角色详情
     *
     * @param id 角色编码
     * @return {@link SingleResponse<RoleDTO>}
     */
    @Override
    public SingleResponse<RoleDTO> details(Long id) {
        return roleAbility.query(id);
    }

    /**
     * 角色选项
     *
     * @param roleBySelectQry
     * @return {@link MultiResponse<RoleOptionDTO>}
     */
    @Override
    public MultiResponse<RoleOptionDTO> select(@Valid RoleBySelectQry roleBySelectQry) {
        return roleAbility.select(roleBySelectQry);
    }

    /**
     * 角色保存
     *
     * @param roleCmd
     * @return {@link Response}
     */
    @Override
    public Response save(@Valid RoleCmd roleCmd) {
        return roleAbility.save(roleCmd);
    }

    /**
     * 角色删除
     *
     * @param id 角色编码
     * @return {@link Response}
     */
    @Override
    public Response delete(Long id) {
        return roleAbility.delete(id);
    }

    /**
     * 权限编码列表
     *
     * @param id 角色编码
     * @return {@link MultiResponse<Long>}
     */
    @Override
    public MultiResponse<Long> getPermissionIds(Long id) {
        return roleAbility.getPermissionIds(id);
    }

    /**
     * 角色授权
     *
     * @param roleGrantPermissionCmd 角色授权操作
     * @return {@link Response}
     */
    @Override
    public Response grantRole(@Valid RoleGrantPermissionCmd roleGrantPermissionCmd) {
        return roleAbility.grantPermission(roleGrantPermissionCmd);
    }
}
