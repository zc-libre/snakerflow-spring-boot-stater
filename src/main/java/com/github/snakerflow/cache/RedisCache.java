package com.github.snakerflow.cache;

import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheException;

import java.util.Objects;

@SuppressWarnings("unchecked")
public class RedisCache<K, V> implements Cache<K, V> {

    private final org.springframework.data.redis.cache.RedisCache redisCache;

    public RedisCache(org.springframework.data.redis.cache.RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    public V get(K k) throws CacheException {
        if(k == null) {
            return null;
        }
        try {
            org.springframework.cache.Cache.ValueWrapper valueWrapper = redisCache.get(k);
            if (Objects.isNull(valueWrapper)) {
                return null;
            }
            return (V) valueWrapper.get();
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public V put(K k, V v) throws CacheException {
        try {
            redisCache.put(k, v);
            return v;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public V remove(K k) throws CacheException {
        try {
            V v = get(k);
            if (v != null) {
                redisCache.evict(k);
            }
            return v;
        } catch (CacheException e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void clear() throws CacheException {
        try {
            redisCache.clear();
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }
}
