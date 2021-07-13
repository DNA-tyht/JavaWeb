package com.tyht10.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	@Test
	public void testGetConnection1() throws Exception {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=Asia/Shanghai");
		cpds.setUser("root");
		cpds.setPassword("20010915tyht");
		// 设置相关参数，对数据库连接池进行管理
		// 设置初始时数据库连接池的连接数
		cpds.setInitialPoolSize(10);

		Connection conn = cpds.getConnection();
		System.out.println(conn);
	}

	// 使用配置文件
	@Test
	public void testGetConnection2() throws SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource("DNAc3p0");
		Connection conn = cpds.getConnection();
		System.out.println(conn);
	}

}
