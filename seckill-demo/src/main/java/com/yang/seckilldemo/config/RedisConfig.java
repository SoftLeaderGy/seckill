package com.yang.seckilldemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.collections.RedisCollectionFactoryBean;

/**
 * @Description: Redis配置类
 * 使用redis 存储用户的信息
 * @Author: Guo.Yang
 * @Date: 2022/03/27/15:53
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String , Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // 设置redis存储的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置为Json的序列化存储
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 注入RedisConnectionFactory 连接工厂 ， 一定要注入
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
