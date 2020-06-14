package com.dingding.kill.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
@Slf4j
class UserMapperTest {

    @Autowired
    Environment env;

    @Autowired
    UserMapper userMapper;
    @Test
    void selectByUserName() {
      log.info("用户：{}",userMapper.selectByUserName("debug"));
    }

    @Test
    void test(){
        System.out.println(env.getProperty("mail.send.from"));
    }

}