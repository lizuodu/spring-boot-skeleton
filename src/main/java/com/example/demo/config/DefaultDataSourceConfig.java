package com.example.demo.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.interceptor.SqlInterceptor;

/**
 * 默认数据源配置：Mybatis, Druid整合
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper.master", sqlSessionFactoryRef = "sqlSessionFactory")
public class DefaultDataSourceConfig {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.druid.initial-size}")
	private int initialSize;

	@Value("${spring.datasource.druid.min-idle}")
	private int minIdle;

	@Value("${spring.datasource.druid.max-active}")
	private int maxActive;

	@Value("${spring.datasource.druid.max-wait}")
	private int maxWait;

	@Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.druid.validation-query}")
	private String validationQuery;

	@Value("${spring.datasource.druid.test-while-idle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.druid.test-on-borrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.druid.test-on-return}")
	private boolean testOnReturn;

	@Value("${spring.datasource.druid.pool-prepared-statements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.druid.filters}")
	private String filters;

	@Value("{spring.datasource.druid.connection-properties}")
	private String connectionProperties;

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);

		// 具体配置
		dataSource.setInitialSize(initialSize);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxWait(maxWait);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setPoolPreparedStatements(poolPreparedStatements);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		try {
			dataSource.setFilters(filters);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dataSource.setConnectionProperties(connectionProperties);
		return dataSource;
	}

	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(this.dataSource());
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource((DataSource) applicationContext.getBean("dataSource"));
		sessionFactory.setVfs(SpringBootVFS.class);
		sessionFactory.setTypeAliasesPackage("com.example.demo.model");
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mapper/master/*.xml"));

		// MyBatis配置
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(false);
		sessionFactory.setConfiguration(configuration);

		// sql拦截插件
		SqlInterceptor sqlInterceptor = new SqlInterceptor();

		sessionFactory.setPlugins(new Interceptor[] { sqlInterceptor });

		return sessionFactory;
	}

}
