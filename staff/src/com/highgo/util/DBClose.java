package com.highgo.util;

import java.sql.*;
import org.slf4j.*;

public class DBClose {
	
	Logger logger = LoggerFactory.getLogger(DBClose.class);
	
	// 关闭结果集对象
	public void close(ResultSet rs) {
		logger.info("rs close start");
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("rs close end");
	}
	
	// 关闭预编译对象
	public void close(PreparedStatement pstmt) {
		logger.info("pstmt close start");
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("pstmt close end");
	}
	
	// 关闭结果集对象
	public void close(Connection con) {
		logger.info("con close start");
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("con close end");
	}
}
