package com.highgo.service;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import org.slf4j.*;
import com.highgo.model.People;
import com.highgo.util.Conn;
import net.sf.json.JSONArray;
import com.highgo.util.DBClose;

public class UtilService {
	
	Logger logger = LoggerFactory.getLogger(UtilService.class);

	// 查询数据库数据
	public void select(HttpServletRequest request, HttpServletResponse response) {
		logger.info("select start");
		
		DBClose close = new DBClose();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = Conn.getConnection();
		try {
			pstmt = con.prepareStatement("select * from human order by id");
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close.close(rs);
			close.close(pstmt);
			close.close(con);
		}
		logger.info("select end");
	}

	// 条件查询数据库数据
	public void query(HttpServletRequest request, HttpServletResponse response) {
		logger.info("query start");
		
		String number = request.getParameter("idtxt");
		logger.info(number);
		
		DBClose close = new DBClose();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = Conn.getConnection();
		try {
			pstmt = con.prepareStatement("select * from human where id=?");
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
			JSONArray jsonstr = JSONArray.fromObject(list);
			PrintWriter out = response.getWriter();
			out.print(jsonstr);
			System.out.println(jsonstr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close.close(rs);
			close.close(pstmt);
			close.close(con);
		}
		logger.info("query end");
	}

	// 新增数据
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		logger.info("insert start");
		
		String name = request.getParameter("name");
		logger.info(name);
		String sex = request.getParameter("sex");
		logger.info(sex);
		
		DBClose close = new DBClose();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = Conn.getConnection();
		try {
			pstmt = con.prepareStatement("insert into human(name,sex) values(?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, sex);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close.close(rs);
			close.close(pstmt);
			close.close(con);
		}
		logger.info("insert end");
	}

	// 删除数据
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		logger.info("delete start");
		
		String number = request.getParameter("number");
		logger.info(number);
		
		DBClose close = new DBClose();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = Conn.getConnection();
		try {
			pstmt = con.prepareStatement("delete from human where id=?");
			pstmt.setInt(1, Integer.parseInt(number));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close.close(rs);
			close.close(pstmt);
			close.close(con);
		}
		logger.info("delete end");
	}

	// 修改数据
	public void update(HttpServletRequest request, HttpServletResponse response) {
		logger.info("update start");
		
		String number = request.getParameter("number");
		logger.info(number);
		String name = request.getParameter("name");
		logger.info(name);
		String sex = request.getParameter("sex");
		logger.info(sex);
		
		DBClose close = new DBClose();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = Conn.getConnection();
		try {
		    pstmt = con.prepareStatement("update human set name=?,sex=? where id=?");
			pstmt.setString(1, name);
			pstmt.setString(2, sex);
			pstmt.setInt(3, Integer.parseInt(number));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close.close(rs);
			close.close(pstmt);
			close.close(con);
		}
		logger.info("update end");
	}
}