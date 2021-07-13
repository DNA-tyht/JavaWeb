package com.tyht02.connection;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionTest {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动类
		Connection conn;
		String DB_URL="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai";
		//testjdbc是数据库的名字
		conn = DriverManager.getConnection(DB_URL,"root","20010915tyht");
		//建立连接
		System.out.println(conn);//打印对象，看是否建立成功
	}
 
}