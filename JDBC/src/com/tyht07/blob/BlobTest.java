package com.tyht07.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.junit.Test;

import com.mysql.cj.jdbc.Blob;
import com.tyht01.bean.Customer;
import com.tyht03.sqlutil.JDBCUtils;

/**
 * 
 * @Description 使用PreparedStatement操作Blob类型的数据
 * @author 脱氧核糖
 * @version
 * @date 2021年4月19日下午9:44:26
 *
 */
public class BlobTest {
	// 向数据表customers中插入Blob类型的字段
	@Test
	public void testInsert() {
		Connection conn = null;
		PreparedStatement ps = null;
		FileInputStream fis = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into customers (name, email, birth, photo)values(?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, "小野猪");
			ps.setObject(2, "xiaoyezhu@qq.com");
			ps.setObject(3, "0728-10-31");
			fis = new FileInputStream(new File("小野猪.jpg"));
			ps.setBlob(4, fis);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils.closeResource(conn, ps);
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	// 查询数据表customers中Blob类型的字段
	@Test
	public void testQuery() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select id, name, email, birth, photo from customers where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 22);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);// getInt("id")
				String name = rs.getString(2);// getString("name")
				String email = rs.getString(3);// getString("email")
				Date birth = rs.getDate(4);// geyDate("birth")
				Customer cust = new Customer(id, name, email, birth);
				System.out.println(cust);
				// 将Blob类型的字段下载下来，以文件的方式保存下来
				Blob photo = (Blob) rs.getBlob("photo");
				is = photo.getBinaryStream();
				fos = new FileOutputStream("野猪.jpg");
				byte[] buffer = new byte[1024];
				int length = is.read(buffer);
				while (length != -1) {
					fos.write(buffer, 0, length);
					length = is.read(buffer);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils.closeResource(conn, ps, rs);
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
