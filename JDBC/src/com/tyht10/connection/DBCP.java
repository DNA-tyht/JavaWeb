package com.tyht10.connection;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class DBCP {
	@Test
	public void testGetConnection1() throws SQLException {
		// 创建DBCP的数据库连接池
		BasicDataSource source = new BasicDataSource();
		// 设置基本信息
		source.setDriverClassName("com.mysql.cj.jdbc.Driver");
		source.setUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=UTC");
		source.setUsername("root");
		source.setPassword("20010915tyht");

		Connection conn = source.getConnection();
		System.out.println(conn);
	}

	@Test
	public void testGetConnection2() throws Exception {
		Properties pros = new Properties();
		FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
		pros.load(is);
		DataSource source = BasicDataSourceFactory.createDataSource(pros);
		Connection conn = source.getConnection();
		System.out.println(conn);
	}

}
