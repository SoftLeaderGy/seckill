package com.yang.seckilldemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.BindException;


/**
 * @Description: RabbitMQ配置类
 * @Author: Guo.Yang
 * @Date: 2022/04/04/20:59
 */
@Configuration
public class RabbitMQConfig {



    private static final String QUEUE01 = "fanout_queue01";
    private static final String QUEUE02 = "fanout_queue02";
    private static final String FANOUTEXCHANGE = "fanoutExchange";
    /**
     * 创建一个队列 new Queue("queue",true);
     * 参数一：队列的名称
     * 参数二：是否持久化队列信息
     * 所以：我们创建一个队列名称为"queue" 并开启消息持久化
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue("queue",true);
    }

}
