package com.keqi.system.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.keqi.common.exception.client.ParamIllegalException;
import com.keqi.system.domain.db.DictItemDO;
import com.keqi.system.mapper.DictItemMapper;
import com.keqi.web.validator.BaseDictValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DictItemService implements BaseDictValidate {

    @Autowired
    private DictItemMapper dictItemMapper;
    @Autowired
    private DictItemService dictItemService;

    public DictItemDO insert(DictItemDO dictItemDO) {
        DictItemDO t = dictItemService.getByTypeCodeAndItemCode(dictItemDO.getTypeCode(), dictItemDO.getItemCode());
        if (t != null) {
            throw new ParamIllegalException("configKey：" + param.getConfigKey() + " 已经存在");
        }

        dictItemMapper.insert(dictItemDO);
        return dictItemDO;
    }

    public void delete(String typeCode, String itemCode) {
        DictItemDO t = new DictItemDO().setTypeCode(typeCode);
        if (itemCode != null) {
            t.setItemCode(itemCode);
        }

        dictItemMapper.delete(Wrappers.query(t));
    }

    // @Cacheable(key = "#typeCode+'-'+#itemCode")
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
