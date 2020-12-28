package com.github.snakerflow.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.snakerflow.mybaits.MybatisAccess;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.snaker.engine.DBAccess;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @author zhao.cheng
 */
@Slf4j
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@Import(MybatisPlusAutoConfiguration.class)
@ConditionalOnProperty(prefix = "snaker.flow", name = "db-access-type", havingValue = "mybatis", matchIfMissing = true)
public class SnakerMybatisAutoConfiguration implements BeanPostProcessor {

     private final static String TYPE_ALIASES_PACKAGE = "org.snaker.engine.entity";

    @Bean
    public DBAccess dbAccess(SqlSessionTemplate sqlSessionTemplate,
                             SqlSessionFactory sqlSessionFactory) {
        log.info("获取到数据库连接类型: mybatis");

        return new MybatisAccess(sqlSessionFactory, sqlSessionTemplate);
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
         if (bean instanceof MybatisPlusProperties) {
             MybatisPlusProperties properties = (MybatisPlusProperties) bean;
             String packages = properties.getTypeAliasesPackage();
             if (StringUtils.isNotBlank(packages)) {
                 packages = packages + StringPool.COMMA + TYPE_ALIASES_PACKAGE;
             }else {
                 packages = TYPE_ALIASES_PACKAGE;
             }
             log.info("获取到扫描实体类路径 : {}", packages);
             properties.setTypeAliasesPackage(packages);
             return properties;
         }
         return bean;
    }

}
