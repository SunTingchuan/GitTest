package com.highgo.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.*;
import com.highgo.service.UtilService;

public class CallMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilService service = new UtilService();
	
	Logger logger = LoggerFactory.getLogger(CallMethodServlet.class);

	public void init() throws ServletException {
		logger.info("init start");
		logger.info("init end");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("get start");
		request.setCharacterEncoding("utf-8");// 设置请求字符集编码
		response.setContentType("text/html;charset=utf-8");// 设置响应字符集编码
		
		String hiddenvalue = request.getParameter("callMethod");// 获取隐藏域的value值
		logger.info(hiddenvalue);
		String hidden = (hiddenvalue == null) ? "select" : hiddenvalue;
		try {
			if ("select".equals(hidden)) {
				service.select(request, response);
			} else if ("insert".equals(hidden)) {
				service.insert(request, response);
			} else if ("delete".equals(hidden)) {
				service.delete(request, response);
			} else if ("update".equals(hidden)) {
				service.update(request, response);
			} else {
				service.query(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		logger.info("get end");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("post start");
		doGet(request, response);
		logger.info("post end");
	}

	public void destroy() {
		logger.info("destroy start");
		logger.info("destroy end");
	}
}