package com.keqi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keqi.system.domain.db.DictItemDO;

import java.util.List;

public interface DictItemMapper extends BaseMapper<DictItemDO> {
    List<DictItemDO> listByTypeCode(String typeCode);
}