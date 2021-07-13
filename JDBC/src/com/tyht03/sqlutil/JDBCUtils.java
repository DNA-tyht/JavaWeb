package com.tyht03.sqlutil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
*@Description 操作数据的工具类
*@author 脱氧核糖
*@version
*@date 2021年4月7日下午10:27:15
*
 */
public class JDBCUtils {

	/**
	*@Description 连接数据库的操作
	*@author 脱氧核糖
	*@date 2021年4月7日下午10:26:59
	*@return
	*@throws Exception
	 */
	public static Connection getConnection() throws Exception {
		//读取配置文件的四个基本信息
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
		Properties pros = new Properties();
		pros.load(is);
		
		String user = pros.getProperty("user");
		String password = pros.getProperty("password");
		String url = pros.getProperty("url");
		String driverClass = pros.getProperty("driverClass");

		//加载驱动
		Class.forName(driverClass);
		
		//获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/**
	*@Description 关闭资源的操作
	*@author 脱氧核糖
	*@date 2021年4月7日下午10:26:28
	*@param conn
	*@param ps
	 */
	public static void closeResource(Connection conn, PreparedStatement ps) {
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
	/**
	 * 
	 * @Description 关闭资源
	 * @author 脱氧核糖
	 * @date 2021年4月8日上午10:30:38
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
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
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
