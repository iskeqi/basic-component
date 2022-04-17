package tech.taoq.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.system.domain.db.DictItemDO;
import tech.taoq.system.mapper.DictItemMapper;
import tech.taoq.system.service.DictItemService;

import java.util.Comparator;
import java.util.List;

@Service
public class DictItemServiceImpl implements DictItemService {

    @Autowired
    private DictItemMapper dictItemMapper;

    public void insert(DictItemDO dictItemDO) {
        DictItemDO t1 = dictItemMapper.selectOne(Wrappers.query(new DictItemDO().setDictType(dictItemDO.getDictType()).setItemCode(dictItemDO.getItemCode())));
        if (t1 != null) {
            throw new ParamIllegalException("dictType -> " + dictItemDO.getDictType() + " itemCode -> " + dictItemDO.getItemCode() + " 已存在");
        }

        dictItemMapper.insert(dictItemDO);
    }

    public void deleteById(String id) {
        dictItemMapper.deleteById(id);
    }

    public void updateById(DictItemDO param) {
        param.setDictType(null);
        dictItemMapper.updateById(param);
    }

    public DictItemDO getById(String id) {
        return dictItemMapper.selectById(id);
    }

    public PageDto<DictItemDO> page(Page<DictItemDO> param) {
        Page<DictItemDO> page = dictItemMapper.selectPage(param, Wrappers.lambdaQuery(DictItemDO.class)
                .orderByAsc((SFunction<DictItemDO, Integer>) DictItemDO::getOrderNum));
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

    public List<DictItemDO> listByDictType(String dictType) {
        List<DictItemDO> dictItemDOList = dictItemMapper.selectList(Wrappers.query(new DictItemDO().setDictType(dictType)));
        dictItemDOList.sort(Comparator.comparing(DictItemDO::getOrderNum));
        return dictItemDOList;
    }

    @Override
    public boolean existItemCode(String dictType, String itemCode) {
        DictItemDO t = dictItemMapper.selectOne(
                Wrappers.query(new DictItemDO().setDictType(dictType).setItemCode(itemCode)));
        return t != null;
    }

}
