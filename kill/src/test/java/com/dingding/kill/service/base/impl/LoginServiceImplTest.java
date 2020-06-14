package com.dingding.kill.service.base.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class LoginServiceImplTest {

    @Autowired
    LoginServiceImpl loginService;

    @Test
    void selectByUserName() {
        log.info("用户：{}",loginService.selectByUserName("liu"));
    }
}