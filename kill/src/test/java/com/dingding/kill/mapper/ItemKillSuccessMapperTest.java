package com.dingding.kill.mapper;

import com.dingding.kill.dto.KillSuccessUserInfo;
import com.dingding.kill.entity.ItemKillSuccess;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ItemKillSuccessMapperTest {

    @Autowired
    ItemKillSuccessMapper itemKillSuccessMapper;

    @Test
    void countByKillUserId() {
        int result = itemKillSuccessMapper.countByKillUserId(3, 10);
        log.info("result:{}",result);
    }

    @Test
    void selectByPrimaryKey() {
        ItemKillSuccess result = itemKillSuccessMapper.selectByPrimaryKey("202005072255414263168");
        log.info("result:{}",result);
    }

    @Test
    void selectByCode() {
        KillSuccessUserInfo result = itemKillSuccessMapper.selectByCode("202005072255414263168");
        log.info("订单详情:{}",result);
    }
}