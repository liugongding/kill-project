package com.dingding.kill.service.base.impl;

import com.dingding.kill.entity.ItemKill;
import com.dingding.kill.entity.ItemKillSuccess;
import com.dingding.kill.enums.SysConstant;
import com.dingding.kill.mapper.ItemKillMapper;
import com.dingding.kill.mapper.ItemKillSuccessMapper;
import com.dingding.kill.service.mq.MqSenderService;
import com.dingding.kill.service.base.KillService;
import com.dingding.kill.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liudingding
 * @ClassName KillServiceImpl
 * @description
 * @date 2020/4/2 0:11
 * Version 1.0
 */
@Service
@Slf4j
public class KillServiceImpl implements KillService {

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Autowired
    private ItemKillMapper itemKillMapper;

    @Autowired
    private MqSenderService mqSenderService;

    @Override
    public Boolean killItem(Integer killId, Integer userId) throws Exception {
        Boolean result = false;
        //TODO 这里有个支付问题没有实现

        int itemKillSuccessResult = itemKillSuccessMapper.countByKillUserId(killId, userId);
        //判断当前用户是否已经抢购过当前商品
        if (itemKillSuccessResult <= 0) {
            // 判断当前抢购商品库存是否充足以及是否在可抢购的时间段内---cankill
            ItemKill itemKill = itemKillMapper.selectById(killId);
            if (itemKill != null && itemKill.getCanKill() == 1) {
                //减库存
                int res = itemKillMapper.updateKillItem(killId);
                if (res > 0) {
                    //生成秒杀成功的订单，同时异步通知用户秒杀成功
                    this.commonRecordKillSuccessInfo(itemKill,userId);
                    result = true;
                }
            }
        } else {
            throw new Exception("您已经抢购过该商品了!");
        }
        return result;
    }

    private void commonRecordKillSuccessInfo(ItemKill itemKill, Integer userId) throws Exception{
        //记录秒杀成功的购买明细即生成订单

        //生成订单号
        String orderNo = RandomUtil.generateOrderCode();
        ItemKillSuccess itemKillSuccess = ItemKillSuccess.builder()
                .code(orderNo)
                .itemId(itemKill.getItemId())
                .killId(itemKill.getId())
                .userId(userId.toString())
                .status(SysConstant.OrderStatus.SuccessNotPayed.getCode().byteValue())
                .createTime(DateTime.now().toDate())
                .build();
        int successResult = itemKillSuccessMapper.insertSelective(itemKillSuccess);
        if (successResult > 0) {
            //进行异步邮件消息的通知 rabbitmq + mail
            mqSenderService.sendKillSuccessEmail(orderNo);

            //入死信队列，用于 “失效” 超过指定的TTL时间时仍然未支付的订单
            mqSenderService.sendKillSuccessOrderExpireMsg(orderNo);
        }
    }
}
