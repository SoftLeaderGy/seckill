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

    public void sendExchange(Object msg){
        log.info("发送消息=======>" + msg.toString());

        // fanoutExchange（）
        rabbitTemplate.convertAndSend("fanoutExchange","",msg);
    }

    /**
     * 向路由健为routeRed的去队列上发消息
     * @param msg
     */
    public void sendDirectExchangeForRouteRed(Object msg){
        log.info("发送消息=======>" + msg.toString());
        // DirectExchange模式的交换机只能发送与路由健相匹配的交换机发消息
        rabbitTemplate.convertAndSend("directExchange","routeRed",msg);
    }

    /**
     * 向路由健为routeGreen的去队列上发消息
     * @param msg
     */
    public void sendDirectExchangeForRouteGreen(Object msg){
        log.info("发送消息=======>" + msg.toString());
        // DirectExchange模式的交换机只能发送与路由健相匹配的交换机发消息
        rabbitTemplate.convertAndSend("directExchange","routeGreen",msg);
    }



    /**
     * 向路由健为Route01的去队列上发消息
     * @param msg
     */
    public void sendTopicExchangeForRoute01(Object msg){
        log.info("发送消息=======>" + msg.toString());
        // DirectExchange模式的交换机只能发送与路由健相匹配的交换机发消息
        rabbitTemplate.convertAndSend("topicExchange","queue.mee.qwe",msg); // *.queue.#
    }

    /**
     * 向路由健为Route02的去队列上发消息
     * @param msg
     */
    public void sendTopicExchangeForRoute02(Object msg){
        log.info("发送消息=======>" + msg.toString());
        // DirectExchange模式的交换机只能发送与路由健相匹配的交换机发消息
        rabbitTemplate.convertAndSend("topicExchange","msg.queue.msg.qwe.qwe",msg); // #.queue.#
    }


    /**
     * 向秒杀交换机（skillExchange）中发送消息，且路由key为"skill.massage"
     * @param msg
     */
    public void sendSkillMsg(Object msg){
        log.info("发送消息=======>" + msg.toString());
        rabbitTemplate.convertAndSend("skillExchange","skill.massage",msg);
    }
}
