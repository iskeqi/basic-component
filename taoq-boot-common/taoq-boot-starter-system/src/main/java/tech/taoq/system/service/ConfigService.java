package tech.taoq.system.service;

import tech.taoq.mp.pojo.PageDto;
import tech.taoq.system.domain.db.ConfigDO;
import tech.taoq.system.domain.param.ConfigPageParam;

public interface ConfigService {

    void insert(ConfigDO param);

    void deleteById(String id);

    void updateById(ConfigDO param);

    PageDto<ConfigDO> page(ConfigPageParam param);

    String getByConfigKey(String configKey);

    ConfigDO getById(String id);

    void updateByConfigKey(String configKey, String configValue);
}
