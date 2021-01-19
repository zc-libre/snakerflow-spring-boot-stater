package com.github.snakerflow.config;

import com.github.snakerflow.cache.EhCacheManager;
import com.github.snakerflow.cache.SnakerRedisCacheManager;
import com.github.snakerflow.engine.SpelExpression;
import com.github.snakerflow.engine.SpringConfiguration;
import com.github.snakerflow.engine.SpringJdbcAccess;
import com.github.snakerflow.engine.SpringSnakerEngine;
import com.github.snakerflow.prop.SnakerFlowProperties;
import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.*;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.cache.memory.MemoryCacheManager;
import org.snaker.engine.core.*;
import org.snaker.engine.impl.JuelExpression;
import org.snaker.engine.impl.LogInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

/**
 * @author zhao.cheng
 */
@Slf4j
@Order
@Configuration
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@EnableConfigurationProperties({SnakerFlowProperties.class})
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SnakerFlowAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SnakerEngine snakerEngine(ApplicationContext context) {
        SpringSnakerEngine snakerEngine = new SpringSnakerEngine();
        SpringConfiguration configuration = new SpringConfiguration(context);
        snakerEngine.configure(configuration);
        return snakerEngine;
    }

    @Bean
    @ConditionalOnMissingBean
    public IProcessService processService(DBAccess dbAccess, CacheManager cacheManager) {
        ProcessService processService = new ProcessService();
        processService.setAccess(dbAccess);
        processService.setCacheManager(cacheManager);
        return processService;
    }

    @Bean
    @ConditionalOnMissingBean
    public IOrderService orderService(DBAccess dbAccess) {
        OrderService orderService = new OrderService();
        orderService.setAccess(dbAccess);
        return orderService;
    }

    @Bean
    @ConditionalOnMissingBean
    public ITaskService taskService(DBAccess dbAccess) {
        TaskService taskService = new TaskService();
        taskService.setAccess(dbAccess);
        return taskService;
    }

    @Bean
    @ConditionalOnMissingBean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }



    @Bean
    @ConditionalOnMissingBean
    public IManagerService managerService(DBAccess dbAccess) {
        ManagerService managerService = new ManagerService();
        managerService.setAccess(dbAccess);
        return managerService;
    }

    @Bean("memoryCacheManager")
    @ConditionalOnProperty(prefix = "snaker.flow.cache", name = "cache-type", havingValue = "memory", matchIfMissing = true)
    public CacheManager memoryCacheManager() {
        log.info("获取到缓存使用类型: memory");
        return new MemoryCacheManager();
    }


    @Bean("redisCacheManager")
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnProperty(prefix = "snaker.flow.cache", name = "cache-type", havingValue = "redis")
    public CacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate, SnakerFlowProperties properties) {
        log.info("获取到缓存使用类型: redis");
        return new SnakerRedisCacheManager(redisTemplate,properties);
    }


    @Bean("ehCacheManager")
    @ConditionalOnProperty(prefix = "snaker.flow.cache", name = "cache-type", havingValue = "ehcache")
    public CacheManager ehCacheManager() {
        log.info("获取到缓存使用类型: ehcache");
        return new EhCacheManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public IQueryService queryService(DBAccess dbAccess) {
        QueryService queryService = new QueryService();
        queryService.setAccess(dbAccess);
        return queryService;
    }

    @Bean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "spring")
    public DBAccess dbAccess(DataSource dataSource,
                             LobHandler lobHandler,
                             SnakerFlowProperties snakerFlowProperties) {
        log.info("获取到数据库连接类型: spring");
        SpringJdbcAccess dbAccess = new SpringJdbcAccess(snakerFlowProperties);
        dbAccess.setDataSource(dataSource);
        dbAccess.setLobHandler(lobHandler);
        return dbAccess;
    }

    @Bean
    @ConditionalOnMissingBean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }

}
