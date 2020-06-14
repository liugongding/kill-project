package com.dingding.kill.service.mq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MqSenderServiceTest {

    @Autowired
    MqSenderService mqSenderService;

    @Test
    void sendKillSuccessOrderExpireMsg() {

//        mqSenderService.sendKillSuccessOrderExpireMsg("202004032302563134985");
    }
}