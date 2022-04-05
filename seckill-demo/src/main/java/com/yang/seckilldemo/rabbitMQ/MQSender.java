package com.yang.seckilldemo.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: MQ消息发送者
 * @Author: Guo.Yang
 * @Date: 2022/04/04/21:08
 */
@Service
@Slf4j
public class MQSender {

    // 注入RabbitTemplate 模版
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息方法
     * @param msg
     */
    public void send(Object msg){
        log.info("发送消息=======>" + msg.toString());
        // 通过rabbitTemplate模版的convertAndSend方法向指定的消息队列中发送消息
        // 参数一： 向那个队列中发送消息 （要发送消息的队列名称）
        // 参数二： 发送的消息
        rabbitTemplate.convertAndSend("queue",msg);
    }
}
