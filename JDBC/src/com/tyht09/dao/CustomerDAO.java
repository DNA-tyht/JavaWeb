package com.tyht09.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.tyht01.bean.Customer;

/**
 * 
 * @Description 此接口用于规范针对于customers表的常用操作
 * @author 脱氧核糖
 * @version
 * @date 2021年4月20日下午9:26:03
 *
 */
public interface CustomerDAO {
	// 将customer对象添加到数据库中
	void insert(Connection conn, Customer cust);

	// 针对指定的id，删除表中的一条记录
	void deleteById(Connection conn, int id);

	// 针对内存中的customer对象，修改表中指定的记录
	void update(Connection conn, Customer cust);

	// 针对指定的id查询得到对应的customer对象
	Customer getCustomerById(Connection conn, int id);

	// 查询所有的记录
	List<Customer> getAll(Connection conn);

	// 返回数据表中的数据条目数
	long getCount(Connection conn);

	// 返回表中最大的生日
	Date getMaxBirth(Connection conn);

}
