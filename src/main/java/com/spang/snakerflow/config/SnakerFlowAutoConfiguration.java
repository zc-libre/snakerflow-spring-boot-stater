package com.spang.snakerflow.config;

import com.spang.snakerflow.engine.SpringConfiguration;
import com.spang.snakerflow.engine.SpringJdbcAccess;
import com.spang.snakerflow.engine.SpringSnakerEngine;
import com.spang.snakerflow.mybaits.MybatisAccess;
import com.spang.snakerflow.prop.SnakerFlowProperties;
import org.snaker.engine.*;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.cache.memory.MemoryCacheManager;
import org.snaker.engine.core.*;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

/**
 * @author zhao.cheng
 */
@Order
@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
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
    public IManagerService managerService(DBAccess dbAccess) {
        ManagerService managerService = new ManagerService();
        managerService.setAccess(dbAccess);
        return managerService;
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        return new MemoryCacheManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public IQueryService queryService(DBAccess dbAccess) {
        QueryService queryService = new QueryService();
        queryService.setAccess(dbAccess);
        return queryService;
    }
    @Bean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "dbAccessType")
    public DBAccess dbAccess(DataSource dataSource, LobHandler lobHandler) {
        SpringJdbcAccess dbAccess = new SpringJdbcAccess();
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
