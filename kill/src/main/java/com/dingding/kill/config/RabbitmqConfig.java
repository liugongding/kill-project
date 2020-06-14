package com.dingding.kill.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author liudingding
 * @ClassName RabbitmqConfig
 * @description
 * @date 2020/4/2 19:20
 * Version 1.0
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    /**
     * 异步发送邮件通知
     */
    public static final String EMAIL_QUEUE = "mq.kill.item.success.email.queue";
    public static final String EMAIL_EXCHANGE = "mq.kill.item.success.email.exchange";
    public static final String EMAIL_ROUTING_KEY = "mq.kill.item.success.email.routing.key";

    public static final String ORDER_QUEUE = "order_queue";
    public static final String ORDER_EXCHANGE = "order_exchange";
    public static final String ORDER_ROUTING_KEY = "order_routing_key";

    public static final String DLX_QUEUE = "dlx_queue";
    public static final String DLX_EXCHANGE = "dlx_exchange";
    public static final String DLX_ROUTING_KEY = "dlx_routing_key";





    /**
     * rabbitmq连接池
     */
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //初始化消费者数量
        factory.setConcurrentConsumers(1);
        //最大消费者数量
        factory.setMaxConcurrentConsumers(1);
        //每次从一次性从broker里面取1个消息待消费
        factory.setPrefetchCount(1);
        return factory;
    }


    /**
     * 邮件通知
     * @return
     */
    @Bean
    public Queue successEmailQueue(){
        return new Queue(EMAIL_QUEUE);
    }

    @Bean
    public TopicExchange successEmailExchange(){
        return new TopicExchange(EMAIL_EXCHANGE);
    }

    @Bean
    public Binding successEmailBinding(){
        return BindingBuilder
                .bind(successEmailQueue())
                .to(successEmailExchange())
                .with(EMAIL_ROUTING_KEY);
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4


//    @Bean
//    public Queue deathLetterQueue(){
//        return QueueBuilder
//                .durable(RabbitmqConfig.DLX_QUEUE)
//                .build();
//    }
//
//    /**
//     * 死信交换机
//     * @return
//     */
//    @Bean
//    public TopicExchange deathTopicExchange(){
//        return ExchangeBuilder
//                .topicExchange(RabbitmqConfig.DLX_EXCHANGE)
//                .durable(true)
//                .build();
//    }
//
//    @Bean
//    public Binding deathLetterBinding(){
//        return BindingBuilder
//                .bind(deathLetterQueue())
//                .to(deathTopicExchange())
//                .with(RabbitmqConfig.DLX_ROUTING_KEY);
//    }
//
//    /**
//     * 创建订单队列
//     * @return
//     */
//    @Bean
//    public Queue orderQueue() {
//        Map<String, Object> args = new HashMap<>(3);
//        //       x-dead-letter-exchange    声明死信交换机
//        args.put("x-dead-letter-exchange", RabbitmqConfig.DLX_EXCHANGE);
//        //       x-dead-letter-routing-key    声明死信路由键
//        args.put("x-dead-letter-routing-key", RabbitmqConfig.DLX_ROUTING_KEY);
//        return QueueBuilder.durable(RabbitmqConfig.ORDER_QUEUE).withArguments(args).build();
//    }
//
//    @Bean
//    public TopicExchange orderExchange(){
//        return ExchangeBuilder
//                .topicExchange(RabbitmqConfig.ORDER_EXCHANGE)
//                .durable(true)
//                .build();
//    }
//
//    @Bean
//    public Binding orderBinding(){
//        return BindingBuilder
//                .bind(orderQueue())
//                .to(orderExchange())
//                .with(RabbitmqConfig.ORDER_ROUTING_KEY);
//    }

    //真正的队列
    @Bean
    public Queue deathLetterQueue(){
        return QueueBuilder
//                .durable("mq.kill.item.success.kill.dead.real.queue")
                .durable(RabbitmqConfig.DLX_QUEUE)
                .build();
    }

    //死信交换机
    @Bean
    public TopicExchange deathTopicExchange(){
        return ExchangeBuilder
//                .topicExchange("mq.kill.item.success.kill.dead.exchange")
                .topicExchange(RabbitmqConfig.DLX_EXCHANGE)
                .durable(true)
                .build();
    }

    //死信交换机+死信路由->真正队列 的绑定
    @Bean
    public Binding deathLetterBinding(){
        return BindingBuilder
                .bind(deathLetterQueue())
                .to(deathTopicExchange())
//                .with("mq.kill.item.success.kill.dead.routing.key");
                .with(RabbitmqConfig.DLX_ROUTING_KEY);
    }


    //构建秒杀成功之后-订单超时未支付的死信队列消息模型
    @Bean
    public Queue orderQueue(){
        Map<String, Object> argsMap= Maps.newHashMap();
//        argsMap.put("x-dead-letter-exchange","mq.kill.item.success.kill.dead.exchange");
//        argsMap.put("x-dead-letter-routing-key","mq.kill.item.success.kill.dead.routing.key");
        argsMap.put("x-dead-letter-exchange", RabbitmqConfig.DLX_EXCHANGE);
        argsMap.put("x-dead-letter-routing-key", RabbitmqConfig.DLX_ROUTING_KEY);
        return QueueBuilder
//                .durable("mq.kill.item.success.kill.dead.queue")
                .durable(RabbitmqConfig.ORDER_QUEUE)
                .withArguments(argsMap)
                .build();
    }
    //基本交换机
    @Bean
    public TopicExchange orderExchange(){
        return ExchangeBuilder
//                .topicExchange("mq.kill.item.success.kill.dead.prod.exchange")
                .topicExchange(RabbitmqConfig.ORDER_EXCHANGE)
                .durable(true)
                .build();
    }

    //创建基本交换机+基本路由 -> 死信队列 的绑定
    @Bean
    public Binding orderBinding(){
        return BindingBuilder
                .bind(orderQueue())
                .to(orderExchange())
//                .with("mq.kill.item.success.kill.dead.prod.routing.key");
                .with(RabbitmqConfig.ORDER_ROUTING_KEY);
    }
}
