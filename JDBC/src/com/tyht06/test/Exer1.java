package com.tyht06.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;

import com.tyht03.sqlutil.JDBCUtils;

//向Customer表中更新一条数据
public class Exer1 {
	@Test
	public void test1() throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入用户名：");
		String name = in.next();
		System.out.print("请输入邮箱：");
		String email = in.next();
		System.out.print("请输入生日：");
		String birth = in.next();
		String sql = "insert into customers(name, email, birth)values(?, ?, ?)";
		if (update(sql, name, email, birth) > 0) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}
	}

	// 通用的增删改操作，更新
	public int update(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
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

			JDBCUtils.closeResource(conn, ps);
		}
		return 0;
	}
}
