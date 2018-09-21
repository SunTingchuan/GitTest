package com.highgo.util;

import java.sql.*;
import org.slf4j.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Conn {

	static ComboPooledDataSource dataSource = new ComboPooledDataSource("highgo");

	public static Connection getConnection() {

		Logger logger = LoggerFactory.getLogger(Conn.class);

		Connection con = null;
		try {
			con = dataSource.getConnection();
			logger.info("Database driver loaded successfully,the database connection was successful");
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return con;
	}

	public static void main(String[] args) {
		Conn.getConnection();
	}
}