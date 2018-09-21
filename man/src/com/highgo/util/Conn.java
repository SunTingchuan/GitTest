package com.highgo.util;

import java.sql.*;
import org.slf4j.*;

public class Conn {
	
	public static Connection getConnection() {
		
		Logger logger = LoggerFactory.getLogger(Conn.class);
		
		Connection con = null;
		try {
			Class.forName("com.highgo.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:highgo://127.0.0.1:5866/highgo", "highgo", "highgo123");
			logger.info("Database driver loaded successfully,the database connection was successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		Conn.getConnection();
	}
}