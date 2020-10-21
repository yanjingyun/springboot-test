package com.yjy.core.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

/**
 * Order数据库配置类
 */
@Configuration
@MapperScan(basePackages = "com.yjy.order.mapper", sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderDataSourceConfig {

	/**
	 * 创建 XADataSource
	 */
	@Bean("orderDataSource")
	public DataSource orderDataSource(OrderConfig orderConfig) throws SQLException {

		// 1、创建Mysql XADataSource
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(orderConfig.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(orderConfig.getPassword());
		mysqlXaDataSource.setUser(orderConfig.getUserName());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		// 2、将本地事务注册到 Atomikos 全局事务
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName(orderConfig.getUniqueResourceName());
		xaDataSource.setMinPoolSize(orderConfig.getMinPoolSize());
		xaDataSource.setMaxPoolSize(orderConfig.getMaxPoolSize());
		xaDataSource.setMaxLifetime(orderConfig.getMaxLifeTime());
		xaDataSource.setBorrowConnectionTimeout(orderConfig.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(orderConfig.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(orderConfig.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(orderConfig.getMaxIdleTime());
		xaDataSource.setTestQuery(orderConfig.getTestQuery());

		return xaDataSource;
	}

	/**
	 * 创建 SQL会话工厂
	 */
	@Bean("orderSqlSessionFactory")
	public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * 创建订单 SqlSession模板
	 */
	@Bean("orderSqlSessionTemplate")
	public SqlSessionTemplate orderSqlSessionTemplate(
			@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}