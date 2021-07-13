package com.tyht02.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

public class ConnectionTest1 {

//	@Test
//	public void testConnection() throws Exception{
//		Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动类
//		Connection conn;
//		String DB_URL="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
//		//testjdbc是数据库的名字
//		conn = DriverManager.getConnection(DB_URL,"root","20010915tyht");
//		//建立连接
//		System.out.println(conn);//打印对象，看是否建立成功
//	}
//	
//	//方式一
//	@Test
//	public void testConnection1() throws SQLException{
//		Driver driver = new com.mysql.cj.jdbc.Driver();
//		String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
//		Properties info = new Properties();
//		info.setProperty("username", "root");
//		info.setProperty("password", "20010915tyht");
//		
//		Connection connection = driver.connect(url, info);
//		System.out.println(connection);
//	}
//	
//	//方式二
//	//对方式一 的迭代，不出现第三方的API，使程序有更好的移植性
//	@Test
//	public void testConnection2() throws Exception {
//		Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
//		Driver driver = (Driver)clazz.newInstance();
//		String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
//		
//		Properties info = new Properties();
//		info.setProperty("username", "root");
//		info.setProperty("password", "20010915tyht");
//		
//		Connection connection = driver.connect(url, info);
//		System.out.println(connection);
//	}
//	
//	//方式三
//	//使用DriverManager替换Driver
//	@Test
//	public void testConnection3() throws Exception {
//		//获取 Driver 实现类的对象
//		Class clazz = Class.forName("com.sql.cj.jdbc.Driver");
//		Driver driver = (Driver)clazz.newInstance();
//		
//		//提供三个连接的基本信息
//		String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
//		String user = "root";
//		String password = "20010915tyht";
//		
//		//注册驱动
//		DriverManager.registerDriver(driver);
//		//获取连接
//		Connection connection = DriverManager.getConnection(url, user, password);
//		System.out.println(connection);
//	}
//	
//	//方式四
//	//
//	@Test
//	public void testConnection4() throws Exception {
//		
//		//提供三个连接的基本信息
//		String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
//		String user = "root";
//		String password = "20010915tyht";
//		//获取 Driver 实现类的对象
//		Class clazz = Class.forName("com.sql.cj.jdbc.Driver");
//		
////		Driver driver = (Driver)clazz.newInstance();
////		//注册驱动
////		DriverManager.registerDriver(driver);
//		//获取连接
//		Connection connection = DriverManager.getConnection(url, user, password);
//		System.out.println(connection);
//	}
//	
	//方式五
	//配置文件
	@Test
	public void geyConnection5() throws Exception {
		//读取配置文件的四个基本信息
		InputStream is = ConnectionTest1.class.getClassLoader().getResourceAsStream("jdbc.properties");
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
		System.out.println(conn);
	}
}
