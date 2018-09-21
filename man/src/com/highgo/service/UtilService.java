package com.highgo.service;

import java.sql.*;
import java.util.*;
import org.slf4j.*;
import com.highgo.util.*;
import com.highgo.model.People;

public class UtilService {

	Logger logger = LoggerFactory.getLogger(getClass());

	// select data
	public List<People> getList() {
		logger.info("+++ select start +++");
		
		String str = "select * from human order by id";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		List<People> list = new ArrayList<People>();
		try {
			con = JdbcUtils.getConnection();
			pstmt = con.prepareStatement(str);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				People people = new People();
				people.setId(rs.getInt("id"));
				people.setName(rs.getString("name"));
				people.setSex(rs.getString("sex"));
				list.add(people);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			
		} finally {
			JdbcUtils.close(rs);
			JdbcUtils.close(pstmt);
			JdbcUtils.close(con);
		}
		logger.info("select end");
		
		return list;
	}

	// condition query data
	public List<People> getList(String number) {
		logger.info("+++ query start +++" + "id =" + number);
		
		String str = "select * from human where id = ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		List<People> list = new ArrayList<People>();
		try {
			con = JdbcUtils.getConnection();
			pstmt = con.prepareStatement(str);
			pstmt.setInt(1, Integer.parseInt(number));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				People people = new People();
				people.setId(rs.getInt("id"));
				people.setName(rs.getString("name"));
				people.setSex(rs.getString("sex"));
				list.add(people);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			
		} finally {
			JdbcUtils.close(rs);
			JdbcUtils.close(pstmt);
			JdbcUtils.close(con);
		}
		logger.info("query end");
		
		return list;
	}

	// insert data
	public void insert(String name, String sex) {
		logger.info("+++ insert start +++" + "name =" + name + ",sex =" + sex);
		
		String str = "insert into human(name, sex) values(?,?)";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			pstmt = con.prepareStatement(str);
			pstmt.setString(1, name);
			pstmt.setString(2, sex);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			
		} finally {
			JdbcUtils.close(rs);
			JdbcUtils.close(pstmt);
			JdbcUtils.close(con);
		}
		logger.info("insert end");
	}

	// delete data
	public void delete(String number) {
		logger.info("+++ delete start +++" + "id =" + number);
		
		String str = "delete from human where id = ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			pstmt = con.prepareStatement(str);
			pstmt.setInt(1, Integer.parseInt(number));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			
		} finally {
			JdbcUtils.close(rs);
			JdbcUtils.close(pstmt);
			JdbcUtils.close(con);
		}
		logger.info("delete end");
	}

	// update data
	public void update(String number, String name, String sex) {
		logger.info("+++ update start +++" + "id =" + number + ", name =" + name + ", sex =" + sex);
		
		String str = "update human set name = ?, sex = ? where id = ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			pstmt = con.prepareStatement(str);
			pstmt.setString(1, name);
			pstmt.setString(2, sex);
			pstmt.setInt(3, Integer.parseInt(number));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			
		} finally {
			JdbcUtils.close(rs);
			JdbcUtils.close(pstmt);
			JdbcUtils.close(con);
		}
		logger.info("update end");
	}
}