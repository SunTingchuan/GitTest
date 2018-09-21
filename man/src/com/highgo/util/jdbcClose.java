package com.highgo.util;

import java.sql.*;

public class jdbcClose {

	// Close the result set object
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	// Close the preparedStatement object
	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	// Close the result set object
	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}
