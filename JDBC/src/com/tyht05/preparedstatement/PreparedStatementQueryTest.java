package com.tyht05.preparedstatement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.tyht01.bean.Customer;
import com.tyht01.bean.Order;
import com.tyht03.sqlutil.JDBCUtils;
/**
 * 
 * @Description 实现针对不同表的通用查询操作
 * @author 脱氧核糖
 * @version
 * @date 2021年4月19日上午9:04:01
 *
\ */
public class PreparedStatementQueryTest {
	
	@Test
	public void testGetForList() {
		String sql = "select id, email, name from customers where id < ?";
		List<Customer> list = getForList(Customer.class, sql, 12);
		list.forEach(System.out::println);
	}
	
	public <T> List<T> getForList(Class<T> clazz, String sql, Object ... args) {
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
			//创建集合对象
			ArrayList<T> list = new ArrayList<T>();
			while (rs.next()) {
				//处理结果集中的每一行，给t对象指定的属性赋值
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
				list.add(t);
			}
			return list;
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

	@Test
	public void testGetInstance() {
		String sql1 = "select id, name, email from customers where id = ?";
		Customer cust = getInstance(Customer.class, sql1, 12);
		System.out.println(cust);
		//注意别名
		String sql2 = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
		Order order = getInstance(Order.class, sql2, 1);
		System.out.println(order);
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
