package tech.taoq.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.ClientErrorException;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;
import tech.taoq.system.domain.DictTypeDO;
import tech.taoq.system.mapper.DictTypeMapper;
import tech.taoq.system.service.DictTypeService;

@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    public void insert(DictTypeDO param) {
        DictTypeDO t = dictTypeMapper.selectOne(Wrappers.query(new DictTypeDO().setType(param.getType())));
        if (t != null) {
            throw new ParamIllegalException("type:" + param.getType() + " 已经存在");
        }

        dictTypeMapper.insert(param);
    }

    public void deleteById(String id) {
        DictTypeDO dictTypeDO = dictTypeMapper.selectById(id);
        if (dictTypeDO.getInternal()) {
            throw new ClientErrorException("内置记录不允许被删除");
        }
        dictTypeMapper.deleteById(id);
    }

    public void updateById(DictTypeDO param) {
        param.setType(null);
        dictTypeMapper.updateById(param);
    }

    public DictTypeDO getById(String id) {
        return dictTypeMapper.selectById(id);
    }

    public PageDto<DictTypeDO> page(PageParam<DictTypeDO> param) {
        Page<DictTypeDO> page = dictTypeMapper.selectPage(param.toPage(), Wrappers.query());
        return new PageDto<>(page.getTotal(), page.getRecords());
    }
}
