package com.spang.snakerflow.cache;

import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheException;
import org.snaker.engine.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * TODO redis实现cache
 * @author zhao.cheng
 */
public class SnakerRedisCacheManager<K,V> implements CacheManager {

    private final RedisTemplate<String, Cache<K,V>> redisTemplate;

    public SnakerRedisCacheManager(RedisTemplate<String, Cache<K, V>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
          return null;
    }

    @Override
    public void destroy() throws CacheException {

    }
}
