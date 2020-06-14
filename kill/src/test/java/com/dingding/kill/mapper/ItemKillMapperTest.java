package com.dingding.kill.mapper;

import com.dingding.kill.entity.ItemKill;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class ItemKillMapperTest {

    @Resource
    private ItemKillMapper itemKillMapper;
    @Test
    void selectAll() {
        itemKillMapper.selectAll().forEach(System.out::println);
    }

    @Test
    void selectById() {
        ItemKill itemKill = itemKillMapper.selectById(1);
        log.info("商品：{}",itemKill);
    }

    @Test
    void updateKillItem() {
        int result = itemKillMapper.updateKillItem(1);
        log.info("库存：{}",result);
    }
}