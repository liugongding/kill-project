package com.dingding.kill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liudingding
 * @ClassName ItemKillSuccess
 * @description 秒杀成功订单表
 * @date 2020/3/31 14:25
 * Version 1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemKillSuccess implements Serializable {

    /**
     * 秒杀成功生成的订单编号
     */
    private String code;

    /**
     * 商品id
     */
    private Integer itemId;

    /**
     * 秒杀id
     */
    private Integer killId;

    /**
     *用户id
     */
    private String userId;

    /**
     * 秒杀结果: -1无效  0成功(未付款)  1已付款  2已取消'
     */
    private Byte status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Integer diffTime;
}
