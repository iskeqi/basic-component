package tech.taoq.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.system.domain.db.ConfigDO;

public interface ConfigService {

    void insert(ConfigDO param);

    void deleteById(String id);

    void updateById(ConfigDO param);

    ConfigDO getById(String id);

    PageDto<ConfigDO> page(Page<ConfigDO> param);
}
