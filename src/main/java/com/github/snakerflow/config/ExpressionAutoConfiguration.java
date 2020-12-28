package com.github.snakerflow.config;

import com.github.snakerflow.engine.SpelExpression;
import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.impl.JuelExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhao.cheng
 * @date 2020/12/28 14:00
 */
@Slf4j
@Configuration
public class ExpressionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "expression-type", havingValue = "juel", matchIfMissing = true)
    public JuelExpression juelExpression() {
        log.info("决策表达式类型为： JuelExpression");
        return new JuelExpression();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "snaker.flow", name = "expression-type", havingValue = "spel")
    public SpelExpression spelExpression() {
        log.info("决策表达式类型为： SpelExpression");
        return new SpelExpression();
    }
}
