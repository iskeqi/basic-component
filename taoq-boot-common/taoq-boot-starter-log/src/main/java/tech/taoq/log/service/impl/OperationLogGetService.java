package tech.taoq.log.service.impl;

import cn.monitor4all.logRecord.bean.LogDTO;
import cn.monitor4all.logRecord.service.IOperationLogGetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import tech.taoq.log.domain.db.OperateLogDO;
import tech.taoq.log.mapper.OperateLogMapper;

/**
 * OperationLogGetService
 *
 * @author keqi
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "taoq.log", value = "operate-log")
public class OperationLogGetService implements IOperationLogGetService {

    @Autowired(required = false)
    private OperateLogMapper operateLogMapper;

    @Override
    public void createLog(LogDTO logDTO) {
        //log.info("logDTO: [{}]", JSON.toJSONString(logDTO));
        if (operateLogMapper != null) {
            OperateLogDO t = new OperateLogDO();
            BeanUtils.copyProperties(logDTO, t);
            operateLogMapper.insert(t);
        }
    }
}
