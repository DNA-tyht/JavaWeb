package com.tyht01.bean;

import java.util.Date;

/**
 * ORM编程思想
 * 一个数据表对应一个java类
 * 一条记录对应java类的一个对象
 * 一个字段对应java类的一个属性
 */

/**
 * 
 * @Description 封装类
 * @author 脱氧核糖
 * @version
 * @date 2021年4月8日上午10:21:12
 *
 */
public class Customer {

	private int id;
	private String name;
	private String email;
	private Date birth;
	
	public Customer() {
		super();
	}

	public Customer(int id, String name, String email, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birth = birth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Customers [id=" + id + ", name=" + name + ", email=" + email + ", birth=" + birth + "]";
	}
		
}
