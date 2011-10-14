package com.oz.springmvc.test.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JDBCDao extends SimpleJdbcDaoSupport {
	@Autowired
	public void anyMethodName(DataSource dataSource) {
		setDataSource(dataSource);
		
	}
	public NamedParameterJdbcOperations getNamedParameterJdbcOperations(){
		NamedParameterJdbcOperations namedParameterJdbcOperations = getSimpleJdbcTemplate().getNamedParameterJdbcOperations();
		return namedParameterJdbcOperations;
	}
}
