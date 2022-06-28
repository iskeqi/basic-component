package tech.taoq.system.service;

import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.ConfigDO;

public interface ConfigService {

    void insert(ConfigDO param);

    void deleteById(String id);

    void updateById(ConfigDO param);

    ConfigDO getById(String id);

    PageDto<ConfigDO> page(PageParam<ConfigDO> param);

    String getByConfigKey(String configKey);

    void updateByConfigKey(String configKey, String configValue);
}
