package cn.meshed.cloud.iam.account.web;

import cn.meshed.cloud.iam.account.SystemAdapter;
import cn.meshed.cloud.iam.account.command.SystemCmd;
import cn.meshed.cloud.iam.account.data.SystemDTO;
import cn.meshed.cloud.iam.account.query.SystemPageQry;
import cn.meshed.cloud.iam.account.query.SystemSelectQry;
import cn.meshed.cloud.iam.domain.account.ability.SystemAbility;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
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
public class SystemWebAdapter implements SystemAdapter {

    private final SystemAbility systemAbility;

    /**
     * 分页列表
     *
     * @param pageQry 分页参数
     * @return {@link PageResponse < AccountDTO >}
     */
    @Override
    public PageResponse<SystemDTO> list(SystemPageQry pageQry) {
        return systemAbility.searchList(pageQry);
    }

    /**
     * 保存
     *
     * @param systemCmd 新增操作
     * @return {@link Response}
     */
    @Override
    public Response save(@Valid SystemCmd systemCmd) {
        return systemAbility.save(systemCmd);
    }

    /**
     * 删除
     *
     * @param id 系统ID
     * @return {@link Response}
     */
    @Override
    public Response delete(Integer id) {
        return systemAbility.delete(id);
    }

    /**
     * 系统选项
     *
     * @param systemSelectQry 选项参数
     * @return {@link MultiResponse <SystemDTO>}
     */
    @Override
    public MultiResponse<SystemDTO> select(SystemSelectQry systemSelectQry) {
        return systemAbility.select(systemSelectQry);
    }

    /**
     * 检查系统标识是否可用
     *
     * @param key 系统标识
     * @return {@link Response}
     */
    @Override
    public Response availableKey(String key) {
        return systemAbility.availableKey(key);
    }
}
