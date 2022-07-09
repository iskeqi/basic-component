package tech.taoq.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.ClientErrorException;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.db.ConfigDO;
import tech.taoq.system.domain.param.ConfigPageParam;
import tech.taoq.system.mapper.ConfigMapper;
import tech.taoq.system.service.ConfigService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    private static final Map<String, ConfigDO> CONFIG_MAP = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigDO> configDOS = configMapper.selectList(null);
        configDOS.forEach(configDO -> CONFIG_MAP.put(configDO.getConfigKey(), configDO));
    }

    public void insert(ConfigDO param) {
        ConfigDO t = configMapper.selectOne(Wrappers.query(new ConfigDO().setConfigKey(param.getConfigKey())));
        if (t != null) {
            throw new ParamIllegalException("configKey：" + param.getConfigKey() + " 已经存在");
        }

        configMapper.insert(param);
    }

    public void deleteById(String id) {
        ConfigDO configDO = configMapper.selectById(id);
        if (configDO.getInternal()) {
            throw new ClientErrorException("内置记录不允许被删除");
        }
        configMapper.deleteById(id);
        CONFIG_MAP.remove(configDO.getConfigKey());
    }

    public void updateById(ConfigDO param) {
        ConfigDO t1 = BeanUtil.copyProperties(param, ConfigDO.class);
        // configKey 是不能修改的
        t1.setConfigKey(null);
        t1.setCreateTime(null);
        configMapper.updateById(param);
        CONFIG_MAP.remove(t1.getConfigKey());
    }

    public ConfigDO getById(String id) {
        return configMapper.selectById(id);
    }

    public PageDto<ConfigDO> page(ConfigPageParam param) {
        ConfigDO configDO = BeanUtil.copyProperties(param, ConfigDO.class);
        Page<ConfigDO> page = configMapper.selectPage(param.toPage(), Wrappers.query(configDO));
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

    @Override
    public String getByConfigKey(String configKey) {
        ConfigDO t = CONFIG_MAP.get(configKey);
        if (t != null) {
            return t.getConfigValue();
        }

        ConfigDO configDO = configMapper.selectOne(Wrappers.query(new ConfigDO().setConfigKey(configKey)));
        if (configDO != null) {
            CONFIG_MAP.put(configKey, configDO);
            return configDO.getConfigValue();
        }
        return null;
    }

    @Override
    public void updateByConfigKey(String configKey, String configValue) {
        configMapper.update(new ConfigDO().setConfigValue(configValue), Wrappers.query(new ConfigDO().setConfigKey(configKey)));
        CONFIG_MAP.remove(configKey);
    }
}
