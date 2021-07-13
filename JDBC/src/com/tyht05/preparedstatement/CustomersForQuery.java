package com.tyht05.preparedstatement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.tyht01.bean.Customer;
import com.tyht03.sqlutil.JDBCUtils;

/**
*@Description 针对Customers表的查询
*@author 脱氧核糖
*@version
*@date 2021年4月8日上午9:39:53
*
 */
public class CustomersForQuery {

	
	/**
	 * 
	 * @Description 针对Customer表的一条查询操作
	 * @author 脱氧核糖
	 * @date 2021年4月8日上午10:42:53
	 *
	 */
	@Test
	public void testQuery1() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select id, name, email, birth from customers where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 1);
			//执行查询语句,返回结果集
			resultSet = ps.executeQuery();
			
			//判断结果集的下一条是否有数据
			//有数据返回true，并指针下移
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				Date birth = resultSet.getDate(4);
				//方式一SOUT
				//方式二数组
				//方式三集合
				//方式四对象
				Customer customer = new Customer(id, name, email, birth);
				System.out.println(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, ps, resultSet);
		}
		
	}
	
	/**
	 * 
	 * @Description 针对Customer表通用的查询操作
	 * @author 脱氧核糖
	 * @throws Exception 
	 * @date 2021年4月8日上午10:40:26
	 * 
	 */
	public Customer queryFormatCustomers(String sql, Object ...args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			
			//获取结果集的源数据
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			//从源数据中获取列数
			int columnCount = rsmd.getColumnCount();
			if(rs.next()) {
				Customer customer = new Customer();
				
				//处理结果集中一行数据的每一列
				for(int i = 0; i < columnCount; i++) {
					Object columValue = rs.getObject(i + 1);
					//获取每个列的列名
					String columnName = rsmd.getColumnName(i + 1);
					//给customer对象指定的colunName属性赋值为colunValue
					//反射
					Field field = Customer.class.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(customer, columValue);
				}
				return customer;
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
	public void testQqueryFormatCustomers() {
		String sql1 = "select id, name, birth from customers where id = ?";
		String sql2 = "select name, birth from customers where name = ?";
		Customer customer1 = queryFormatCustomers(sql1, 13);
		Customer customer2 = queryFormatCustomers(sql2, "哪吒");
		System.out.println(customer1);
		System.out.println(customer2);
	}
	
}
