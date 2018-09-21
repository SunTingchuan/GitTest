package com.highgo.servlet;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.*;

import com.highgo.model.People;
import com.highgo.service.UtilService;

import net.sf.json.JSONArray;

public class CallMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilService service = new UtilService();
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public void init() throws ServletException {
		logger.info("+++init+++");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("get--start");
		request.setCharacterEncoding("utf-8");// 设置请求字符集编码
		response.setContentType("text/html;charset=utf-8");// 设置响应字符集编码
		
		String hiddenvalue = request.getParameter("callMethod");// 获取隐藏域的value值
		logger.info("value="+hiddenvalue);
		String hidden = (hiddenvalue == null) ? "select" : hiddenvalue;
		try {
			if ("select".equals(hidden)) {
				
				service.select(response);
				
			} else if ("insert".equals(hidden)) {
				
				String name = request.getParameter("name");
				logger.info("name="+name);
				
				String sex = request.getParameter("sex");
				logger.info("sex="+sex);
				
				service.insert(name, sex);
				
			} else if ("delete".equals(hidden)) {
				
				String number = request.getParameter("number");
				logger.info("id="+number);
				
				service.delete(number);
				
			} else if ("update".equals(hidden)) {
				
				String number = request.getParameter("number");
				logger.info("id="+number);
				
				String name = request.getParameter("name");
				logger.info("name="+name);
				
				String sex = request.getParameter("sex");
				logger.info("sex="+sex);
				
				service.update(number, name, sex);
				
			} else {
				
				String number = request.getParameter("idtxt");
				logger.info("id="+number);
				
				service.query(number, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		} 
		logger.info("get end");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("post__start");
		doGet(request, response);
		logger.info("post end");
	}

	public void destroy() {
		logger.info("+++destroy+++");
	}
}