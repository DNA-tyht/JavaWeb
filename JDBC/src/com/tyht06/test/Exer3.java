package com.tyht06.test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.tyht01.bean.Student;
import com.tyht03.sqlutil.JDBCUtils;

//根据身份证号或准考证号查询学生成绩信息
public class Exer3 {

	@Test
	public void queryWithIDCardOrExamCard() {
		Scanner in = new Scanner(System.in);
		System.out.println("请选择您要输入的类型：");
		System.out.println("a.准考证号");
		System.out.println("b.身份证号");
		String selection = in.next();
		
		if ("a".equalsIgnoreCase(selection)) {
			System.out.print("请输入准考证号：");
			String IDCard = in.next();
			//前写表名，后写类属性名
			String sql = "select FlowID flowID, Type type, IDCard, ExamCard examCard, StudentName name, Location location, Grade grade from examstudent where IDCard = ?";
			Student student = getInstance(Student.class, sql, IDCard);
			System.out.println(student);
		} else if ("b".equalsIgnoreCase(selection)) {
			System.out.print("请输入身份证号：");
			String examCard = in.next();
			//前写表名，后写类属性名
			String sql = "select FlowID flowID, Type type, IDCard, ExamCard examCard, StudentName name, Location location, Grade grade from examstudent where examCard = ?";
			Student student = getInstance(Student.class, sql, examCard);
			System.out.println(student);
		} else {
			System.out.println("您的输入有误，请重新进入程序");
		}
	}
	
	// 针对不同的表的通用的查询操作，返回表中的一条记录
	public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();

			// 获取结果集的源数据
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			// 从源数据中获取列数
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				T t = clazz.newInstance();

				// 处理结果集中一行数据的每一列
				for (int i = 0; i < columnCount; i++) {
					Object columValue = rs.getObject(i + 1);
					// 获取每个列的列名
					String columnName = rsmd.getColumnLabel(i + 1);
					// 给t对象指定的colunName属性赋值为colunValue
					// 反射
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(t, columValue);

				}
				return t;
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, ps, rs);
		}
		return null;
	}
}
