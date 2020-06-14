package com.dingding.kill.service.base.impl;

import com.dingding.kill.dto.KillSuccessUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ItemServiceImplTest {

    @Autowired
    private ItemServiceImpl itemService;

    @Test
    void selectByCode() throws Exception {
        KillSuccessUserInfo result = itemService.selectByCode("202005081553256084985");
        log.info("订单详情:{}", result);
    }
}