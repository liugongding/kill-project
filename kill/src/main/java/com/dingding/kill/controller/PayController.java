package com.dingding.kill.controller;

import com.dingding.kill.enums.StatusCode;
import com.dingding.kill.mapper.PayOrderMapper;
import com.dingding.kill.response.BaseResponse;
import com.dingding.kill.service.base.impl.PayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liudingding
 * @ClassName PayController
 * @description
 * @date 2020/5/8 21:10
 * Version 1.0
 */
@RestController
@Slf4j
public class PayController {

    @Autowired
    private PayServiceImpl payService;
    @Autowired
    private PayOrderMapper payOrderMapper;

    @GetMapping(value = "/pay")
    public BaseResponse payOrder(String code){
        log.info("订单编号：{}", code);
        System.out.println(code);
        BaseResponse response;
        try {
            payService.payOrder(code);
//            payOrderMapper.updateOrder(orderNo)
            response = new BaseResponse(StatusCode.SUCCESS);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.FAIL.getCode(), "支付失败");
        }
        return response;
    }
}
