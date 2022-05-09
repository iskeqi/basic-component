package tech.taoq.log.service;

import cn.monitor4all.logRecord.bean.LogDTO;
import cn.monitor4all.logRecord.service.IOperationLogGetService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.taoq.log.domain.OperateLogDO;
import tech.taoq.log.mapper.OperateLogMapper;

/**
 * OperationLogGetService
 *
 * @author keqi
 */
@Slf4j
@Component
public class OperationLogGetService implements IOperationLogGetService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public void createLog(LogDTO logDTO) {
        log.info("logDTO: [{}]", JSON.toJSONString(logDTO));

        OperateLogDO t = new OperateLogDO();
        BeanUtils.copyProperties(logDTO, t);
        operateLogMapper.insert(t);
    }
}
