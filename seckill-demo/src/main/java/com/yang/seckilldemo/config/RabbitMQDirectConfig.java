package com.yang.seckilldemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/04/06/20:50
 */
//@Configuration
public class RabbitMQDirectConfig {
//
//    private static final String QUEUE01 = "queueDirect01";
//    private static final String QUEUE02 = "queueDirect02";
//    private static final String DirectExchange = "directExchange";
//    private static final String ROUTERED = "routeRed";
//    private static final String ROUTERGREEN = "routeGreen";
//
//    @Bean
//    public Queue queue01(){
//        return new Queue(QUEUE01);
//    }
//    @Bean
//    public Queue queue02(){
//        return new Queue(QUEUE02);
//    }
//
//    // 创建DirectExchange交换机
//    @Bean
//    public DirectExchange directExchange(){
//        return new DirectExchange(DirectExchange);
//    }
//
//    // 绑定queue01队列到 directExchange 交换机上 并使用的路由健为  routeRed
//    @Bean
//    public Binding binding01(){
//        return BindingBuilder.bind(queue01()).to(directExchange()).with(ROUTERED);
//    }
//
//    // 绑定queue02队列到 directExchange 交换机上 并使用的路由健为  routeGreen
//    @Bean
//    public Binding binding02(){
//        return BindingBuilder.bind(queue02()).to(directExchange()).with(ROUTERGREEN);
//    }

}
