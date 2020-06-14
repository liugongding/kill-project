package com.dingding.kill.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author liudingding
 * @ClassName RedissonConfig
 * @description
 * @date 2020/5/19 15:46
 * Version 1.0
 */

@Configuration
public class RedissonConfig {

    @Autowired
    private Environment environment;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress(environment.getProperty("spring.redis.host"))
                .setPassword(environment.getProperty("spring.redis.password"));
        RedissonClient client= Redisson.create(config);
        return client;
    }
}
