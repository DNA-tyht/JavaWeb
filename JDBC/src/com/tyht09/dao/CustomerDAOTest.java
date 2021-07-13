package com.tyht09.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.tyht01.bean.Customer;
import com.tyht03.sqlutil.JDBCUtils;

public class CustomerDAOTest {
	private CustomersDAO dao = new CustomersDAO();
	
	@Test
	public void testInsert() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		Customer cust = new Customer(1, "小野猪", "xiaoyezhu@163.com", new Date(629649170472L));
		dao.insert(conn, cust);
		JDBCUtils.closeResource(conn, null);
	}

	@Test
	public void testDeleteById() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		dao.deleteById(conn, 13);
		JDBCUtils.closeResource(conn, null);
	}

	@Test
	public void testUpdateConnectionCustomer() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		Customer cust = new Customer(24, "凌雪阁", "xiaoyezhu@163.com", new Date(629649170472L));
		dao.update(conn, cust);
		JDBCUtils.closeResource(conn, null);	
	}

	@Test
	public void testGetCustomerById() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		Customer cust = dao.getCustomerById(conn, 23);
		System.out.println(cust);
		JDBCUtils.closeResource(conn, null);
	}

	@Test
	public void testGetAll() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		List<Customer> list = dao.getAll(conn);
		System.out.println(list);
		JDBCUtils.closeResource(conn, null);
	}

	@Test
	public void testGetCount() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		long count = dao.getCount(conn);
		System.out.println(count);
		JDBCUtils.closeResource(conn, null);
	}

	@Test
	public void testGetMaxBirth() throws Exception {
		Connection conn = JDBCUtils.getConnection();
		Date maxBirth = dao.getMaxBirth(conn);
		System.out.println(maxBirth);
		JDBCUtils.closeResource(conn, null);
	}

}
