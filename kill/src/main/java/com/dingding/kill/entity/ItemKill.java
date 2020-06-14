package com.dingding.kill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liudingding
 * @ClassName ItemKill
 * @description 待秒杀商品表
 * @date 2020/3/31 14:15
 * Version 1.0
 */
@Data
@ToString
public class ItemKill implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 可被秒杀的总数
     */
    private Integer total;

    /**
     * 秒杀开启时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    /**
     * 是否有效(1-是， 0-否)
     */
    private Byte isActive;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date crateTime;

    //采用服务器时间控制是否可以进行抢购
    private Integer canKill;




}
