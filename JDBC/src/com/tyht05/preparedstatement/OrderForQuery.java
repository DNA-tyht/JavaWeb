package com.tyht05.preparedstatement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import com.tyht01.bean.Order;
import com.tyht03.sqlutil.JDBCUtils;

/**
 * 
 * @Description 针对Order表的查询操作
 * @author 脱氧核糖
 * @version
 * @date 2021年4月8日下午6:43:58
 *
 */
public class OrderForQuery {
/**
 * 
 * 表的字段名与类的属性名不相同的情况
 * 1.必须在声明SQL时，使用类的属性名来命名字段的别名
 * 2.使用ResultSetMetaData时，用getColumnLabel()替换getColumnName()
 * 
 */
	
	
	@Test
	public void testOrderForQuery() {
		//写别名
		String sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
		Order order = orderForQuery(sql, 1);
		System.out.println(order);
	}
	
	public Order orderForQuery(String sql, Object ...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			//获取结果集
			rs = ps.executeQuery();
			//获取结果集的源数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取列数
			int columnCount = rsmd.getColumnCount();
			if(rs.next()) {
				Order order = new Order();
				for(int i = 0; i < columnCount; i++) {
					//获取每个列的列值
					Object columValue = rs.getObject(i + 1);
					//获取列的列名:getColumnName()
					//获取列的别名:getColumnLabel()推荐使用
					String columnName = rsmd.getColumnLabel(i + 1);
					Field field = Order.class.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(order, columValue);
				}
				return order;
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

	@Test
	public void testQuery1() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select order_id, order_name, order_date from `order` where order_id = ?";
			ps = conn.prepareStatement(sql);
			//填充占位符
			ps.setObject(1, 1);
			rs = ps.executeQuery();
			if(rs.next()) {
				int orderId  = rs.getInt(1);
				String orderName = rs.getString(2);
				Date orderDate = rs.getDate(3);
				Order order = new Order(orderId, orderName, orderDate);
				System.out.println(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, ps, rs);
		}

	}
}
