package com.spang.snakerflow.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhao.cheng
 * @date 2020/11/25 9:12
 */
@ConfigurationProperties("snaker.flow")
public class SnakerFlowProperties {


    private DbAccessType dbAccessType = DbAccessType.MYBATIS;

    /**
     * 是否开启事务
     */
    private boolean transactionEnabled = true;

    /**
     * 缓存实现类型
     */
    private CacheType cacheType = CacheType.MEMORY;


    public CacheType getCacheType() {
        return cacheType;
    }

    public void setCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
    }

    public boolean isTransactionEnabled() {
        return transactionEnabled;
    }

    public void setTransactionEnabled(boolean transactionEnabled) {
        this.transactionEnabled = transactionEnabled;
    }

    public DbAccessType getDbAccessType() {
        return dbAccessType;
    }

    public void setDbAccessType(DbAccessType dbAccessType) {
        this.dbAccessType = dbAccessType;
    }

    public enum DbAccessType {

        /**
         * mybatis
         */
        MYBATIS,

        /**
         * spring
         */
        SPRING
    }

    public enum CacheType{
        /**
         * redis
         */
        REDIS,

        /**
         * 内存
         */
        MEMORY
    }
}
