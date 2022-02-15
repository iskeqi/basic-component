package tech.taoq.system.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.common.pojo.enums.DisableEnum;
import tech.taoq.system.domain.db.ConfigDO;
import tech.taoq.system.mapper.ConfigMapper;

@Service
//@CacheConfig(cacheNames = "sys:config")
public class ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    public void insert(ConfigDO param) {
        ConfigDO t = configMapper.selectOne(Wrappers.query(new ConfigDO()
                .setConfigKey(param.getConfigKey())));
        if (t != null) {
            throw new ParamIllegalException("configKey：" + param.getConfigKey() + " 已经存在");
        }

        configMapper.insert(param);
    }

//    @CacheEvict(key = "#configKey")
    public void deleteByConfigKey(String configKey) {
        configMapper.delete(Wrappers.query(new ConfigDO().setConfigKey(configKey)));
    }

//    @CacheEvict(key = "#param.configKey")
    public void updateByConfigKey(ConfigDO param) {
        ConfigDO t1 = BeanUtil.copyProperties(param, ConfigDO.class);
        // configKey 是不能修改的
        t1.setConfigKey(null);
        configMapper.update(t1, Wrappers.query(new ConfigDO().setConfigKey(param.getConfigKey())));
    }

//    @Cacheable(key = "#configKey")
    public ConfigDO getByConfigKey(String configKey) {
        // 只返回启用状态的配置，已经禁用的 configKey 是不会查询出来的
        return configMapper.selectOne(Wrappers.query(new ConfigDO()
                .setConfigKey(configKey).setDisable(DisableEnum.ENABLE.getCode())));
    }

    public PageDto<ConfigDO> page(Page<ConfigDO> param) {
        Page<ConfigDO> page = configMapper.selectPage(param, Wrappers.query());
        return new PageDto<>(page.getTotal(), page.getRecords());
    }
}
