package cn.meshed.cloud.iam.domain.account.ability;

import cn.meshed.cloud.core.IDelete;
import cn.meshed.cloud.core.IQuery;
import cn.meshed.cloud.core.ISave;
import cn.meshed.cloud.core.ISearchList;
import cn.meshed.cloud.core.ISelect;
import cn.meshed.cloud.iam.account.command.SystemCmd;
import cn.meshed.cloud.iam.account.data.SystemDTO;
import cn.meshed.cloud.iam.account.query.SystemPageQry;
import cn.meshed.cloud.iam.account.query.SystemSelectQry;
import cn.meshed.cloud.iam.domain.account.System;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;


/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface SystemAbility extends ISearchList<SystemPageQry, PageResponse<SystemDTO>>, ISave<SystemCmd, Response>,
        IQuery<Integer, SingleResponse<SystemDTO>>, IDelete<Integer, Response>,
        ISelect<SystemSelectQry, MultiResponse<SystemDTO>> {

    /**
     * 检查系统标识是否可用
     *
     * @param key 系统标识
     * @return {@link Boolean}
     */
    Response availableKey(String key);

    /**
     * 系统根据唯一标识查询
     *
     * @param key 唯一标识
     * @return {@link System}
     */
    SingleResponse<System> getSystemByKey(String key);

    /**
     * 系统根据客户端ID查询
     *
     * @param clientId 客户端ID
     * @return {@link System}
     */
    SingleResponse<System> getSystemByClientId(String clientId);
}
