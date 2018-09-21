package com.highgo.servlet;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.slf4j.*;
import com.google.gson.Gson;
import com.highgo.model.People;
import com.highgo.service.UtilService;
import net.sf.json.JSONArray;

@WebServlet("/select")
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
		logger.info("value=" + hiddenvalue);
		String hidden = (hiddenvalue == null) ? "select" : hiddenvalue;
		try {
			if ("select".equals(hidden)) {

				List<People> list = service.getList();
				JSONArray jsonstr = JSONArray.fromObject(list);
				PrintWriter out = response.getWriter();
				out.print(jsonstr);
				System.out.println(jsonstr);

			} else if ("insert".equals(hidden)) {

				String name = request.getParameter("name");
				logger.info("name=" + name);

				String sex = request.getParameter("sex");
				logger.info("sex=" + sex);

				service.insert(name, sex);

			} else if ("delete".equals(hidden)) {

				String number = request.getParameter("number");
				logger.info("id=" + number);

				service.delete(number);

			} else if ("update".equals(hidden)) {

				String number = request.getParameter("number");
				logger.info("id=" + number);

				String name = request.getParameter("name");
				logger.info("name=" + name);

				String sex = request.getParameter("sex");
				logger.info("sex=" + sex);

				service.update(number, name, sex);

			} else {

				String number = request.getParameter("idtxt");
				logger.info("id=" + number);

				List<People> list = service.getList(number);
				Gson gson = new Gson();
				String jsonstr = gson.toJson(list);
				PrintWriter out = response.getWriter();
				out.print(jsonstr);
				System.out.println(jsonstr);
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