package cn.meshed.cloud.iam.rbac.web;

import cn.meshed.cloud.iam.domain.rbac.ability.PermissionAbility;
import cn.meshed.cloud.iam.rbac.PermissionAdapter;
import cn.meshed.cloud.iam.rbac.command.PermissionCmd;
import cn.meshed.cloud.iam.rbac.data.PermissionDTO;
import cn.meshed.cloud.iam.rbac.data.PermissionOptionDTO;
import cn.meshed.cloud.iam.rbac.query.PermissionBySelectQry;
import cn.meshed.cloud.iam.rbac.query.PermissionQry;
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
public class PermissionWebAdapter implements PermissionAdapter {

    private final PermissionAbility permissionAbility;


    /**
     * 分页列表
     *
     * @param permissionQry 参数
     * @return {@link MultiResponse < PermissionDTO >}
     */
    @Override
    public MultiResponse<PermissionDTO> list(PermissionQry permissionQry) {
        return permissionAbility.searchList(permissionQry);
    }

    /**
     * 权限选项
     *
     * @param permissionBySelectQry 选项参数
     * @return {@link MultiResponse< PermissionOptionDTO >}
     */
    @Override
    public MultiResponse<PermissionOptionDTO> select(PermissionBySelectQry permissionBySelectQry) {
        return permissionAbility.select(permissionBySelectQry);
    }

    /**
     * 权限详情
     *
     * @param id 权限编码
     * @return {@link SingleResponse <PermissionDTO>}
     */
    @Override
    public SingleResponse<PermissionDTO> details(Long id) {
        return permissionAbility.query(id);
    }

    /**
     * 保存权限
     *
     * @param permissionCmd 权限命令
     * @return {@link Response}
     */
    @Override
    public Response save(@Valid PermissionCmd permissionCmd) {
        return permissionAbility.save(permissionCmd);
    }

    /**
     * 删除权限
     *
     * @param id 权限编码
     * @return {@link Response}
     */
    @Override
    public Response delete(Long id) {
        return permissionAbility.delete(id);
    }
}
