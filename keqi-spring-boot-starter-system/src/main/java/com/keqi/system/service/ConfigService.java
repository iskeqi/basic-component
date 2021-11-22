package com.keqi.system.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keqi.common.exception.client.ParamIllegalException;
import com.keqi.common.pojo.PageDto;
import com.keqi.system.domain.db.ConfigDO;
import com.keqi.system.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "config")
public class ConfigService {

    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private ConfigService configService;

    public void insert(ConfigDO param) {
        ConfigDO t = configService.getByConfigKey(param.getConfigKey());
        if (t != null) {
            throw new ParamIllegalException("configKey：" + param.getConfigKey() + " 已经存在");
        }

        configMapper.insert(param);
    }

    @CacheEvict(key = "configKey")
    public void deleteByConfigKey(String configKey) {
        configMapper.delete(Wrappers.query(new ConfigDO().setConfigKey(configKey)));
    }

    @CacheEvict(key = "param.configKey")
    public void updateByConfigKey(ConfigDO param) {
        ConfigDO t1 = configService.getByConfigKey(param.getConfigKey());
        if (t1 == null) {
            throw new ParamIllegalException("configKey：" + param.getConfigKey() + " 不存在");
        }

        ConfigDO t2 = BeanUtil.copyProperties(param, ConfigDO.class);
        // configKey 是不能修改的
        t2.setConfigKey(null);
        configMapper.update(t2, Wrappers.query(new ConfigDO().setConfigKey(param.getConfigKey())));
    }

    @Cacheable(key = "#configKey")
    public ConfigDO getByConfigKey(String configKey) {
        return configMapper.selectOne(Wrappers.query(new ConfigDO().setConfigKey(configKey)));
    }

    public PageDto<ConfigDO> page(Page<ConfigDO> param) {
        Page<ConfigDO> page = configMapper.selectPage(param, Wrappers.query());
        return new PageDto<>(page.getTotal(), page.getRecords());
    }
}
