package com.keqi.system.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.keqi.common.exception.client.ParamIllegalException;
import com.keqi.system.domain.db.DictItemDO;
import com.keqi.system.mapper.DictItemMapper;
import com.keqi.web.validator.BaseDictValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@CacheConfig(cacheNames = "dictItem")
public class DictItemService implements BaseDictValidate {

    @Autowired
    private DictItemMapper dictItemMapper;
    @Autowired
    private DictItemService dictItemService;

    public DictItemDO insert(DictItemDO dictItemDO) {
        DictItemDO t = dictItemService.getByTypeCodeAndItemCode(dictItemDO.getTypeCode(), dictItemDO.getItemCode());
        if (t != null) {
            throw new ParamIllegalException("typeCode -> " + dictItemDO.getTypeCode()
                    + " itemCode -> " + dictItemDO.getItemCode() + " 已存在");
        }

        dictItemMapper.insert(dictItemDO);
        return dictItemDO;
    }

    @CacheEvict(key = "#typeCode.concat(#itemCode)")
    public void delete(String typeCode, String itemCode) {
        DictItemDO t = new DictItemDO().setTypeCode(typeCode);
        if (itemCode != null) {
            t.setItemCode(itemCode);
        }

        dictItemMapper.delete(Wrappers.query(t));
    }

    @CacheEvict(key = "#param.typeCode.concat(#param.itemCode)")
    public void updateByTypeCodeAndItemCode(DictItemDO param) {
        DictItemDO t = BeanUtil.copyProperties(param, DictItemDO.class);
        t.setTypeCode(null);
        t.setItemCode(null);
        dictItemMapper.update(t, Wrappers.query(new DictItemDO()
                .setTypeCode(param.getTypeCode())
                .setItemCode(param.getItemCode())));
    }

    @Cacheable(key = "#typeCode.concat(#itemCode)")
    public DictItemDO getByTypeCodeAndItemCode(String typeCode, String itemCode) {
        DictItemDO param = new DictItemDO();
        param.setTypeCode(typeCode);
        param.setItemCode(itemCode);

        return dictItemMapper.selectOne(Wrappers.lambdaQuery(param));
    }

    public List<DictItemDO> listByTypeCode(String typeCode) {
        return dictItemMapper.listByTypeCode(typeCode);
    }

    @Override
    public boolean existItemCode(String typeCode, String itemCode) {
        return !Objects.isNull(dictItemService.getByTypeCodeAndItemCode(typeCode, itemCode));
    }


}
