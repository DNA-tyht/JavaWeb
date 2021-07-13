package com.tyht10.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Druid {
	@Test
	public void getConnection1() throws Exception {
		Properties pros = new Properties();
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
		pros.load(is);
		DataSource source = DruidDataSourceFactory.createDataSource(pros);
		Connection conn = source.getConnection();
		System.out.println(conn);
	}
}
