package com.github.snakerflow.prop;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
     * 缓存实现类型
     */
    private Cache cache = new Cache();

    /**
     * 数据库连接类型
     */
    private DbAccessType dbAccessType = DbAccessType.MYBATIS;

    /**
     * 决策表达式类型
     */
    private ExpressionType expressionType = ExpressionType.JUEL;


    public static class Cache {

        /**
         * 过期时间（仅对redis模式生效）
         */
        private Integer timeout = 3;

        /**
         * 时间单位
         */
        private TimeUnit timeUnit = TimeUnit.HOURS;

        public Integer getTimeout() {
            if (Objects.isNull(timeout)) {
                return 3;
            }
            return timeout;
        }

        public void setTimeout(Integer timeout) {
            this.timeout = timeout;
        }

        public TimeUnit getTimeUnit() {
            if (Objects.isNull(timeUnit)) {
                timeUnit = TimeUnit.HOURS;
            }
            return timeUnit;
        }

        public void setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
        }

        public String getKeyPrefix() {
            if (StringUtils.isBlank(keyPrefix)) {
                return "snaker::";
            }
            return keyPrefix;
        }

        public void setKeyPrefix(String keyPrefix) {
            this.keyPrefix = keyPrefix;
        }

        public CacheType getCacheType() {
            return cacheType;
        }

        public void setCacheType(CacheType cacheType) {
            this.cacheType = cacheType;
        }

        /**
         * 缓存key前缀
         */
        private String keyPrefix = "snaker::";

        /**
         * 缓存类型
         */
        private CacheType cacheType = CacheType.REDIS;
    }
}
