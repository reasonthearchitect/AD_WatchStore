package com.rta.watchstore.repo.impl;

import com.rta.watchstore.repo.IWatchingCarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WatchingCarRepo implements IWatchingCarRepo {

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Override
    public void save(String name, String carId){
        this.redisTemplate.opsForList().leftPush(name, carId);
    }

    @Override
    public List<String> findAllForPerson(String name) {
        return this.redisTemplate.opsForList().range(name, 0, 10000);
    }

    @Override
    public void delete(String name, String carId) {
        this.redisTemplate.opsForList().remove(name, 1, carId);
    }
}


