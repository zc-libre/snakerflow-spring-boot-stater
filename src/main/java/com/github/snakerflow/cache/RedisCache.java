package com.github.snakerflow.cache;

import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 *
 * TODO 修改实现方式
 * @author zhao.cheng
 * @date 2020/12/24 9:47
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public RedisCache(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public V get(K key) throws CacheException {
       return redisTemplate.opsForValue().get(key);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V v = get(key);
        redisTemplate.delete(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {

    }
}
