package com.highgo.service;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import org.slf4j.*;
import com.google.gson.Gson;
import com.highgo.model.People;
import com.highgo.util.*;
import net.sf.json.JSONArray;

public class UtilService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	// select data
	public void select(HttpServletResponse response) {
		logger.info("+++select start+++");
		String str = "select * from human order by id";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con =  null;
		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(str);
			
			rs = pstmt.executeQuery();
			List<People> list = new ArrayList<People>();
			while (rs.next()) {
				People people = new People();
				people.setId(rs.getInt("id"));
				people.setName(rs.getString("name"));
				people.setSex(rs.getString("sex"));
				list.add(people);
			}
			JSONArray jsonstr = JSONArray.fromObject(list);
			PrintWriter out = response.getWriter();
			out.print(jsonstr);
			System.out.println(jsonstr);
		}catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}finally {
			jdbcClose.close(rs);
			jdbcClose.close(pstmt);
			jdbcClose.close(con);
		}
		logger.info("select end");
	}

	// condition query data
	public void query(String number, HttpServletResponse response) {
		logger.info("+++query start+++");
		String str = "select * from human where id=?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con =  null;
		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(str);
			pstmt.setInt(1, Integer.parseInt(number));
			
			rs = pstmt.executeQuery();
			List<People> list = new ArrayList<People>();
			while (rs.next()) {
				People people = new People();
				people.setId(rs.getInt("id"));
				people.setName(rs.getString("name"));
				people.setSex(rs.getString("sex"));
				list.add(people);
			}
			Gson gson = new Gson();
			String jsonstr = gson.toJson(list);
			PrintWriter out = response.getWriter();
			out.print(jsonstr);
			System.out.println(jsonstr);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}finally {
			jdbcClose.close(rs);
			jdbcClose.close(pstmt);
			jdbcClose.close(con);
		}
		logger.info("query end");
	}

	// insert data
	public void insert(String name, String sex) {
		logger.info("+++insert start+++");
		String str = "insert into human(name,sex) values(?,?)";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con =  null;
		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(str);
			pstmt.setString(1, name);
			pstmt.setString(2, sex);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}finally {
			jdbcClose.close(rs);
			jdbcClose.close(pstmt);
			jdbcClose.close(con);
		}
		logger.info("insert end");
	}

	// delete data
	public void delete(String number) {
		logger.info("+++delete start+++");
		String str = "delete from human where id=?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con =  null;
		try {
			con = Conn.getConnection();
			pstmt = con.prepareStatement(str);
			pstmt.setInt(1, Integer.parseInt(number));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}finally {
			jdbcClose.close(rs);
			jdbcClose.close(pstmt);
			jdbcClose.close(con);
		}
		logger.info("delete end");
	}

	// update data
	public void update(String number, String name, String sex) {
		logger.info("+++update start+++");
		String str = "update human set name=?,sex=? where id=?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con =  null;
		try {
			con = Conn.getConnection();
		    pstmt = con.prepareStatement(str);
			pstmt.setString(1, name);
			pstmt.setString(2, sex);
			pstmt.setInt(3, Integer.parseInt(number));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}finally {
			jdbcClose.close(rs);
			jdbcClose.close(pstmt);
			jdbcClose.close(con);
		}
		logger.info("update end");
	}
}