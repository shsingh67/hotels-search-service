package com.hotels.hotelsSearch.dao;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class HotelDAO {

    private RedisTemplate<String, String> redisTemplate;
    private HashOperations hashOperations;


    public static final String KEY = "hotels";

    public HotelDAO(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.expire(KEY, 300, TimeUnit.SECONDS);
        this.hashOperations = redisTemplate.opsForHash();
    }


    public void add(String hashKey, String data) {
        hashOperations.put(KEY, hashKey, data);
    }

    public String retrieveHotels(String hashKey) {
        return (String)hashOperations.get(KEY, hashKey);

    }

}
