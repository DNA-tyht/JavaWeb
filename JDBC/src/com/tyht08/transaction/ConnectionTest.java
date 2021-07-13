package com.tyht08.transaction;

import java.sql.Connection;

import org.junit.Test;

import com.tyht03.sqlutil.JDBCUtils;
public class ConnectionTest {

	@Test
	public void testGetConnection() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		System.out.println(conn);
	}
}
