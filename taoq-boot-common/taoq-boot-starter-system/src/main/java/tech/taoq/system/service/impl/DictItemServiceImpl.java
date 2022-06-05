package tech.taoq.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.ClientErrorException;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.DictItemDO;
import tech.taoq.system.domain.DictTypeDO;
import tech.taoq.system.mapper.DictItemMapper;
import tech.taoq.system.mapper.DictTypeMapper;
import tech.taoq.system.service.DictItemService;

import java.util.Comparator;
import java.util.List;

@Service
public class DictItemServiceImpl implements DictItemService {

    @Autowired
    private DictItemMapper dictItemMapper;
    @Autowired
    private DictTypeMapper dictTypeMapper;

    public void insert(DictItemDO dictItemDO) {
        DictItemDO t1 = dictItemMapper.selectOne(Wrappers.query(new DictItemDO()
                .setDictTypeId(dictItemDO.getDictTypeId()).setItemCode(dictItemDO.getItemCode())));
        if (t1 != null) {
            throw new ParamIllegalException("dictTypeId -> " + dictItemDO.getDictTypeId()
                    + " itemCode -> " + dictItemDO.getItemCode() + " 已存在");
        }

        dictItemMapper.insert(dictItemDO);
    }

    public void deleteById(String id) {
        DictItemDO dictItemDO = dictItemMapper.selectById(id);
        if (dictItemDO.getInternal()) {
            throw new ClientErrorException("内置记录不允许被删除");
        }
        dictItemMapper.deleteById(id);
    }

    public void updateById(DictItemDO param) {
        param.setDictTypeId(null);
        dictItemMapper.updateById(param);
    }

    public DictItemDO getById(String id) {
        return dictItemMapper.selectById(id);
    }

    public PageDto<DictItemDO> page(PageParam<DictItemDO> param) {
        Page<DictItemDO> page = dictItemMapper.selectPage(param.toPage(), Wrappers.lambdaQuery(DictItemDO.class)
                .orderByAsc((SFunction<DictItemDO, Integer>) DictItemDO::getOrderNum));
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

    public List<DictItemDO> listByDictTypeId(String dictTypeId) {
        List<DictItemDO> dictItemDOList = dictItemMapper.selectList(Wrappers.query(new DictItemDO()
                .setDictTypeId(dictTypeId)));
        dictItemDOList.sort(Comparator.comparing(DictItemDO::getOrderNum));
        return dictItemDOList;
    }

    @Override
    public boolean existItemCode(String dictType, String itemCode) {
        DictTypeDO dictTypeDO = dictTypeMapper.selectOne(Wrappers.query(new DictTypeDO().setType(dictType)));
        if (dictTypeDO == null) {
            return false;
        }
        DictItemDO t = dictItemMapper.selectOne(Wrappers.query(new DictItemDO()
                .setDictTypeId(dictTypeDO.getId()).setItemCode(itemCode)));
        return t != null;
    }

}
