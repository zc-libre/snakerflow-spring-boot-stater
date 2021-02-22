package com.github.snakerflow.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.github.snakerflow.prop.SnakerFlowProperties;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * TODO 修改实现方式
 *
 * @author zhao.cheng
 * @date 2020/12/24 9:47
 */
@RequiredArgsConstructor
public class RedisCache implements Cache<String, Object> {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SnakerFlowProperties properties;

    @Override
    public Object get(String key) throws CacheException {
        if (key == null) {
            return null;
        }
        Object val = null;
        try {

            val = redisTemplate.opsForValue().get(key);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
        return val;
    }

    @Override
    public Object put(String key, Object value) throws CacheException {
        try {
            redisTemplate.opsForValue().set(key,
                            value,
                            properties.getCache().getTimeout(),
                            properties.getCache().getTimeUnit());
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Object remove(String key) throws CacheException {
        try {
            Object val = get(key);
            if (Objects.nonNull(val)) {
                redisTemplate.delete(key);
            }
            return val;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        try {
            Set<String> keys = redisTemplate.keys( "*");
            if (keys != null && !keys.isEmpty()) {
                redisTemplate.delete(keys);
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}
