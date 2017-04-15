package web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dom.UserDom;
import entry.User;

public class AjaxCheckUsername extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String username=(String) request.getParameter("username");
		System.out.println(username);
//		获取user
		UserDom userDom=new UserDom();
		User user=userDom.getUserByName(username);
		if (user!=null) {
//			已有该用户返回错误信息
			response.getWriter().write("用户名已存在");
		}
		else {
//			返回可以注册信息
			response.getWriter().write("恭喜, 用户名可以使用!");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
