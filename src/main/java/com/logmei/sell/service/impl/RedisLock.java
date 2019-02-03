package com.logmei.sell.service.impl;

import com.logmei.sell.service.IRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RedisLock implements IRedisLock {

    @Autowired
    StringRedisTemplate redisTemplate;

    private static final Long keepMills = 100L;

    @Override
    public boolean lock(String key, Long time) {
        time = System.currentTimeMillis()+keepMills;
        //判断是否有key
        if(redisTemplate.opsForValue().setIfAbsent(key,String.valueOf(time))){
            System.out.println("redis中key已经超时或不存在");
            return true;
        }
        //存在key判断是否超时
        long now = System.currentTimeMillis();
        String oldTime = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(oldTime) &&  redisTemplate.opsForValue().getAndSet(key,String.valueOf(now+keepMills)).equals(oldTime) ){
            System.out.println("key存在，但修改时间为当前线程修改的时间，没有被其他线程抢先");
            return true;
        }

        return false;
    }

    @Override
    public void unlock(String key, Long time) {
      redisTemplate.opsForValue().getOperations().delete(key);
    }
}
