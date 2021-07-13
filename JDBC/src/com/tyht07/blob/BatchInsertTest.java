package com.tyht07.blob;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.tyht03.sqlutil.JDBCUtils;

/**
 * 
 * @Description 此时的 批量操作主要指插入
 * @author 脱氧核糖
 * @version
 * @date 2021年4月20日下午1:18:08
 *
 */

// 向goods表中插入20000条数据
public class BatchInsertTest {
	@Test
	public void testInsert1() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			long start = System.currentTimeMillis();
			conn = JDBCUtils.getConnection();
			String sql = "insert into goods(name)values(?)";
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= 200; i++) {
				ps.setObject(1, "name_" + i);
				ps.execute();
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils.closeResource(conn, ps);
		}
	}

	/*
	 * mySQL服务器默认关闭批处理 需要在配置文件url后加上?rewriteBatchedStatements=true
	 * 使用新的mySQL驱动：5.1.37
	 */
	@Test
	public void testInsert2() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			long start = System.currentTimeMillis();
			conn = JDBCUtils.getConnection();
			String sql = "insert into goods(name)values(?)";
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= 2000; i++) {
				ps.setObject(1, "name_" + i);
				ps.addBatch();
				if (i % 500 == 0) {
					// 执行batch
					ps.executeBatch();
					// 清空batch
					ps.clearBatch();
				}
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils.closeResource(conn, ps);
		}
	}

	@Test
	public void testInsert3() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			long start = System.currentTimeMillis();
			conn = JDBCUtils.getConnection();
			//这只不允许自动提交数据
			String sql = "insert into goods(name)values(?)";
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= 2000; i++) {
				ps.setObject(1, "name_" + i);
				ps.addBatch();
				if (i % 500 == 0) {
					// 执行batch
					ps.executeBatch();
					// 清空batch
					ps.clearBatch();
				}
			}
			//提交数据
			conn.commit();
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils.closeResource(conn, ps);
		}
	}

}
