package com.edu.bit.cs.lemondy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

/**
 * 
 * @author lemon
 * create table bank(
 * 	uid string,
 * 	uname string,
 *  idcard string,
 * 	upaswd string,
 * 	sex byte,
 * 	age smallint,
 * 	phoneNumber string,
 * 	address	string
 * );
 */

public class DBManager {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/"; // database: bandk
	private static final String USER = "root";
	private static final String PASWD = "admin";
	
	private static Connection connection;
	private static Statement statement;
	
	
	private static Connection getConn(String dbName){
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DBURL+dbName,USER,PASWD);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("数据库连接失败"+e.toString());
		}
		
		return connection;
	}
	
	public static ResultSet execQuery(String dbName,String sql){
		connection = getConn(dbName);
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("查询数据失败。");
		}
		return rs;
	}
	
	public static int execUpdate(String dbName, String sql){
		connection = getConn(dbName);
		Statement statement = null;
		int count = 0;
		try{
			statement = connection.createStatement();
			count = statement.executeUpdate(sql);
			connection.close();
		}catch(SQLException e){
			System.out.println("数据库更新失败");
		}
		return count;
	}
}
