package tech.taoq.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.system.domain.DictItemDO;
import tech.taoq.web.validator.BaseDictValidate;

import java.util.List;

public interface DictItemService extends BaseDictValidate {

    void insert(DictItemDO dictItemDO);

    void deleteById(String id);

    void updateById(DictItemDO param);

    DictItemDO getById(String id);

    PageDto<DictItemDO> page(Page<DictItemDO> param);

    List<DictItemDO> listByDictTypeId(String dictTypeId);
}
