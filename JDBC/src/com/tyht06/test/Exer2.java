package com.tyht06.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;

import com.tyht03.sqlutil.JDBCUtils;

//向examStudent表中添加一条数据
public class Exer2 {

	@Test
	public void testInsert() {
		Scanner in = new Scanner(System.in);
		System.out.print("四级/六级：");
		int type = in.nextInt();
		System.out.print("身份证号：");
		String IDCard = in.next();
		System.out.print("准考证号：");
		String examCard = in.next();
		System.out.print("学生姓名：");
		String studentName = in.next();
		System.out.print("所在城市：");
		String location = in.next();
		System.out.print("考试成绩：");
		int grade = in.nextInt();

		String sql = "insert into examstudent(type, IDCard, examCard, studentName, location, grade)values(?, ?, ?, ?, ?, ?)";
		if (update(sql, type, IDCard, examCard, studentName, location, grade) > 0) {
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
