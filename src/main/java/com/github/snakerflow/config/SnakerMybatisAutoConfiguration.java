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
@ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "mybatis", matchIfMissing = true)
public class SnakerMybatisAutoConfiguration {


    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DBAccess dbAccess(SqlSessionTemplate sqlSessionTemplate,
                             SqlSessionFactory sqlSessionFactory) {
        log.info("获取到数据库连接类型: mybatis");

        return new MybatisAccess(sqlSessionFactory, sqlSessionTemplate);
    }


}
