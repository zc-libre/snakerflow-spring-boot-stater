package com.spang.snakerflow.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhao.cheng
 * @date 2020/11/25 9:12
 */
@Data
@ConfigurationProperties("snaker.flow")
public class SnakerFlowProperties {

    /**
     * 是否初始化数据库
     */
    private boolean autoInitSchema = Boolean.FALSE;
    /**
     * 是否开启事务
     */
    private boolean transactionEnabled = true;

    /**
     * 缓存实现类型
     */
    private CacheType cacheType = CacheType.MEMORY;

    /**
     * 数据库连接类型
     */
    private DbAccessType dbAccessType = DbAccessType.MYBATIS;


    private enum DbAccessType {

        /**
         * mybatis
         */
        MYBATIS,

        /**
         * spring
         */
        SPRING
    }

    public enum CacheType {
        /**
         * redis
         */
        REDIS,

        /**
         * 内存
         */
        MEMORY,

        /**
         * ehcache
         */
        EHCACHE;
    }
}
