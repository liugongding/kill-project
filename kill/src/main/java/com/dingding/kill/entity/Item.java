package com.dingding.kill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liudingding
 * @ClassName item
 * @description 商品实体类
 * @date 2020/3/31 14:08
 * Version 1.0
 */
@Data
@ToString
public class Item implements Serializable{

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编号
     */
    private String code;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 采购时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date purchaseTime;

    /**
     * 是否有效(1-是， 0-否)
     */
    private Integer isActive;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
