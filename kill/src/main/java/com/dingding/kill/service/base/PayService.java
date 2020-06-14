package com.dingding.kill.service.base;

/**
 * @author liudingding
 * @ClassName PayService
 * @description
 * @date 2020/5/8 21:14
 * Version 1.0
 */
public interface PayService {

    /**
     * 订单支付
     * @param orderNo
     * @return
     * @throws Exception
     */
     int payOrder(String orderNo) throws Exception;

}
