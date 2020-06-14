package com.dingding.kill.service.mq;

import com.dingding.kill.config.RabbitmqConfig;
import com.dingding.kill.dto.KillSuccessUserInfo;
import com.dingding.kill.dto.MailDTO;
import com.dingding.kill.entity.ItemKillSuccess;
import com.dingding.kill.mapper.ItemKillSuccessMapper;
import com.dingding.kill.mapper.PayOrderMapper;
import com.dingding.kill.service.base.KillService;
import com.dingding.kill.service.mail.MailService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author liudingding
 * @ClassName MqReceiverService
 * @description
 * @date 2020/4/2 22:44
 * Version 1.0
 */
@Service
@Slf4j
public class MqReceiverService {

    private  String subject = "商品抢购成功";
    private  String content =
            "您好，您已成功抢购到商品: <strong style='color: red'>%s</strong> ，" +
                    "复制该链接并在浏览器采用新的页面打开，即可查看抢购详情：" +
                    "<a href='http://localhost:8092/kill/success/detail/%s' target='_blank'>http:localhost:8092/kill/success/detail/%s</a>" +
                    "，并请您在1个小时内完成订单的支付，" +
                    "超时将失效该订单哦！祝你生活愉快！";

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Autowired
    private KillService killService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PayOrderMapper payOrderMapper;

    /**
     * 秒杀异步邮件通知 -- 接受消息
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    //将队列持久化
                    value = @Queue(value = RabbitmqConfig.EMAIL_QUEUE, durable = "true"),
                    //绑定交换机
                    exchange = @Exchange(value = RabbitmqConfig.EMAIL_EXCHANGE, durable = "true", type = ExchangeTypes.TOPIC),
                    //绑定路由键名称
                    key = RabbitmqConfig.EMAIL_ROUTING_KEY
            )
    )
    @RabbitHandler
    public void ReceiveEmail(@Headers Map<String, Object> headers, Message message,
                             Channel channel, KillSuccessUserInfo killSuccessUserInfo) throws IOException {
        try {
            log.info("---------收到消息，开始消费--------");

            /**
             * Delivery Tag 用来标志信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个Delivery Tag
             * 以便 Consumer 可以在消息确认是告诉 RabbitMQ 到底那条消息被确认了
             * RabbitMQ 保证在每条信道中，每条消息的 Delivery Tag 从 1 开始递增
             */
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            log.info("deliveryTag:{}", deliveryTag);
            /**
             * * multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
             * 如果为 true， 则额外将比第一个参数指定的 Delivery Tag 小的消息一并确认
             */
            boolean multiple = false;
            log.info("消息实体:{}",killSuccessUserInfo);
            String itemName = killSuccessUserInfo.getItemName();
            String code = killSuccessUserInfo.getCode();
            String contents = String.format(this.content, itemName, code, code);
            MailDTO mail = new MailDTO(this.subject, contents, new String[]{"3486171474@qq.com"});
            mailService.sendHTMLEmail(mail);
            log.info("邮件发送完毕");
            channel.basicAck(deliveryTag,multiple);
        } catch (Exception e) {
            log.error("消息处理失败：{}",e.getMessage());
        }
    }

    /**
     * 商品未支付 -- 接受消息
     */
    @RabbitListener(queues = RabbitmqConfig.DLX_QUEUE)
    @RabbitHandler
    public void consumeExpireOrder(@Headers Map<String, Object> headers,
                                   KillSuccessUserInfo info, Channel channel){
        try {
            log.info("用户秒杀成功后超时未支付-监听者-接收消息:{}",info);
            if (info!=null){
                ItemKillSuccess itemKillSuccess = itemKillSuccessMapper.selectByPrimaryKey(info.getCode());
                if (itemKillSuccess != null && itemKillSuccess.getStatus().intValue()==0){
                    itemKillSuccessMapper.expireOrder(info.getCode());
                }
            }
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag, false);
        }catch (Exception e){
            log.error("订单超时:{}", e.getMessage());
        }
    }

}
