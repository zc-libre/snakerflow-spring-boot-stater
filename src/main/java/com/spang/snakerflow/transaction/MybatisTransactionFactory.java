package com.spang.snakerflow.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * mybatis自定义的事务工厂
 * @author yuqs
 * @since 1.0
 */
public class MybatisTransactionFactory extends SpringManagedTransactionFactory {

	@Override
	public void setProperties(Properties props) {
		// not needed in this version
	}

	@Override
	public Transaction newTransaction(Connection conn) {
		throw new UnsupportedOperationException("New transactions require a DataSource");
	}

	@Override
	public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
		return new MybatisTransaction(dataSource);
	}
}
