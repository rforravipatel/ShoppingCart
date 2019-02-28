package com.demo.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.demo.model.Product;

public class ProductService {

	Connection con;
//	FileInputStream fileInputStream;
	Properties properties = new Properties();;
	String driverClassName, url, username, password;
	
	public ProductService() {
		try {
//			fileInputStream = new FileInputStream();
			
			properties.load(getClass().getResourceAsStream("db.prop"));
			driverClassName = (String)properties.get("driverClassName");
			url = (String)properties.get("url");
			username = (String)properties.get("username");
			password = (String)properties.get("password");
			
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> viewAll() {

		ArrayList<Product> list = new ArrayList<Product>();

		try {
			PreparedStatement ps = con.prepareCall("select * from products");
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("No data!");
			}

			do {
				Product product = new Product(rs.getString(2), rs.getDouble(3), rs.getString(4));
				product.setId(rs.getInt(1));
				list.add(product);
			} while (rs.next());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insert(String name, double price, String seller) {

		try {
			PreparedStatement ps = con.prepareStatement("insert into products(name, price, seller) values(?,?,?)");
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setString(3, seller);
			return ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int deleteProduct(int id) {
		System.out.println(id);
		try {
			PreparedStatement ps = con.prepareStatement("delete from products where id=?");
			ps.setInt(1, id);

			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Product getRecordById(int id) {
		Product product = null;
		try {
			PreparedStatement ps = con.prepareStatement("Select * from products where id=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getString(2), rs.getDouble(3), rs.getString(4));
				product.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return product;
	}
	
	public int updateProduct(int id, String name, double price, String seller) {
		try {
			PreparedStatement ps = con.prepareStatement("update products set name=?,price=?,seller=? where id=?");
			ps.setInt(4, id);
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setString(3, seller);
			return ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}