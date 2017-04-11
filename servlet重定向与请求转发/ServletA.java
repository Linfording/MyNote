package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletA extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
//		请求重定向
//		response.sendRedirect(request.getContextPath()+"/servlet/ServletB");
//		地址栏是http://localhost:8080/ServletTest/servlet/ServletB
//		此时客户端输出为servletB....
		
//		请求转发
//		请求转发: 实现资源的跳转, 是服务器内部的同一web应用内的资源跳转
//		request.getRequestDispatcher("/servlet/ServletB").forward(request, response);
//		response.getWriter().write("servletA.....");
//		地址是http://localhost:8080/ServletTest/servlet/ServletA
//		此时客户端输出为servletB.....
//		一次请求, 一次响应, 地址栏不会发生变化
//		**如果在转发之前, 有任何数据写入了response缓冲区中但是还没有打给浏览器, 在转发时, 缓冲区中的数据将会被清空.
//		**如果在转发之前, 有任何数据写入了response缓冲区中, 并且已经打给了浏览器, 转发将会失败!!
//		**不能转发多次!!!但是可以多重转发.
//		**转发就像方法的调用, 在转发成功之后, 转发后面的代码还会接着执行!!
		
//		包含请求
//		所谓的请求包含就是服务器内部的资源合并的现象
//		如果浏览器访问AServlet, AServlet不能独立的处理这个请求, 需要BServlet帮忙处理, 可以在AServlet中将BServlet包含进来, 由A和B一起处理, 最终A和B处理的结果一起响应给浏览器
//		request.getRequestDispatcher("/servlet/ServletB").include(request, response);
//		response.getWriter().write("servletA.....");
//		地址栏依然是http://localhost:8080/ServletTest/servlet/ServletA
//		此时客户端输出为servletB....servletA.....
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
