package com.spang.snakerflow.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spang.snakerflow.mybaits.MybatisAccess;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.snaker.engine.DBAccess;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhao.cheng
 */

@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@MapperScan("org.snaker.engine.entity")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SnakerMybatisAutoConfiguration implements BeanPostProcessor {

     private final static String TYPE_ALIASES_PACKAGE = "org.snaker.engine.entity";

    @Bean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "dbAccessType", matchIfMissing = true)
    public DBAccess dbAccess(SqlSessionTemplate sqlSessionTemplate,
                             SqlSessionFactory sqlSessionFactory) {
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
             properties.setTypeAliasesPackage(packages);
             return properties;
         }
         return bean;
    }

}
