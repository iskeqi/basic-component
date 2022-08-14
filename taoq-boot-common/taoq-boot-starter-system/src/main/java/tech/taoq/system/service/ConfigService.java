package tech.taoq.system.service;

import tech.taoq.mp.pojo.PageDto;
import tech.taoq.system.domain.db.ConfigDO;
import tech.taoq.system.domain.param.ConfigPageParam;

public interface ConfigService {

    void insert(ConfigDO param);

    void deleteById(String id);

    void updateById(ConfigDO param);

    ConfigDO getByConfigkey(String configKey);

    PageDto<ConfigDO> page(ConfigPageParam param);

    String getByConfigKey(String configKey);

    void updateByConfigKey(String configKey, String configValue);
}
