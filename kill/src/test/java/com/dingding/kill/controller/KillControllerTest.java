package com.dingding.kill.controller;

import com.dingding.kill.service.base.impl.KillServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class KillControllerTest {

    @Autowired
    KillServiceImpl killService;

    @Test
    void execute() throws Exception {
        boolean result = killService.killItem(2, 10);
        log.info("是否抢购成功：{}", result);
    }
}