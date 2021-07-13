package com.tyht09.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.tyht03.sqlutil.JDBCUtils;

/**
 * 
 * @Description 封装了数据表的通用操作
 * @author 脱氧核糖
 * @version
 * @date 2021年4月20日下午9:14:37
 *
 */
public class BaseDAO {

	// 2.0：考虑数据库事务
	// 针对不同的表的通用的查询操作，返回表中的一条记录
	public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();

			// 获取结果集的源数据
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			// 从源数据中获取列数
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				T t = clazz.newInstance();

				// 处理结果集中一行数据的每一列
				for (int i = 0; i < columnCount; i++) {
					Object columValue = rs.getObject(i + 1);
					// 获取每个列的列名
					String columnName = rsmd.getColumnLabel(i + 1);
					// 给t对象指定的colunName属性赋值为colunValue
					// 反射
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(t, columValue);
				}
				return t;
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
			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}

	// 2.0：考虑数据库事务
	// 通用的增删改操作，更新
	public int update(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			// 判定SQL中的占位符的个数
			// 与可变形参的长度有关
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(null, ps);
		}
		return 0;
	}

	// 2.0：考虑数据库事务
	// 通用的查询操作，返回多条记录构成的集合
	public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();

			// 获取结果集的源数据
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			// 从源数据中获取列数
			int columnCount = rsmd.getColumnCount();
			// 创建集合对象
			ArrayList<T> list = new ArrayList<T>();
			while (rs.next()) {
				// 处理结果集中的每一行，给t对象指定的属性赋值
				T t = clazz.newInstance();

				// 处理结果集中一行数据的每一列
				for (int i = 0; i < columnCount; i++) {
					Object columValue = rs.getObject(i + 1);
					// 获取每个列的列名
					String columnName = rsmd.getColumnLabel(i + 1);
					// 给t对象指定的colunName属性赋值为colunValue
					// 反射
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(t, columValue);
				}
				list.add(t);
			}
			return list;
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
			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}

	// 用于查询特殊值通用的方法
	public <E> E getValue(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(1 + 1, args[i]);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return (E) rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}

}
