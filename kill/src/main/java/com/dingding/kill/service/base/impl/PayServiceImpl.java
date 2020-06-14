package com.dingding.kill.service.base.impl;

import com.dingding.kill.mapper.PayOrderMapper;
import com.dingding.kill.service.base.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liudingding
 * @ClassName PayServiceImpl
 * @description
 * @date 2020/5/8 21:14
 * Version 1.0
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Override
    public int payOrder(String code) throws Exception {
        int payResult = payOrderMapper.updateOrder(code);
        if (payResult != 1){
            throw new Exception("支付失败");
        }
        return payResult;
    }
}

