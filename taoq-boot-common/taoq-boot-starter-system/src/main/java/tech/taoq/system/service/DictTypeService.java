package tech.taoq.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.system.domain.DictTypeDO;

public interface DictTypeService {

    void insert(DictTypeDO param);

    void deleteById(String id);

    void updateById(DictTypeDO param);

    DictTypeDO getById(String id);

    PageDto<DictTypeDO> page(Page<DictTypeDO> param);
}
