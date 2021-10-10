package com.todo.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
	private static Connection conn = null;
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();				
			}
			
		}
	}
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				String dbFile = "todoList.db";
				conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		return conn;
	}
}
