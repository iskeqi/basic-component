package com.keqi.system.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.keqi.system.domain.db.DictItemDO;
import com.keqi.system.mapper.DictItemMapper;
import com.keqi.web.validator.BaseDictValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictItemService implements BaseDictValidate {

    @Autowired
    private DictItemMapper dictItemMapper;

    public DictItemDO insert(DictItemDO dictItemDO) {
        dictItemMapper.insert(dictItemDO);
        return dictItemDO;
    }

    public List<DictItemDO> listByTypeCode(String typeCode) {
        return dictItemMapper.listByTypeCode(typeCode);
    }

    @Override
    public boolean existItemCode(String typeCode, String itemCode) {
        DictItemDO param = new DictItemDO();
        param.setTypeCode(typeCode);
        param.setItemCode(itemCode);

        DictItemDO t = dictItemMapper.selectOne(Wrappers.lambdaQuery(param));

        return t != null;
    }

    public void delete(String typeCode, String itemCode) {
        DictItemDO t = new DictItemDO().setTypeCode(typeCode);
        if (itemCode != null) {
            t.setItemCode(itemCode);
        }

        dictItemMapper.delete(Wrappers.query(t));
    }
}
