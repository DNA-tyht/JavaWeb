package com.tyht05.preparedstatement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.tyht03.sqlutil.JDBCUtils;
import com.tyht04.statement.User;

/**
 * 
 * @Description 解决SQL注入问题
 * @author 脱氧核糖
 * @version
 * @date 2021年4月19日上午9:45:57
 *
 */
public class PreparedStatmentTest {
	
	/**
	 * 
	 * @Description 解决了SQL注入问题
	 * PreparedStatment能够操作Blob的数据
	 * 可以实现更高效的批量插入
	 * @author 脱氧核糖
	 * @date 2021年4月19日上午10:06:22
	 *
	 */
	@Test
	public void testLogin() {
		Scanner scan = new Scanner(System.in);

		System.out.print("用户名：");
		String user = scan.nextLine();
		System.out.print("密   码：");
		String password = scan.nextLine();

		String sql = "SELECT user,password FROM user_table WHERE user = ? and password = ?";
		User returnUser = getInstance(User.class, sql, user, password);
		if (returnUser != null) {
			System.out.println("登陆成功!");
		} else {
			System.out.println("用户名不存在或密码错误！");
		}
	}
		
	/**
	 * 
	 * @Description 针对不同的表的通用的查询操作，返回表中的一条记录
	 * @author 脱氧核糖
	 * @date 2021年4月19日上午9:27:14
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 *
	 */
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
