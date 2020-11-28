package com.API.masiv.TestApi.Configuration;
import com.API.masiv.TestApi.Documents.Bet;
import com.API.masiv.TestApi.Documents.Roulette;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }
    
    @Bean
    RedisTemplate<String, Roulette> redisTemplate(){
        final RedisTemplate<String, Roulette> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
    
    @Bean
    RedisTemplate<String, Bet> redisTemplateBet(){
        final RedisTemplate<String, Bet> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
