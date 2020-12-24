package com.spang.snakerflow.cache;

import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheException;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.cache.memory.MemoryCache;
import org.snaker.engine.helper.StringHelper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * TODO redis实现cache
 * @author zhao.cheng
 */
@SuppressWarnings("all")
public class SnakerRedisCacheManager<K, V> implements CacheManager {

    private final RedisTemplate<K, V> redisTemplate;
    private final ConcurrentMap<String, Cache> caches;

    public SnakerRedisCacheManager(RedisTemplate<K, V> redisTemplate) {
        this.caches = new ConcurrentHashMap<String, Cache>();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if(StringHelper.isEmpty(name)) {
            throw new IllegalArgumentException("Cache名称不能为空.");
        }
        Cache cache;
        cache = caches.get(name);

        if (cache == null) {
            cache = new RedisCache(redisTemplate);
            Cache existing = caches.putIfAbsent(name, cache);
            if (existing != null) {
                cache = existing;
            }
        }

        return cache;
    }

    @Override
    public void destroy() throws CacheException {
        while (!caches.isEmpty()) {
            caches.clear();
        }
    }
}
