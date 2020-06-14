package com.dingding.kill.service.mq;

import com.dingding.kill.config.RabbitmqConfig;
import com.dingding.kill.dto.KillSuccessUserInfo;
import com.dingding.kill.mapper.ItemKillSuccessMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liudingding
 * @ClassName MqSenderService
 * @description
 * @date 2020/4/2 21:35
 * Version 1.0
 */
@Component
@Slf4j
public class MqSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;


    /**
     * @param correlationData 唯一标识，有了这个唯一标识，就能确定那一条消息、是成功还是失败
     * @param ack 是否投递成功
     * @param cause 失败原因
     */
    final RabbitTemplate.ConfirmCallback confirmCallback= (correlationData, ack, cause) -> {
        log.info("回调id:{}", correlationData.getId());
        String messageId = correlationData.getId();
        if (ack == true) {
            log.info("消息发送成功:correlationData({}),ack({}),cause({})",messageId,ack,cause);
        } else {
            log.error("消息未达到交换机");
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = ((message, replyCode, replyText, exchange, routingKey) -> {
        log.warn("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}"
                ,exchange,routingKey,replyCode,replyText,message);
    });

    /**
     * 秒杀异步邮件通知 -- 发送消息
     * @param orderNo
     */
    public void sendKillSuccessEmail(String orderNo){
        log.info("秒杀成功异步发送邮件通知消息-准备发送消息：{}",orderNo);
        try{
            if (StringUtils.isNotBlank(orderNo)){
                KillSuccessUserInfo info = itemKillSuccessMapper.selectByCode(orderNo);
                // message 的作用是如果消息超时了，则进入死信队列，多一段时间重发
                if (info != null) {
                    rabbitTemplate.setConfirmCallback(confirmCallback);
                    rabbitTemplate.setReturnCallback(returnCallback);
                    rabbitTemplate.convertAndSend(RabbitmqConfig.EMAIL_EXCHANGE
                    , RabbitmqConfig.EMAIL_ROUTING_KEY, info, message -> {
                                MessageProperties messageProperties=message.getMessageProperties();
                                messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                                messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,KillSuccessUserInfo.class);
                                return message;
                    }, new CorrelationData(orderNo));
                }
            }
        } catch (Exception e){
            log.error("秒杀成功异步发送邮件通知消息-发生异常，消息为：{}, 异常信息：{}",orderNo, e.getMessage());
        }
    }

    /**
     * 秒杀成功后生成抢购订单-发送信息入死信队列，等待着一定时间失效超时未支付的订单
     * @param orderNo
     */
    public void sendKillSuccessOrderExpireMsg(final String orderNo){
        log.info("商品未支付进入死信队列：{}",orderNo);
        try{
            if (StringUtils.isNotBlank(orderNo)){
                KillSuccessUserInfo info = itemKillSuccessMapper.selectByCode(orderNo);
                if (info != null) {
                    rabbitTemplate.setConfirmCallback(confirmCallback);
                    rabbitTemplate.setReturnCallback(returnCallback);
                    rabbitTemplate.convertAndSend(RabbitmqConfig.ORDER_EXCHANGE,
                            RabbitmqConfig.ORDER_ROUTING_KEY, info, message -> {
                                MessageProperties messageProperties=message.getMessageProperties();
                                messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                                messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,KillSuccessUserInfo.class);
                                messageProperties.setContentEncoding("utf-8");
                                messageProperties.setExpiration("60000");
                                return message;
                                }, new CorrelationData(orderNo));
                }
            }
        } catch (Exception e){
            log.error("秒杀成功后生成抢购订单-发送信息入死信队列，等待着一定时间失效超时未支付的订单-发生异常，消息为：{},{}",orderNo,e.getMessage());
        }
    }






}
