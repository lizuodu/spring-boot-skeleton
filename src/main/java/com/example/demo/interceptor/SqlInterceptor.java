package com.example.demo.interceptor;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyBatis拦截器
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }),
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class SqlInterceptor implements Interceptor {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final static String MAGIC_CHAR_SEMICOLON = ";";
	private final static String SQL_METHOD = "com.example.demo.mapper.master.UserMapper.findAll";

	@Override
	public Object intercept(Invocation arg0) throws Throwable {

		LOGGER.debug("intercept---->");

		Object target = arg0.getTarget();

		if (target instanceof StatementHandler) {
			StatementHandler handler = (StatementHandler) arg0.getTarget();

			// 由于mappedStatement中有我们需要的方法id,但却是protected的，所以要通过反射获取
			MetaObject metaStatementHandler = SystemMetaObject.forObject(handler);
			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
					.getValue("delegate.mappedStatement");

			// BoundSql boundSql = handler.getBoundSql();
			// 获取SQL
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			String sql = boundSql.getSql();
			// 去掉所有SQL结尾分号
			if (sql.endsWith(MAGIC_CHAR_SEMICOLON)) {
				sql = sql.substring(0, sql.length() - 1);
			}

			// 获取目标方法签名
			String methodSignature = mappedStatement.getId();
			LOGGER.debug("intercept---->" + methodSignature);
			
			if (SQL_METHOD.equals(methodSignature)) {
				sql = "SELECT * FROM (" + sql + ") AS T WHERE 1=1";
				metaStatementHandler.setValue("delegate.boundSql.sql", sql);
				LOGGER.debug("intercept---->" + sql);
			}
		}

		return arg0.proceed();
	}

	@Override
	public Object plugin(Object arg0) {
		if (arg0 instanceof StatementHandler) {
			return Plugin.wrap(arg0, this);
		} else {
			return arg0;
		}
	}

	@Override
	public void setProperties(Properties arg0) {
	}

}
