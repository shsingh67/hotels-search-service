package com.hotels.hotelsSearch;

import com.hotels.hotelsSearch.models.Hotel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@Configuration
public class AppConfig {

    @Value("${yelp.token}")
    private String yelpToken;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(new StringRedisSerializer());
        return template;
    }


    @Bean
    public HttpHeaders addHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String authToken = "Bearer " + yelpToken;
        headers.set("Authorization", authToken);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    @Bean
    public HttpEntity<String> getHttpEntity() {
        HttpEntity<String> entity = new HttpEntity<>(addHeaders());
        return entity;
    }

}
