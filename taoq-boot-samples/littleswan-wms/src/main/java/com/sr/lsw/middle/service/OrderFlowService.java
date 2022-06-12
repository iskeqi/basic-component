package com.sr.lsw.middle.service;

import com.sr.lsw.communication.fms.domain.param.FmsOrder;
import com.sr.lsw.communication.fms.domain.param.FmsOrderMission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * 订单流程 Service
 *
 * @author keqi
 */
@Service
public class OrderFlowService {

    public static final Map<FlowType, BiFunction<FmsOrder, Object, List<FmsOrderMission>>>
            FLOW_MAP = new ConcurrentHashMap<>();

    public enum FlowType {
        // 起点搬运流程

        START_FLOW1,
        START_FLOW2,

        // 终点搬运流程

        END_FLOW1,
        END_FLOW2
    }

    {
        FLOW_MAP.put(FlowType.START_FLOW1, this::startFlow1);
        FLOW_MAP.put(FlowType.START_FLOW2, this::startFlow2);
        FLOW_MAP.put(FlowType.END_FLOW1, this::endFlow1);
        FLOW_MAP.put(FlowType.END_FLOW2, this::endFlow2);
    }

    private List<FmsOrderMission> startFlow1(FmsOrder fmsOrder, Object param) {
        return null;
    }

    private List<FmsOrderMission> startFlow2(FmsOrder fmsOrder, Object param) {
        return null;
    }

    private List<FmsOrderMission> endFlow1(FmsOrder fmsOrder, Object param) {
        return null;
    }

    private List<FmsOrderMission> endFlow2(FmsOrder fmsOrder, Object param) {
        return null;
    }
}
