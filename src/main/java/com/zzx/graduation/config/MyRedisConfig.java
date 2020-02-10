package com.zzx.graduation.config;

import com.zzx.graduation.entity.Staff;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Staff> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Staff> template = new RedisTemplate<Object, Staff>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Staff> ser = new Jackson2JsonRedisSerializer<Staff>(Staff.class);
        template.setDefaultSerializer(ser);
        return template;
    }
}

