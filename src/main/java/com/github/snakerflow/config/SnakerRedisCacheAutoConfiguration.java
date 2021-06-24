package com.github.snakerflow.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.github.snakerflow.cache.RedisAutoCacheManager;
import com.github.snakerflow.cache.SnakerRedisCacheManager;
import com.github.snakerflow.util.FastJsonRedisSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.cache.CacheManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.lang.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@ConditionalOnBean(RedisConnectionFactory.class)
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(prefix = "snaker.flow.cache", name = "cache-type", havingValue = "redis")
@RequiredArgsConstructor
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SnakerRedisCacheAutoConfiguration {

    private final CacheProperties cacheProperties;
    @Nullable
    private final RedisCacheConfiguration redisCacheConfiguration;

    @Primary
    @Bean("redisCacheManager")
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory,
                                               CacheManagerCustomizers customizerInvoker) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration cacheConfiguration = this.determineConfiguration();
        List<String> cacheNames = this.cacheProperties.getCacheNames();
        Map<String, RedisCacheConfiguration> initialCaches = new LinkedHashMap<>();
        if (!cacheNames.isEmpty()) {
            Map<String, RedisCacheConfiguration> cacheConfigMap = new LinkedHashMap<>(cacheNames.size());
            cacheNames.forEach(it -> cacheConfigMap.put(it, cacheConfiguration));
            initialCaches.putAll(cacheConfigMap);
        }
        boolean allowInFlightCacheCreation = true;
        boolean enableTransactions = false;
        RedisAutoCacheManager cacheManager = new RedisAutoCacheManager(redisCacheWriter, cacheConfiguration, initialCaches, allowInFlightCacheCreation);
        cacheManager.setTransactionAware(enableTransactions);
        return customizerInvoker.customize(cacheManager);
    }


    @Bean("snakerRedisCacheManager")
    public CacheManager snakerRedisCacheManager(RedisCacheManager redisCacheManager) {
        return new SnakerRedisCacheManager(redisCacheManager);
    }

    private RedisCacheConfiguration determineConfiguration() {
        if (this.redisCacheConfiguration != null) {
            return this.redisCacheConfiguration;
        } else {
            CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
            FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
            ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
            config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
            if (redisProperties.getTimeToLive() != null) {
                config = config.entryTtl(redisProperties.getTimeToLive());
            }

            if (redisProperties.getKeyPrefix() != null) {
                config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
            }

            if (!redisProperties.isCacheNullValues()) {
                config = config.disableCachingNullValues();
            }

            if (!redisProperties.isUseKeyPrefix()) {
                config = config.disableKeyPrefix();
            }

            return config;
        }
    }

    @Bean
    @ConditionalOnMissingBean(CacheManagerCustomizers.class)
    public CacheManagerCustomizers cacheManagerCustomizers(
            ObjectProvider<List<CacheManagerCustomizer<?>>> customizers) {
        return new CacheManagerCustomizers(customizers.getIfAvailable());
    }

}
