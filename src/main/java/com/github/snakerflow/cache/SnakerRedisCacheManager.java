package com.github.snakerflow.cache;

import com.github.snakerflow.prop.SnakerFlowProperties;
import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheException;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.helper.StringHelper;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * TODO redis实现cache
 * @author zhao.cheng
 */
@SuppressWarnings("all")
public class SnakerRedisCacheManager implements CacheManager {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ConcurrentMap<String, Cache> caches;
    private final SnakerFlowProperties snakerFlowProperties;

    public SnakerRedisCacheManager(RedisTemplate<String, Object> redisTemplate, SnakerFlowProperties snakerFlowProperties) {
        this.redisTemplate = redisTemplate;
        this.caches = new ConcurrentHashMap<>();
        this.snakerFlowProperties = snakerFlowProperties;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if(StringHelper.isEmpty(name)) {
            throw new IllegalArgumentException("Cache名称不能为空.");
        }
        Cache cache;
        cache = caches.get(name);

        if (cache == null) {
            cache = new RedisCache(redisTemplate, snakerFlowProperties);
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
