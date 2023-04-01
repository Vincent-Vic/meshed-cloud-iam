package cn.meshed.cloud.iam.account.gatewayimpl;

import cn.meshed.cloud.iam.account.enums.SystemStatusEnum;
import cn.meshed.cloud.iam.account.gatewayimpl.database.dataobject.SystemDO;
import cn.meshed.cloud.iam.account.gatewayimpl.database.mapper.SystemMapper;
import cn.meshed.cloud.iam.account.query.SystemPageQry;
import cn.meshed.cloud.iam.account.query.SystemSelectQry;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.gateway.SystemGateway;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.CopyUtils;
import cn.meshed.cloud.utils.PageUtils;
import com.alibaba.cola.dto.PageResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class SystemGatewayImpl implements SystemGateway {

    private final SystemMapper systemMapper;

    /**
     * <h1>删除系统</h1>
     *
     * @param systemId 系统编码
     * @return {@link Boolean}
     */
    @Override
    public Boolean delete(Integer systemId) {
        return systemMapper.deleteById(systemId) > 0;
    }

    /**
     * 查询
     *
     * @param systemId 系统编码
     * @return {@link System}
     */
    @Override
    public System query(Integer systemId) {
        return CopyUtils.copy(systemMapper.selectById(systemId), System.class);
    }

    /**
     * <h1>保存对象</h1>
     *
     * @param system 系统对象
     * @return {@link Boolean}
     */
    @Override
    public Boolean save(System system) {
        SystemDO systemDO = CopyUtils.copy(system, SystemDO.class);
        return systemMapper.insert(systemDO) > 0;
    }

    /**
     * <h1>搜索列表</h1>
     *
     * @param pageQry 分页参数
     * @return {@link PageResponse<System>}
     */
    @Override
    public PageResponse<System> searchList(SystemPageQry pageQry) {
        Page<Object> page = PageUtils.startPage(pageQry.getPageIndex(), pageQry.getPageSize());
        LambdaQueryWrapper<SystemDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(pageQry.getStatus() != null, SystemDO::getStatus, pageQry.getStatus());
        lqw.and(lambdaQueryWrapper -> {
            lambdaQueryWrapper.or(StringUtils.isNotBlank(pageQry.getKeyword()))
                    .like(StringUtils.isNotBlank(pageQry.getKeyword()), SystemDO::getKey, pageQry.getKeyword());
            lambdaQueryWrapper.or()
                    .like(StringUtils.isNotBlank(pageQry.getKeyword()), SystemDO::getName, pageQry.getKeyword());
        });

        List<SystemDO> list = systemMapper.selectList(lqw);
        return PageUtils.of(list, page, System::new);
    }

    /**
     * 判断key是否已经存在
     *
     * @param key 表单唯一标识
     * @return {@link Boolean}
     */
    @Override
    public Boolean existFormKey(String key) {
        AssertUtils.isTrue(StringUtils.isNotBlank(key), "唯一标识不能为空");
        LambdaQueryWrapper<SystemDO> lqw = new LambdaQueryWrapper<>();
        lqw.select(SystemDO::getId).eq(SystemDO::getKey, key);
        return systemMapper.selectCount(lqw) > 0;
    }

    /**
     * 系统根据唯一标识查询
     *
     * @param key 唯一标识
     * @return {@link java.lang.System}
     */
    @Override
    public System getSystemByKey(String key) {
        AssertUtils.isTrue(StringUtils.isNotBlank(key), "唯一标识不能为空");
        LambdaQueryWrapper<SystemDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemDO::getKey, key);
        return CopyUtils.copy(systemMapper.selectOne(lqw), System.class);
    }

    /**
     * 系统根据客户端ID查询
     *
     * @param clientId 客户端ID
     * @return {@link System}
     */
    @Override
    public System getSystemByClientId(String clientId) {
        AssertUtils.isTrue(StringUtils.isNotBlank(clientId), "唯一标识不能为空");
        LambdaQueryWrapper<SystemDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemDO::getClientId, clientId);
        return CopyUtils.copy(systemMapper.selectOne(lqw), System.class);
    }

    /**
     * <h1>选项查询</h1>
     *
     * @param systemSelectQry 选项参数
     * @return {@link List<System>}
     */
    @Override
    public List<System> select(SystemSelectQry systemSelectQry) {
        LambdaQueryWrapper<SystemDO> lqw = new LambdaQueryWrapper<>();
        lqw.select(SystemDO::getId, SystemDO::getKey, SystemDO::getName);
        lqw.eq(SystemDO::getStatus, SystemStatusEnum.RUN)
                .eq(systemSelectQry.getType() != null, SystemDO::getType, systemSelectQry.getType());

        return CopyUtils.copyListProperties(systemMapper.selectList(lqw), System.class);
    }
}
