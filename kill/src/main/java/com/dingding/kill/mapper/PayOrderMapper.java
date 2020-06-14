package com.dingding.kill.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author liudingding
 * @ClassName PayOrderMapper
 * @description
 * @date 2020/5/8 21:18
 * Version 1.0
 */
@Mapper
@Repository
public interface PayOrderMapper {

    /**
     * 支付订单
     * @param code
     * @return
     */
    int updateOrder(@Param("code") String code);
}
