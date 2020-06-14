package com.dingding.kill.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liudingding
 * @ClassName RandomCode
 * @description
 * @date 2020/3/31 14:27
 * Version 1.0
 */
@Data
@ToString
public class RandomCode implements Serializable {

    private Integer id;

    private String code;
}
