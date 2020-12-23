/*
 *  Copyright 2013-2015 www.snakerflow.com.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.spang.snakerflow.mybaits;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.access.jdbc.JdbcHelper;
import org.snaker.engine.access.transaction.TransactionObjectHolder;
import org.snaker.engine.helper.ConfigHelper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * mybatis操作帮助类
 * @author yuqs
 * @since 1.0
 */
public abstract class MybatisHelper {
	private static final Logger log = LoggerFactory.getLogger(MybatisHelper.class);
	private static final String SCAN_PACKAGE = "org.snaker.engine.entity";
	private static SqlSessionFactory sqlSessionFactory = null;
	private static final String[] resources = new String[] {
		"mapper/process.xml",
		"mapper/order.xml",
		"mapper/task.xml",
		"mapper/task-actor.xml",
		"mapper/hist-order.xml",
		"mapper/hist-task.xml",
		"mapper/hist-task-actor.xml",
		"mapper/query.xml",
		"mapper/hist-query.xml"
	};
	
	/**
	 * 在没有任何sqlSessionFactory注入的情况下，默认使用mybatis.cfg.xml配置初始化
	 */
	public static void initialize() {
		InputStream in;
		try {
			in = Resources.getResourceAsStream("mybatis.cfg.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in, ConfigHelper.getProperties());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用DataSource初始化SqlSessionFactory
	 * @param ds 数据源
	 */
	public static void initialize(DataSource ds) {
		TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
		Environment environment = new Environment("snaker", transactionFactory, ds);
		Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAliases(SCAN_PACKAGE, Object.class);
        if (log.isInfoEnabled()) {
        	Map<String, Class<?>> typeAliases = configuration.getTypeAliasRegistry().getTypeAliases();
        	for(Entry<String, Class<?>> entry : typeAliases.entrySet()) {
            	log.info("Scanned class:[name=" + entry.getKey() + ",class=" + entry.getValue().getName() + "]");
        	}
        }
		try {
			for(String resource : resources) {
				InputStream in = Resources.getResourceAsStream(resource);
				XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(in, configuration, resource, configuration.getSqlFragments());
				xmlMapperBuilder.parse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ErrorContext.instance().reset();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}
	
	public static Connection getConnection() throws SQLException {
		if(sqlSessionFactory == null) {
    		log.debug("don't found available sqlSessionFactory");
    		sqlSessionFactory = getSqlSessionFactory();
    	}
    	DataSource dataSource = sqlSessionFactory.
    			getConfiguration().
    			getEnvironment().
    			getDataSource();
    	return JdbcHelper.getConnection(dataSource);
	}
	
	public static SqlSession getSession(SqlSessionFactory sqlSessionFactory) {
		SqlSession session = (SqlSession)TransactionObjectHolder.get();
		if(session != null) return session;
    	if(sqlSessionFactory != null) {
	    	return sqlSessionFactory.openSession();
    	}
		return getSqlSessionFactory().openSession();
	}

	/**
	 * 返回SqlSessionFactory
	 * @return SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		if(sqlSessionFactory == null) {
			synchronized (MybatisHelper.class) {
				if(sqlSessionFactory == null) {
					initialize();
				}
			}
		}
		return sqlSessionFactory;
	}
}
