package com.github.snakerflow.config;

import com.github.snakerflow.mybatis.MybatisPlusAccess;
import com.github.snakerflow.mybatis.service.*;
import com.github.snakerflow.mybatis.service.impl.*;
import com.github.snakerflow.mybatis.service.mapstruct.EntityConvert;
import com.github.snakerflow.prop.SnakerFlowProperties;
import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.DBAccess;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhao.cheng
 * @date 2021/1/11 9:17
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "mybatis_plus")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SnakerMybatisPlusAutoConfiguration {

    @Bean
    public DBAccess mybatisPlusDBAccess(SnakerFlowProperties properties,
                                        DataSource dataSource,
                                        SnakerProcessService snakerProcessService,
                                        SnakerOrderService snakerOrderService,
                                        SnakerHistOrderService snakerHistOrderService,
                                        SnakerCcOrderService snakerCcOrderService,
                                        SnakerTaskService snakerTaskService,
                                        SnakerTaskActorService snakerTaskActorService,
                                        SnakerHistTaskService snakerHistTaskService,
                                        SnakerHistTaskActorService histTaskActorService,
                                        SnakerSurrogateService snakerSurrogateService) {
        log.info("获取到数据库连接类型: mybatis-plus");

        MybatisPlusAccess access = new MybatisPlusAccess(properties,dataSource);
        access.setSnakerProcessService(snakerProcessService);
        access.setSnakerOrderService(snakerOrderService);
        access.setSnakerCcOrderService(snakerCcOrderService);
        access.setSnakerHistOrderService(snakerHistOrderService);
        access.setSnakerTaskService(snakerTaskService);
        access.setSnakerTaskActorService(snakerTaskActorService);
        access.setSnakerHistTaskService(snakerHistTaskService);
        access.setHistTaskActorService(histTaskActorService);
        access.setSnakerSurrogateService(snakerSurrogateService);
        access.setEntityConvert(EntityConvert.INSTANCE);
        return access;
    }




    @Bean
    public SnakerProcessService snakerProcessService() {
        return new SnakerProcessServiceImpl();
    }

    @Bean
    public SnakerOrderService snakerOrderService() {
        return new SnakerOrderServiceImpl();
    }

    @Bean
    public SnakerTaskService snakerTaskService() {
        return new SnakerTaskServiceImpl();
    }

    @Bean
    public SnakerTaskActorService snakerTaskActorService() {
        return new SnakerTaskActorServiceImpl();
    }

    @Bean
    public SnakerHistOrderService snakerHistOrderService() {
        return new SnakerHistOrderServiceImpl();
    }

    @Bean
    public SnakerHistTaskService snakerHistTaskService() {
        return new SnakerHistTaskServiceImpl();
    }

    @Bean
    public SnakerCcOrderService snakerCcOrderService() {
        return new SnakerCcOrderServiceImpl();
    }

    @Bean
    public SnakerHistTaskActorService snakerHistTaskActorService() {
        return new SnakerHistTaskActorServiceImpl();
    }

    @Bean
    public SnakerSurrogateService snakerSurrogateService() {
        return new SnakerSurrogateServiceImpl();
    }
}
