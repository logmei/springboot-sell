package com.logmei.sell.service;

public interface IRedisLock {
    boolean lock(String key,Long time);
    void unlock(String key,Long time);
}
