package com.tyht11.dbutils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.tyht01.bean.Customer;
import com.tyht03.sqlutil.JDBCUtils;

/**
 * 
 * @Description commons-dbutils是Apache提供的一个开源的JDBC类库 封装了对于数据库的增删改查操作
 * @author 脱氧核糖
 * @version
 * @date 2021年4月21日上午11:59:00
 *
 */
public class QueryRunnerTest {
	@Test
	public void testInsert() {
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();
			conn = JDBCUtils.getConnection();
			String sql = "insert into customers(name, email, birth)values(?, ?, ?)";
			int inserCount = runner.update(conn, sql, "打咩", "mie@163.com", "0789-09-23");
			System.out.println(inserCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, null);
		}
	}

	// BeanHandler：ResultSetHandler接口的实现类，用于封装表中的一条记录
	@Test
	public void testQuery1() {
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();
			conn = JDBCUtils.getConnection();
			String sql = "select id, name, email, birth from customers where id = ?";
			BeanHandler<Customer> handler = new BeanHandler<Customer>(Customer.class);
			Customer customer = runner.query(conn, sql, handler, 23);
			System.out.println(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, null);
		}
	}

	// BeanListHandler：ResultSetHandler接口的实现类，用于封装表中的多条记录
	@Test
	public void testQuery2() {
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();
			conn = JDBCUtils.getConnection();
			String sql = "select id, name, email, birth from customers where id < ?";
			BeanListHandler<Customer> handler = new BeanListHandler<Customer>(Customer.class);
			List<Customer> list = runner.query(conn, sql, handler, 23);
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, null);
		}
	}

	// MapHander：ResultSetHandler接口的实现类，用于封装表中的一条记录
	// 将字段及字段对应的值作为map和value
	@Test
	public void testQuery3() {
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();
			conn = JDBCUtils.getConnection();
			String sql = "select id, name, email, birth from customers where id = ?";
			MapHandler handler = new MapHandler();
			Map<String, Object> map = runner.query(conn, sql, handler, 22);
			System.out.println(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, null);
		}
	}

	// MapListHander：ResultSetHandler接口的实现类，用于封装表中的多条记录
	// 将字段及字段对应的值作为map和value，将这些map添加到list中
	@Test
	public void testQuery4() {
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();
			conn = JDBCUtils.getConnection();
			String sql = "select id, name, email, birth from customers where id < ?";
			MapListHandler handler = new MapListHandler();
			List<Map<String, Object>> list = runner.query(conn, sql, handler, 22);
			list.forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, null);
		}
	}

	// 查询其他语句
	@Test
	public void testQuery5() {
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from customers";
			ScalarHandler handler = new ScalarHandler();
			long count = (long) runner.query(conn, sql, handler);
			System.out.println(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, null);
		}
	}

	// 自定义ResultSetHandler的实现类
	@Test
	public void testQuery6() {
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();
			conn = JDBCUtils.getConnection();
			String sql = "select id, name, email, birth from customers where id = ?";
			ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
				@Override
				public Customer handle(ResultSet rs) throws SQLException {
					System.out.println("执行");
					//return new Customer(12, "咩啊", "miea@qq.com", new Date(694664432L));
					if (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String email = rs.getString("email");
						Date birth = rs.getDate("birth");
						Customer customer = new Customer(id, name, email, birth);
						return customer;
					}
					return null;
				}
			};
			Customer customer = runner.query(conn, sql, handler, 23);
			System.out.println(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(conn, null);
		}
	}

}
