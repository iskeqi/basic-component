package com.sr.lsw.middle.controller;

import com.sr.lsw.communication.fms.FmsService;
import com.sr.lsw.communication.fms.domain.param.FmsOrder;
import com.sr.lsw.communication.fms.domain.resp.FmsResp;
import com.sr.lsw.middle.domain.dto.CreateOrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.taoq.common.exception.third.ThirdServiceErrorException;

@Slf4j
@Api(tags = "订单管理")
@RestController
@RequestMapping("/api/v2/middle/orders")
public class OrderController {

    @Autowired
    private FmsService fmsService;

    @ApiOperation("创建订单")
    @PostMapping
    public CreateOrderDto createOrder(@RequestBody FmsOrder param) {
        FmsResp<String> resp = fmsService.createOrder(param);
        if (!resp.isSuccess()) {
            throw new ThirdServiceErrorException("createOrder fail : " + resp.getMsg());
        }
        return new CreateOrderDto().setOrderId(resp.getData());
    }
}
