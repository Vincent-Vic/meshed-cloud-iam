package cn.meshed.cloud.iam.domain.account.gateway;

import cn.meshed.cloud.core.IDelete;
import cn.meshed.cloud.core.IQuery;
import cn.meshed.cloud.core.ISave;
import cn.meshed.cloud.core.ISearchList;
import cn.meshed.cloud.core.ISelect;
import cn.meshed.cloud.iam.account.query.SystemPageQry;
import cn.meshed.cloud.iam.account.query.SystemSelectQry;
import cn.meshed.cloud.iam.domain.account.System;
import com.alibaba.cola.dto.PageResponse;

import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface SystemGateway extends ISearchList<SystemPageQry, PageResponse<System>>, ISave<System, Boolean>,
        IQuery<Integer, System>, IDelete<Integer, Boolean>, ISelect<SystemSelectQry, List<System>> {

    /**
     * 判断系统标识是否已经存在
     *
     * @param key 系统标识
     * @return {@link Boolean}
     */
    Boolean existFormKey(String key);

    /**
     * 系统根据唯一标识查询
     *
     * @param key 唯一标识
     * @return {@link System}
     */
    System getSystemByKey(String key);

    /**
     * 系统根据客户端ID查询
     *
     * @param clientId 客户端ID
     * @return {@link System}
     */
    System getSystemByClientId(String clientId);
}
