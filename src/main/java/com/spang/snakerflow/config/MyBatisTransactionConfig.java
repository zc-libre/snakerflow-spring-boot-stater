package com.spang.snakerflow.config;

import com.spang.snakerflow.transaction.MybatisTransactionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zhao.cheng
 * @date 2020/12/24 18:14
 */

public class MyBatisTransactionConfig {

    public TransactionFactory mybatisTransactionFactory() {
        return new MybatisTransactionFactory();
    }
}
