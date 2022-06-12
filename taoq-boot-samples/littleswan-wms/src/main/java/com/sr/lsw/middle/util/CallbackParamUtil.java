package com.sr.lsw.middle.util;

import com.alibaba.fastjson.JSON;
import com.sr.lsw.middle.domain.db.CallbackParamDO;
import com.sr.lsw.middle.mapper.CallbackParamMapper;
import com.sr.lsw.middle.service.CallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.taoq.common.exception.server.ServerErrorException;

/**
 * ActionParamUtil
 *
 * @author keqi
 */
@Component
public class CallbackParamUtil {

    private static CallbackParamMapper callbackParamMapper;

    @Autowired
    public void setCallbackParamMapper(CallbackParamMapper callbackParamMapper) {
        CallbackParamUtil.callbackParamMapper = callbackParamMapper;
    }

    /**
     * 删除对应的回调参数
     *
     * @param id id
     */
    public static void deleteCallbackParamById(String id) {
        callbackParamMapper.deleteById(id);
    }

    /**
     * 保存回调参数，并获取对应的编码
     *
     * @param callbackParamType callbackParamType
     * @param callbackParam     callbackParam
     * @return r
     */
    public static int[] encodeCallbackParam(CallbackService.CallbackParamType callbackParamType, Object callbackParam) {
        CallbackParamDO t = new CallbackParamDO();
        t.setType(callbackParamType.name());
        t.setCallbackParam(JSON.toJSONString(callbackParam));
        callbackParamMapper.insert(t);
        return encode(t.getId());
    }

    /**
     * 获取回调参数
     *
     * @param actionParams actionParams
     * @return r
     */
    public static CallbackParamDO decodeCallbackParam(int[] actionParams) {
        String id = decode(actionParams);
        return callbackParamMapper.selectById(id);
    }

    /**
     * 根据 callbackParam 构造一个 int[]
     *
     * @param callbackParam 必须小于 999999999999999999(正常情况下到不了这一天)
     * @return r
     */
    private static int[] encode(String callbackParam) {
        int[] action = new int[3];
        action[0] = -20;
        int length = callbackParam.length();
        if (length < 10) {
            // action[1] = 0;
            action[2] = Integer.parseInt(callbackParam);
        }
        if (length == 10) {
            int i = Integer.parseInt(String.valueOf(callbackParam.charAt(0)));
            if (i < 2) {
                action[2] = Integer.parseInt(callbackParam);
            }
            if (i >= 2) {
                action[1] = i;
                action[2] = Integer.parseInt(callbackParam.substring(1));
            }
        }
        if (length > 10) {
            int index = length - 10;
            int i = Integer.parseInt(String.valueOf(callbackParam.charAt(index)));
            String prefix = "";
            if (i < 2) {
                action[2] = Integer.parseInt(callbackParam.substring(index));
                prefix = callbackParam.substring(0, index);
            }
            if (i >= 2) {
                action[2] = Integer.parseInt(callbackParam.substring(index + 1));
                prefix = callbackParam.substring(0, index + 1);
            }
            if (prefix.length() > 10 || (prefix.length() == 10 && ((Integer.parseInt(String.valueOf(prefix.charAt(0)))) >= 2))) {
                // 正常情况下，应该到不了这一天
                throw new ServerErrorException("callback_param 表的主键过大，需要将 AUTO_INCREMENT 参数重置为 1");
            }
            action[1] = Integer.parseInt(prefix);
        }

        return action;
    }

    /**
     * 根据 int[] actionParams 构造出一个字符串
     *
     * @param actionParams actionParams
     * @return r
     */
    private static String decode(int[] actionParams) {
        if (actionParams[1] == 0) {
            return String.valueOf(actionParams[2]);
        }
        return actionParams[1] + "" + actionParams[2];
    }
}
