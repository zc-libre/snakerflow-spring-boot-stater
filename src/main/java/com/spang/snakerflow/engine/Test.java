package com.spang.snakerflow.engine;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhao.cheng
 * @date 2020/12/28 9:55
 */
public class Test {

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("task1", "1");
        params.put("task2", "1");
        params.put("task3", "1");
        params.put("content", "1");
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        System.out.println(parser.parseExpression("#content==200").getValue());
    }
}
