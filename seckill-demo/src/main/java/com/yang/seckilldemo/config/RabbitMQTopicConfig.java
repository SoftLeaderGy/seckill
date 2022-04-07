package com.yang.seckilldemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/04/06/20:50
 */
@Configuration
public class RabbitMQTopicConfig {

    /**
     *
     */
    private static final String QUEUE01 = "queueTopic01";
    private static final String QUEUE02 = "queueTopic02";
    private static final String TopicExchange = "topicExchange";
    private static final String ROUTEKEY01 = "#.queue.#";
    private static final String ROUTEKEY02 = "*.queue.#";

    @Bean
    public Queue queue01(){
        return new Queue(QUEUE01);
    }
    @Bean
    public Queue queue02(){
        return new Queue(QUEUE02);
    }

    // 创建TopicExchange交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TopicExchange);
    }

    // 绑定queue01队列到 topicExchange 交换机上 并使用的路由健为  routeRed
    @Bean
    public Binding binding01(){
        return BindingBuilder.bind(queue01()).to(topicExchange()).with(ROUTEKEY01);
    }

    // 绑定queue02队列到 topicExchange 交换机上 并使用的路由健为  routeGreen
    @Bean
    public Binding binding02(){
        return BindingBuilder.bind(queue02()).to(topicExchange()).with(ROUTEKEY02);
    }

}
