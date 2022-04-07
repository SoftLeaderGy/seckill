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

    @RabbitListener(queues = "fanout_queue01")
    public void receiver01(Object msg){
        log.info("fanout_queue01接收消息=====>" + msg);
    }

    @RabbitListener(queues = "fanout_queue02")
    public void receiver02(Object msg){
        log.info("fanout_queue02接收消息=====>" + msg);
    }

    @RabbitListener(queues = "queueDirect01")
    public void directReceiver01(Object msg){
        log.info("directQueue01接收消息=====>" + msg);
    }

    @RabbitListener(queues = "queueDirect02")
    public void directReceiver02(Object msg){
        log.info("directQueue02接收消息=====>" + msg);
    }

    @RabbitListener(queues = "queueTopic01")
    public void topicReceiver01(Object msg){
        log.info("topicReceiver01接收消息=====>" + msg);
    }

    @RabbitListener(queues = "queueTopic02")
    public void topicReceiver02(Object msg){
        log.info("topicReceiver02接收消息=====>" + msg);
    }
}
