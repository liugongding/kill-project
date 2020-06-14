package com.dingding.kill.service.base.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class KillServiceImplTest {

    @Autowired
    KillServiceImpl killService;

    @Test
    void killItem() throws Exception {
        boolean result = killService.killItem(1,10);
        log.info("是否秒杀成功：{}", result);
    }
}