package tech.taoq.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.common.pojo.PageDto;
import tech.taoq.common.pojo.enums.DisableEnum;
import tech.taoq.system.domain.db.DictItemDO;
import tech.taoq.system.mapper.DictItemMapper;
import tech.taoq.web.validator.BaseDictValidate;

import java.util.List;
import java.util.Objects;

@Service
//@CacheConfig(cacheNames = "sys:dictItem")
public class DictItemService implements BaseDictValidate {

    @Autowired
    private DictItemMapper dictItemMapper;
    @Autowired
    private DictItemService dictItemService;

    public DictItemDO insert(DictItemDO dictItemDO) {
        DictItemDO t1 = dictItemMapper.selectOne(Wrappers.query(new DictItemDO()
                .setTypeCode(dictItemDO.getTypeCode())
                .setItemCode(dictItemDO.getItemCode())));
        if (t1 != null) {
            throw new ParamIllegalException("typeCode -> " + dictItemDO.getTypeCode()
                    + " itemCode -> " + dictItemDO.getItemCode() + " 已存在");
        }

        List<DictItemDO> list = dictItemMapper.selectList(Wrappers.query(new DictItemDO()
                .setTypeCode(dictItemDO.getTypeCode())));
        if (list.size() == 0) {
            // 当前 typeCode 的第一次新增，必须填写 typeName
            if (StrUtil.isBlank(dictItemDO.getTypeName())) {
                throw new ParamIllegalException("typeName 为必填项");
            }
        } else {
            dictItemDO.setTypeName(list.get(0).getTypeName());
        }

        dictItemMapper.insert(dictItemDO);
        return dictItemDO;
    }

//    @CacheEvict(key = "#typeCode.concat(#itemCode)")
    public void delete(String typeCode, String itemCode) {
        DictItemDO t = new DictItemDO().setTypeCode(typeCode);
        if (itemCode != null) {
            t.setItemCode(itemCode);
        }

        dictItemMapper.delete(Wrappers.query(t));
    }

//    @CacheEvict(key = "#param.typeCode.concat(#param.itemCode)")
    public void updateByTypeCodeAndItemCode(DictItemDO param) {
        DictItemDO t1 = dictItemMapper.selectOne(Wrappers.query(new DictItemDO()
                .setTypeCode(param.getTypeCode())
                .setItemCode(param.getItemCode())));
        if (t1 == null) {
            throw new ParamIllegalException("不存在对应的字典项");
        }

        DictItemDO t2 = BeanUtil.copyProperties(param, DictItemDO.class);
        // typeCode 和 itemCode 不能修改
        t2.setTypeCode(null);
        t2.setItemCode(null);
        dictItemMapper.update(t2, Wrappers.query(new DictItemDO()
                .setTypeCode(param.getTypeCode())
                .setItemCode(param.getItemCode())));
    }

    public PageDto<DictItemDO> page(Page<DictItemDO> param) {
        Page<DictItemDO> page = dictItemMapper.selectPage(param, Wrappers.lambdaQuery(DictItemDO.class)
                .orderByAsc((SFunction<DictItemDO, Integer>) DictItemDO::getOrderNum));
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

//    @Cacheable(key = "#typeCode.concat(#itemCode)")
    public DictItemDO getByTypeCodeAndItemCode(String typeCode, String itemCode) {
        // 只返回启用状态的字典项
        return dictItemMapper.selectOne(Wrappers.query(new DictItemDO()
                .setTypeCode(typeCode)
                .setItemCode(itemCode)
                .setDisable(DisableEnum.ENABLE.getCode())));
    }

    public List<DictItemDO> listByTypeCode(String typeCode) {
        // 只返回启用状态的字典项
        return dictItemMapper.selectList(Wrappers.lambdaQuery(DictItemDO.class)
                .setEntity(new DictItemDO().setTypeCode(typeCode).setDisable(DisableEnum.ENABLE.getCode()))
                .orderByAsc((SFunction<DictItemDO, Integer>) DictItemDO::getOrderNum));
    }

    @Override
    public boolean existItemCode(String typeCode, String itemCode) {
        return !Objects.isNull(dictItemService.getByTypeCodeAndItemCode(typeCode, itemCode));
    }

    public void updateTypeNameByTypeCode(String typeCode, String typeName) {
        List<DictItemDO> dictItemDOList =
                dictItemMapper.selectList(Wrappers.query(new DictItemDO().setTypeCode(typeCode)));
        for (DictItemDO dictItemDO : dictItemDOList) {
            DictItemDO t = new DictItemDO();
            t.setTypeCode(dictItemDO.getTypeCode());
            t.setItemCode(dictItemDO.getItemCode());
            t.setTypeName(typeName);
            dictItemService.updateByTypeCodeAndItemCode(t);
        }
    }
}
