package tech.taoq.system.service;

import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.db.DictTypeDO;

public interface DictTypeService {

    void insert(DictTypeDO param);

    void deleteById(String id);

    void updateById(DictTypeDO param);

    DictTypeDO getById(String id);

    PageDto<DictTypeDO> page(PageParam<DictTypeDO> param);
}
