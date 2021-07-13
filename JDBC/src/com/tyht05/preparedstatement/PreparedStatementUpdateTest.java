package com.tyht05.preparedstatement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.junit.Test;

import com.tyht03.sqlutil.JDBCUtils;

/**
 * @Description 使用PreparedStatemnt替换 Statement,实现对数据表的增删改查
 * @author 脱氧核糖
 * @version
 * @date 2021年4月7日下午10:30:15
 *
 */

public class PreparedStatementUpdateTest {

	/**
	 * @Description 向customers表中添加一条记录
	 * @author 脱氧核糖
	 * @date 2021年4月7日下午10:30:30
	 */
	@Test
	public void testInsert() {
		//获取链接
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//读取配置文件
			InputStream is = PreparedStatementUpdateTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties pros = new Properties();
			pros.load(is);
			String user = pros.getProperty("user");
			String password = pros.getProperty("password");
			String url = pros.getProperty("url");
			String driverClass = pros.getProperty("driverClass");
			
			//加载驱动
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);

			//预编译SQL语句,返回PreparedStatement的实例
			String sql = "insert into customers(name, email, birth)values(?,?,?)";//占位符
			ps = conn.prepareStatement(sql);
			//填充占位符
			ps.setString(1, "哪吒");
			ps.setString(2, "nezha@geamil.com");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf.parse("1000-01-01");
			ps.setDate(3, new java.sql.Date(date.getTime()));
			
			//执行SQL
			ps.execute();
			System.out.println("插入成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//资源关闭
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Description 修改Customers表中的一条记录
	 * @author 脱氧核糖
	 * @date 2021年4月7日下午10:30:55
	 */
	@Test
	public void testUpdate() {
		//获取数据库的链接
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			//预编译SQL语句，返回PreparedStatement类的实例
			String sql = "update customers set name = ? where id = ?";
			ps = conn.prepareStatement(sql);
			//填充占位符
			ps.setObject(1, "莫扎特");
			ps.setObject(2, 18);
			//执行修改操作
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//资源关闭
			JDBCUtils.closeResource(conn, ps);
		}
	}
	
	/**
	 * @Description 通用的增删改操作，更新
	 * @author 脱氧核糖
	 * @date 2021年4月7日下午10:40:51
	 */
	@Test
	public void updata(String sql, Object ...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			//判定SQL中的占位符的个数
			//与可变形参的长度有关
			for(int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCUtils.closeResource(conn, ps);
		}
		
	}

	
}
