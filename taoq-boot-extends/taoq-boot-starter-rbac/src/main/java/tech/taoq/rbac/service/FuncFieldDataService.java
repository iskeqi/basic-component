package tech.taoq.rbac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.common.util.DateUtil;
import tech.taoq.rbac.domain.constant.RbacConstant;
import tech.taoq.rbac.domain.db.FuncFieldDO;
import tech.taoq.rbac.domain.db.MenuDO;
import tech.taoq.rbac.domain.param.FuncFieldDataPageParam;
import tech.taoq.rbac.domain.param.FuncFieldDataParam;
import tech.taoq.rbac.mapper.FuncFieldDataMapper;
import tech.taoq.rbac.mapper.MenuMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FuncFieldDataService {

    @Autowired
    private FuncFieldDataMapper funcFieldDataMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private FuncFieldService funcFieldService;

    @Transactional
    public void insert(List<FuncFieldDataParam> param) {
        if (param.size() == 0) {
            throw new ParamIllegalException("至少应包含一个字段");
        }
        MenuDO menuDO = menuMapper.selectById(param.get(0).getMenuId());
        String menuPermiss = menuDO.getPermiss();
        Map<String, String> dataMap = new HashMap<>(param.size());
        for (FuncFieldDataParam dataParam : param) {
            if (RbacConstant.ID.equals(dataParam.getCode())) {
            } else if (RbacConstant.CREATE_TIME.equals(dataParam.getCode())) {
                dataMap.put(dataParam.getCode(), LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(DateUtil.LOCAL_DATE_TIME)));
            } else if (RbacConstant.UPDATE_TIME.equals(dataParam.getCode())) {
                dataMap.put(dataParam.getCode(), LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(DateUtil.LOCAL_DATE_TIME)));
            } else
                dataMap.put(dataParam.getCode(), dataParam.getDataValue());
        }
        funcFieldDataMapper.insert(menuPermiss, dataMap);
    }

    @Transactional
    public void deleteById(String menuId, String id) {
        MenuDO menuDO = menuMapper.selectById(menuId);
        funcFieldDataMapper.deleteById(menuDO.getPermiss(), id);
    }

    @Transactional
    public void updateById(List<FuncFieldDataParam> param) {
        MenuDO menuDO = menuMapper.selectById(param.get(0).getMenuId());
        String menuPermiss = menuDO.getPermiss();
        Map<String, String> dataMap = new HashMap<>(param.size());
        for (FuncFieldDataParam dataParam : param) {
            if (RbacConstant.ID.equals(dataParam.getCode())) {
            } else if (RbacConstant.CREATE_TIME.equals(dataParam.getCode())) {
            } else if (RbacConstant.UPDATE_TIME.equals(dataParam.getCode())) {
                dataMap.put(dataParam.getCode(), LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(DateUtil.LOCAL_DATE_TIME)));
            } else
                dataMap.put(dataParam.getCode(), dataParam.getDataValue());
        }
        funcFieldDataMapper.updateById(param.get(0).getId(), menuPermiss, dataMap);
    }

    public PageDto<List<FuncFieldDataParam>> page(FuncFieldDataPageParam param) {
        MenuDO menuDO = menuMapper.selectById(param.getMenuId());
        param.setMenuPermiss(menuDO.getPermiss());

        List<FuncFieldDO> funcFieldDOList = funcFieldService.listByMenuId(param.getMenuId());
        Map<String, FuncFieldDO> funcFieldDOMap = new HashMap<>(funcFieldDOList.size());
        for (FuncFieldDO funcFieldDO : funcFieldDOList) {
            funcFieldDOMap.put(funcFieldDO.getCode(), funcFieldDO);
        }

        Long total = funcFieldDataMapper.count(param);
        PageDto<List<FuncFieldDataParam>> pageDto = new PageDto<>(total, null);

        if (total > 0) {
            param.setOffset(param.calculateOffset());
            List<Map<String, String>> page = funcFieldDataMapper.page(param);
            List<List<FuncFieldDataParam>> result = new ArrayList<>(page.size());

            for (Map<String, String> data : page) {
                List<FuncFieldDataParam> record = new ArrayList<>(data.size());
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    FuncFieldDO funcFieldDO = funcFieldDOMap.get(entry.getKey());

                    FuncFieldDataParam funcFieldDataParam = new FuncFieldDataParam();
                    funcFieldDataParam.setId(String.valueOf(data.get(RbacConstant.ID)));
                    funcFieldDataParam.setCreateTime(LocalDateTime.parse(data.get(RbacConstant.CREATE_TIME),
                            DateTimeFormatter.ofPattern(DateUtil.LOCAL_DATE_TIME)));
                    funcFieldDataParam.setUpdateTime(LocalDateTime.parse(data.get(RbacConstant.UPDATE_TIME),
                            DateTimeFormatter.ofPattern(DateUtil.LOCAL_DATE_TIME)));
                    funcFieldDataParam.setName(funcFieldDO.getName());
                    funcFieldDataParam.setCode(entry.getKey());
                    funcFieldDataParam.setMenuId(funcFieldDO.getMenuId());
                    funcFieldDataParam.setType(funcFieldDO.getType());
//                    funcFieldDataParam.setDictTypeId(funcFieldDO.getDictTypeId());
                    funcFieldDataParam.setOrderNum(funcFieldDO.getOrderNum());
//                    funcFieldDataParam.setDisable(funcFieldDO.getDisable());
                    funcFieldDataParam.setDataValue(String.valueOf(entry.getValue()));

                    record.add(funcFieldDataParam);
                }
                record.sort(Comparator.comparing(FuncFieldDataParam::getOrderNum));
                result.add(record);
            }

            pageDto.setRecords(result);
        }

        return pageDto;
    }
}
