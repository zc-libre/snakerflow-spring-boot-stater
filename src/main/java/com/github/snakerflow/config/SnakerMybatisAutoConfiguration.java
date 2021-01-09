package com.github.snakerflow.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.snakerflow.mybatis.MybatisAccess;
import com.github.snakerflow.mybatis.MybatisPlusAccess;
import com.github.snakerflow.mybatis.service.*;
import com.github.snakerflow.mybatis.service.impl.*;
import com.github.snakerflow.mybatis.service.mapstruct.EntityConvert;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.snaker.engine.DBAccess;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhao.cheng
 */
@Slf4j
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})

public class SnakerMybatisAutoConfiguration {

     private final static String TYPE_ALIASES_PACKAGE = "org.snaker.engine.entity";

    @Bean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "mybatis", matchIfMissing = true)
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DBAccess dbAccess(SqlSessionTemplate sqlSessionTemplate,
                             SqlSessionFactory sqlSessionFactory) {
        log.info("获取到数据库连接类型: mybatis");

        return new MybatisAccess(sqlSessionFactory, sqlSessionTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "mybatis_plus")
    public DBAccess mybatisPlusDBAccess(SnakerProcessService snakerProcessService,
                                        SnakerOrderService snakerOrderService,
                                        SnakerHistOrderService snakerHistOrderService,
                                        SnakerCcOrderService snakerCcOrderService,
                                        SnakerTaskService snakerTaskService,
                                        SnakerTaskActorService snakerTaskActorService,
                                        SnakerHistTaskService snakerHistTaskService,
                                        SnakerSurrogateService snakerSurrogateService) {
        log.info("获取到数据库连接类型: mybatis-plus");
        MybatisPlusAccess access = new MybatisPlusAccess();
        access.setSnakerProcessService(snakerProcessService);
        access.setSnakerOrderService(snakerOrderService);
        access.setSnakerCcOrderService(snakerCcOrderService);
        access.setSnakerHistOrderService(snakerHistOrderService);
        access.setSnakerTaskService(snakerTaskService);
        access.setSnakerTaskActorService(snakerTaskActorService);
        access.setSnakerHistTaskService(snakerHistTaskService);
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
