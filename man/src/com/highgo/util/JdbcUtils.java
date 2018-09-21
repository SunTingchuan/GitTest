package com.highgo.util;

import java.sql.*;
import org.slf4j.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	static ComboPooledDataSource dataSource = new ComboPooledDataSource("highgo");

	public static Connection getConnection() {

		Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

		Connection con = null;
		try {
			con = dataSource.getConnection();
			logger.info("Database driver loaded successfully,the database connection was successful");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return con;
	}
	
	// Close the result set object
		public static void close(ResultSet rs) {
			Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
			
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}

		// Close the preparedStatement object
		public static void close(PreparedStatement pstmt) {
			Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}

		// Close the result set object
		public static void close(Connection con) {
			Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
			
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
		}
}