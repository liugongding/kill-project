package com.dingding.kill.service.base.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PayServiceImplTest {

    @Autowired
    private PayServiceImpl payService;

    @Test
    void payOrder() throws Exception {
        int result = payService.payOrder("202005082336107244985");
        log.info("订单信息：{}", result);
    }
}