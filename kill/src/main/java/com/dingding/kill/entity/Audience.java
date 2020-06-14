package com.dingding.kill.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author liudingding
 * @ClassName Audience
 * @description
 * @date 2020/3/31 22:26
 * Version 1.0
 */

@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience implements Serializable {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}
