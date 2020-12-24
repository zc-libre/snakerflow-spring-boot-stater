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

import com.spang.snakerflow.prop.SnakerFlowProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.snaker.engine.SnakerException;
import org.snaker.engine.access.ScriptRunner;
import org.snaker.engine.access.jdbc.JdbcAccess;
import org.snaker.engine.access.jdbc.JdbcHelper;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * mybatis方式的数据库访问
 * @author yuqs
 * @since 1.0
 */
@Slf4j
public class MybatisAccess extends JdbcAccess {
	/**
	 * mybatis的sqlSessionFactory
	 */
	private SqlSessionFactory sqlSessionFactory;
	private final SqlSessionTemplate sqlSessionTemplate;

	public MybatisAccess(SqlSessionFactory sqlSessionFactory,
						 SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionFactory = sqlSessionFactory;
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	/**
	 * setter
	 * @param sqlSessionFactory /
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public void initialize(Object accessObject) {
		if(accessObject == null) {
			return;
		}
		if(accessObject instanceof SqlSessionFactory) {
			this.sqlSessionFactory = (SqlSessionFactory)accessObject;
			setDataSource(this.sqlSessionFactory.getConfiguration().getEnvironment().getDataSource());

		}
	}

	private SqlSession getSession() {
		return this.sqlSessionTemplate;
	}

	@Override
	public boolean isORM() {
		return false;
	}

	@Override
	protected Connection getConnection() {
		return getSession().getConnection();
	}

}
