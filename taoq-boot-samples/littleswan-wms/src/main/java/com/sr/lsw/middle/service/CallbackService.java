package com.sr.lsw.middle.service;

import com.sr.lsw.middle.domain.db.CallbackParamDO;
import com.sr.lsw.middle.domain.param.CommandParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * 回调实现类
 *
 * @author keqi
 */
@Slf4j
@Service
public class CallbackService {

    public static final Map<CallbackParamType, BiFunction<CommandParam, CallbackParamDO, Boolean>>
            TYPE_MAP = new ConcurrentHashMap<>();

    public enum CallbackParamType {
        UPDATE_STATION_STATUS
    }

    {
        TYPE_MAP.put(CallbackParamType.UPDATE_STATION_STATUS, this::updateStationStatus);
    }

    /**
     * 更改库位状态
     *
     * @param commandParam    commandParam
     * @param callbackParamDO callbackParamDO
     * @return true or false
     */
    private Boolean updateStationStatus(CommandParam commandParam, CallbackParamDO callbackParamDO) {
        return true;
    }
}
