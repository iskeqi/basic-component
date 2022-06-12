package com.sr.lsw.communication.fms;

import com.alibaba.fastjson.JSON;
import com.dtflys.forest.http.ForestResponse;
import com.sr.lsw.communication.fms.domain.param.FmsOrder;
import com.sr.lsw.communication.fms.domain.resp.FmsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * FMS Service
 *
 * @author keqi
 */
@Service
public class FmsService {

    @Autowired
    private FmsHttp fmsHttp;

    /**
     * 创建订单
     *
     * @param param param
     * @return 响应成功时 data 中的值为订单id，响应失败时，msg 字段包含了失败信息
     */
    public FmsResp<String> createOrder(FmsOrder param) {
        FmsResp<String> result;

        ForestResponse<Map<String, Object>> response = fmsHttp.createOrder(param);

        if (response.isSuccess()) {
            Map<String, Object> respMap = response.getResult();
            result = new FmsResp<String>().setData(String.valueOf(respMap.get("id")));
        } else {
            FmsResp fmsResp = JSON.parseObject(response.getContent(), FmsResp.class);
            result = new FmsResp<String>().setSuccess(false).setMsg(fmsResp.getMsg());
        }

        return result;
    }

    /**
     * 分页查询地图列表
     *
     * @return 地图列表信息
     */
    public FmsResp<Object> mapList() {
        FmsResp<Object> result;

        ForestResponse<Object> response = fmsHttp.mapList();

        if (response.isSuccess()) {
            result = new FmsResp<>().setData(response.getResult());
        } else {
            result = new FmsResp<>().setData(null);
        }

        return result;
    }

    /**
     * 查询指定地图详情
     *
     * @param mapId 地图id
     * @return 指定地图的详情信息
     */
    public FmsResp<Object> mapDetail(String mapId) {
        FmsResp<Object> result;

        ForestResponse<Object> response = fmsHttp.mapDetail(mapId);

        if (response.isSuccess()) {
            result = new FmsResp<>().setData(response.getResult());
        } else {
            result = new FmsResp<>().setData(null);
        }

        return result;
    }
}
