package com.github.snakerflow.config;

import com.github.snakerflow.cache.EhCacheManager;
import com.github.snakerflow.cache.SnakerRedisCacheManager;
import com.github.snakerflow.prop.SnakerFlowProperties;
import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.cache.memory.MemoryCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhao.cheng
 * @date 2021/1/19 16:17
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SnakerFlowProperties.class})
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SnakerCacheAutoConfiguration {

    @Bean("memoryCacheManager")
    @ConditionalOnProperty(prefix = "snaker.flow.cache", name = "cache-type", havingValue = "memory", matchIfMissing = true)
    public CacheManager memoryCacheManager() {
        log.info("获取到缓存使用类型: memory");
        return new MemoryCacheManager();
    }


    @Bean("redisCacheManager")
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnProperty(prefix = "snaker.flow.cache", name = "cache-type", havingValue = "redis")
    public CacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate,
                                          SnakerFlowProperties properties) {
        log.info("获取到缓存使用类型: redis");
        return new SnakerRedisCacheManager(redisTemplate,properties);
    }


    @Bean("ehCacheManager")
    @ConditionalOnProperty(prefix = "snaker.flow.cache", name = "cache-type", havingValue = "ehcache")
    public CacheManager ehCacheManager() {
        log.info("获取到缓存使用类型: ehcache");
        return new EhCacheManager();
    }
}
