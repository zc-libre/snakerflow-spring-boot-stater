package com.github.snakerflow.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.snaker.engine.access.jdbc.JdbcAccess;;

import java.sql.Connection;

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
