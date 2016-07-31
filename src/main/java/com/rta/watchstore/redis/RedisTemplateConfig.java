package com.rta.watchstore.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisTemplateConfig {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> getStringRestTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        redisTemplate.setDefaultSerializer(new StringRedisSerializer());

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        //System.out.println("&&&&&&&&&&&&&&&&&&&&&   " + redisTemplate.getConnectionFactory().getConnection().ping());
        return redisTemplate;
    }
}
