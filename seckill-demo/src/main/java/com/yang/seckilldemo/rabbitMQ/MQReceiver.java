package com.yang.seckilldemo.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description: MQ消息的接受者
 * @Author: Guo.Yang
 * @Date: 2022/04/04/21:14
 */
@Slf4j
@Service
public class MQReceiver {

    /**
     * 接收消息方法receiver(Object msg)
     * 入参 用于接收消息
     * 方法上添加注解@RabbitListener(queues = "queue")
     * 参数为监听的是那个消息队列
     */
    @RabbitListener(queues = "queue")
    public void receiver(Object msg){
        log.info("接收消息=====>" + msg);
    }
}
