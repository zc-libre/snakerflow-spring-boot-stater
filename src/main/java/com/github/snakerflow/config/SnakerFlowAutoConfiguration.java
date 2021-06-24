package com.github.snakerflow.config;

import com.github.snakerflow.engine.SpringConfiguration;
import com.github.snakerflow.engine.SpringJdbcAccess;
import com.github.snakerflow.engine.SpringSnakerEngine;
import com.github.snakerflow.prop.SnakerFlowProperties;
import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.*;
import org.snaker.engine.access.jdbc.JdbcAccess;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.core.*;
import org.snaker.engine.impl.LogInterceptor;
import org.snaker.engine.impl.SurrogateInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
    @ConditionalOnProperty(prefix = "snaker.flow", name = "enable-surrogate", havingValue = "true")
    public SurrogateInterceptor surrogateInterceptor() {
        return new SurrogateInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "jdbc", matchIfMissing = true)
    public DBAccess jdbcDBAccess(DataSource dataSource) {
        JdbcAccess jdbcAccess = new JdbcAccess();
        jdbcAccess.setDataSource(dataSource);
        return jdbcAccess;
    }

    @Bean
    @ConditionalOnMissingBean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }

}
