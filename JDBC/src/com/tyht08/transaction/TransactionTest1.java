package com.tyht08.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.tyht03.sqlutil.JDBCUtils;

public class TransactionTest1 {

	// 1.0：未考虑数据库事务的转账操作
	// 2.0：考虑数据库事务的转账操作
	// 针对于数据表user_table，AA用户给BB用户转账100
	@Test
	public void testUpdate() {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			// 取消数据的自动提交功能
			conn.setAutoCommit(false);
			String sql1 = "update user_table set balance = balance - 100 where user = ?";
			String sql2 = "update user_table set balance = balance + 100 where user = ?";
			update(conn, sql1, "AA");
			// 模拟网络异常
			System.out.println(10 / 0);
			update(conn, sql2, "BB");
			System.out.println("转账成功");
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 回滚数据

		} finally {
			// 还原数据的自动提交功能
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBCUtils.closeResource(conn, null);
		}
	}

	// 2.0：考虑数据库事务
	// 通用的增删改操作，更新
	public int update(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			// 判定SQL中的占位符的个数
			// 与可变形参的长度有关
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(null, ps);
		}
		return 0;
	}
	
	
}
