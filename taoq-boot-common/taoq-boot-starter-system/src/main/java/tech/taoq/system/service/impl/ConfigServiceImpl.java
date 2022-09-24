package tech.taoq.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.system.domain.db.ConfigDO;
import tech.taoq.system.domain.param.ConfigPageParam;
import tech.taoq.system.mapper.ConfigMapper;
import tech.taoq.system.service.ConfigService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Lazy
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    public static final Map<String, ConfigDO> CONFIG_MAP = new HashMap<>();

    @Override
    public void insert(ConfigDO param) {
        Long count = configMapper.selectCount(Wrappers.query(new ConfigDO().setConfigKey(param.getConfigKey())));
        if (count > 0) {
            throw new ParamIllegalException("配置key: " + param.getConfigKey() + " 已经存在");
        }

        configMapper.insert(param);
    }

    @Override
    public void deleteById(String id) {
        ConfigDO t1 = configMapper.selectById(id);
        if (t1 == null) {
            throw new ParamIllegalException("配置ID " + id + " 不存在");
        }

        CONFIG_MAP.remove(t1.getConfigKey());

        ConfigDO t2 = new ConfigDO();
        t2.setId(t1.getId());
        t2.setDeleted(true);
        configMapper.updateById(t2);
    }

    @Override
    public void updateById(ConfigDO param) {
        ConfigDO t3 = configMapper.selectById(param.getId());
        if (t3 == null) {
            throw new ParamIllegalException("配置ID: " + param.getId() + " 不存在");
        }

        ConfigDO t1 = configMapper.selectOne(Wrappers.query(new ConfigDO().setConfigKey(param.getConfigKey())));
        if (t1 !=null && !Objects.equals(param.getId(), t1.getId())) {
            throw new ParamIllegalException("配置key: " + param.getConfigKey() + " 已经存在");
        }

        CONFIG_MAP.remove(param.getConfigKey());

        ConfigDO t2 = BeanUtil.copyProperties(param, ConfigDO.class);
        // configKey 是不能修改的
        t2.setConfigKey(null);
        t2.setCreateTime(null);
        configMapper.updateById(param);
    }

    @Override
    public PageDto<ConfigDO> page(ConfigPageParam param) {
        Page<ConfigDO> page = configMapper.selectPage(param.toPage(), Wrappers.lambdaQuery(ConfigDO.class)
                .eq(StringUtils.hasText(param.getId()), ConfigDO::getId, param.getId())
                .likeRight(StringUtils.hasText(param.getConfigKey()), ConfigDO::getConfigKey, param.getConfigKey())
                .likeRight(StringUtils.hasText(param.getCategoryName()), ConfigDO::getCategoryName, param.getCategoryName())
                .likeRight(StringUtils.hasText(param.getDisplayName()), ConfigDO::getDisplayName, param.getDisplayName())
                .eq(ConfigDO::getDeleted, false));

        return PageDto.build(page);
    }

    @Override
    public String getByConfigKey(String configKey) {
        ConfigDO t = CONFIG_MAP.get(configKey);
        if (t != null) {
            return t.getConfigValue();
        }

        ConfigDO configDO = configMapper.selectOne(Wrappers.query(new ConfigDO().setConfigKey(configKey).setDeleted(false)));
        if (configDO != null) {
            CONFIG_MAP.put(configKey, configDO);
            return configDO.getConfigValue();
        }

        return null;
    }

    @Override
    public ConfigDO getById(String id) {
        ConfigDO t1 = configMapper.selectById(id);
        if (t1 == null || t1.getDeleted()) {
            return null;
        }

        return t1;
    }

    @Override
    public void updateByConfigKey(String configKey, String configValue) {
        String oldValue = getByConfigKey(configKey);
        if (oldValue == null) {
            throw new ParamIllegalException("配置key: " + configKey + " 不存在");
        }

        ConfigDO t1 = CONFIG_MAP.get(configKey);
        t1.setConfigValue(configValue);

        updateById(t1);
    }
}
