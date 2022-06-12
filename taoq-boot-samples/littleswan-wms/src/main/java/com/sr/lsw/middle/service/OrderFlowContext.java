package com.sr.lsw.middle.service;

import com.sr.lsw.communication.fms.FmsService;
import com.sr.lsw.communication.fms.domain.param.FmsOrder;
import com.sr.lsw.communication.fms.domain.param.FmsOrderMission;
import com.sr.lsw.communication.fms.domain.resp.FmsResp;
import com.sr.lsw.middle.domain.db.LocationDO;
import com.sr.lsw.middle.domain.param.OrderFlowContextParam;
import com.sr.lsw.middle.mapper.LocationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.server.ServerErrorException;
import tech.taoq.common.exception.third.ThirdServiceErrorException;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 订单流程上下文类
 *
 * @author keqi
 */
@Slf4j
@Service
public class OrderFlowContext {

    @Autowired
    private FmsService fmsService;
    @Autowired
    private LocationMapper locationMapper;

    private synchronized FmsResp<String> execute(OrderFlowContextParam param) {
        BiFunction<FmsOrder, Object, List<FmsOrderMission>> startFun =
                OrderFlowService.FLOW_MAP.get(param.getStartFlowType());

        BiFunction<FmsOrder, Object, List<FmsOrderMission>> endFun =
                OrderFlowService.FLOW_MAP.get(param.getEndFlowType());

        if (startFun == null || endFun == null) {
            throw new ServerErrorException("no orderFlow type exists for start " + param.getStartFlowType()
                    + " or end " + param.getEndFlowType());
        }

        // 获取起点搬运子任务列表
        List<FmsOrderMission> startMissionList = startFun.apply(param.getFmsOrder(), param.getStartParam());
        // 获取终点搬运子任务列表
        List<FmsOrderMission> endMissionList = endFun.apply(param.getFmsOrder(), param.getEndParam());

        startMissionList.addAll(endMissionList);
        param.getFmsOrder().setMission(startMissionList);

        FmsResp<String> resp = fmsService.createOrder(param.getFmsOrder());
        if (!resp.isSuccess()) {
            // 订单创建失败，直接抛异常
            throw new ThirdServiceErrorException("createOrder fail : " + resp.getMsg());
        }
        if (resp.isSuccess()) {
            // 订单创建成功，将起点和终点的库位状态更改为锁定
            log.info("Order {} is created success, update location status is LOCK, start location {} and end location {}",
                    resp.getData(), param.getStartLocationId(), param.getEndLocationId());
            LocationDO start = new LocationDO();
            start.setId(param.getStartLocationId());
            start.setStatus(LocationDO.Status.LOCK.name());
            locationMapper.updateById(start);

            LocationDO end = new LocationDO();
            end.setId(param.getStartLocationId());
            end.setStatus(LocationDO.Status.LOCK.name());
            locationMapper.updateById(end);
        }

        return resp;
    }
}
