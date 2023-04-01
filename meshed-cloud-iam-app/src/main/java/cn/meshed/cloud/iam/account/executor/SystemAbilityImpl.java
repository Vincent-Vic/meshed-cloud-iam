package cn.meshed.cloud.iam.account.executor;

import cn.meshed.cloud.iam.account.command.SystemCmd;
import cn.meshed.cloud.iam.account.data.SystemDTO;
import cn.meshed.cloud.iam.account.executor.command.SystemCmdExe;
import cn.meshed.cloud.iam.account.executor.command.SystemDelExe;
import cn.meshed.cloud.iam.account.executor.query.SystemAvailableKeyQryExe;
import cn.meshed.cloud.iam.account.executor.query.SystemByClientIdQryExe;
import cn.meshed.cloud.iam.account.executor.query.SystemByIdQryExe;
import cn.meshed.cloud.iam.account.executor.query.SystemByKeyQryExe;
import cn.meshed.cloud.iam.account.executor.query.SystemPageQryExe;
import cn.meshed.cloud.iam.account.executor.query.SystemSelectQryExe;
import cn.meshed.cloud.iam.account.query.SystemPageQry;
import cn.meshed.cloud.iam.account.query.SystemSelectQry;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.ability.SystemAbility;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SystemAbilityImpl implements SystemAbility {

    private final SystemByClientIdQryExe systemByClientIdQryExe;
    private final SystemByKeyQryExe systemByKeyQryExe;
    private final SystemAvailableKeyQryExe systemAvailableKeyQryExe;
    private final SystemByIdQryExe systemByIdQryExe;
    private final SystemPageQryExe systemPageQryExe;
    private final SystemSelectQryExe systemSelectQryExe;
    private final SystemCmdExe systemCmdExe;
    private final SystemDelExe systemDelExe;

    /**
     * 检查系统标识是否可用
     *
     * @param key 系统标识
     * @return {@link Boolean}
     */
    @Override
    public Response availableKey(String key) {
        return systemAvailableKeyQryExe.execute(key);
    }

    /**
     * 系统根据唯一标识查询
     *
     * @param key 唯一标识
     * @return {@link SingleResponse<SystemDTO>}
     */
    @Override
    public SingleResponse<System> getSystemByKey(String key) {
        return systemByKeyQryExe.execute(key);
    }

    /**
     * 系统根据客户端ID查询
     *
     * @param clientId 客户端ID
     * @return {@link SingleResponse<SystemDTO>}
     */
    @Override
    public SingleResponse<System> getSystemByClientId(String clientId) {
        return systemByClientIdQryExe.execute(clientId);
    }

    /**
     * <h1>删除对象</h1>
     *
     * @param systemId 系统编码
     * @return {@link Response}
     */
    @Override
    public Response delete(Integer systemId) {
        return systemDelExe.execute(systemId);
    }

    /**
     * 查询
     *
     * @param systemId 系统编码
     * @return {@link SingleResponse<SystemDTO>}
     */
    @Override
    public SingleResponse<SystemDTO> query(Integer systemId) {
        return systemByIdQryExe.execute(systemId);
    }

    /**
     * <h1>保存对象</h1>
     *
     * @param systemCmd 系统参数
     * @return {@link Response}
     */
    @Override
    public Response save(SystemCmd systemCmd) {
        return systemCmdExe.execute(systemCmd);
    }

    /**
     * <h1>搜索列表</h1>
     *
     * @param pageQry 分页参数
     * @return {@link PageResponse<SystemDTO>}
     */
    @Override
    public PageResponse<SystemDTO> searchList(SystemPageQry pageQry) {
        return systemPageQryExe.execute(pageQry);
    }

    /**
     * <h1>选项查询</h1>
     *
     * @param systemSelectQry 选项参数
     * @return {@link MultiResponse<SystemDTO>}
     */
    @Override
    public MultiResponse<SystemDTO> select(SystemSelectQry systemSelectQry) {
        return systemSelectQryExe.execute(systemSelectQry);
    }
}
