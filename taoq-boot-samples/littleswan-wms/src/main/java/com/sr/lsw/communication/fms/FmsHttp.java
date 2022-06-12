package com.sr.lsw.communication.fms;

import com.dtflys.forest.annotation.*;
import com.dtflys.forest.http.ForestResponse;
import com.sr.lsw.communication.fms.domain.param.FmsOrder;

import java.util.Map;

/**
 * FmsHttp
 *
 * @author keqi
 */
@BaseRequest(baseURL = FmsHttpInterceptor.FMS_URL, interceptor = FmsHttpInterceptor.class)
public interface FmsHttp {

    /**
     * 创建订单
     *
     * @param param param
     * @return r
     */
    @Post("/api/v2/orders")
    ForestResponse<Map<String, Object>> createOrder(@JSONBody FmsOrder param);

    /**
     * 分页查询地图列表
     *
     * @return r
     */
    @LogEnabled(false)
    @Get("/api/v2/maps?page=1&perpage=100&active_state=ALL")
    ForestResponse<Object> mapList();

    /**
     * 查询指定地图详情
     *
     * @param id 地图id
     * @return r
     */
    @LogEnabled(false)
    @Get("/api/v2/maps/{id}")
    ForestResponse<Object> mapDetail(@Var("id") String id);
}
