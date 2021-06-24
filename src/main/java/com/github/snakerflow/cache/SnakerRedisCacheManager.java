package com.github.snakerflow.cache;

import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheException;
import org.snaker.engine.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;


import java.util.Collection;
import java.util.Objects;

@Slf4j
public class SnakerRedisCacheManager implements CacheManager {

    private final RedisCacheManager redisCacheManager;

    public SnakerRedisCacheManager(RedisCacheManager redisCacheManager) {
        log.info("获取到缓存使用类型: redis");
        this.redisCacheManager = redisCacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        try {
            org.springframework.data.redis.cache.RedisCache redisCache = (org.springframework.data.redis.cache.RedisCache) redisCacheManager.getCache(name);
            return new RedisCache<>(redisCache);
        } catch (Exception e) {
            throw new CacheException(e);
        }

    }

    @Override
    public void destroy() throws CacheException {
        try {
            Collection<String> cacheNames = redisCacheManager.getCacheNames();
            for (String cacheName : cacheNames) {
                org.springframework.cache.Cache cache = redisCacheManager.getCache(cacheName);
               if (Objects.nonNull(cache)) {
                   cache.clear();
               }
            }
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }
}
