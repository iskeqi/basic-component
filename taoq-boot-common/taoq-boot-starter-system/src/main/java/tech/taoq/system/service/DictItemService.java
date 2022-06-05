package tech.taoq.system.service;

import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.DictItemDO;
import tech.taoq.web.validator.BaseDictValidate;

import java.util.List;

public interface DictItemService extends BaseDictValidate {

    void insert(DictItemDO dictItemDO);

    void deleteById(String id);

    void updateById(DictItemDO param);

    DictItemDO getById(String id);

    PageDto<DictItemDO> page(PageParam<DictItemDO> param);

    List<DictItemDO> listByDictTypeId(String dictTypeId);
}
