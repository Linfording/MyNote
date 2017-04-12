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
		request.setAttribute("key", "value");
//		请求重定向
//		response.setStatus(302);
//		response.setHeader("location", request.getContextPath()+"/servlet/ServletB");
//		上面两句和下面一句等价
//		response.sendRedirect(request.getContextPath()+"/servlet/ServletB");
		/**
		地址栏是http://localhost:8080/ServletTest/servlet/ServletB
		servletB:response.getWriter().write("servletB...."+request.getAttribute("key"));
		此时客户端输出为servletB....null
		*/
		
//		请求转发
//		请求转发: 实现资源的跳转, 是服务器内部的同一web应用内的资源跳转
//		request.getRequestDispatcher("/servlet/ServletB").forward(request, response);
//		response.getWriter().write("servletA.....");
		/**
		 地址是http://localhost:8080/ServletTest/servlet/ServletA
		servletB:response.getWriter().write("servletB...."+request.getAttribute("key"));
		此时客户端输出为servletB.....value
		一次请求, 一次响应, 地址栏不会发生变化
		**如果在转发之前, 有任何数据写入了response缓冲区中但是还没有打给浏览器, 在转发时, 缓冲区中的数据将会被清空.
		response.flushBuffer();
		**如果在转发之前, 有任何数据写入了response缓冲区中, 并且已经打给了浏览器, 转发将会失败!!
		**不能转发多次!!!但是可以多重转发.
		**转发就像方法的调用, 在转发成功之后, 转发后面的代码还会接着执行!!
		*/
		
//		包含请求
//		所谓的请求包含就是服务器内部的资源合并的现象
//		如果浏览器访问AServlet, AServlet不能独立的处理这个请求, 需要BServlet帮忙处理, 可以在AServlet中将BServlet包含进来, 由A和B一起处理, 最终A和B处理的结果一起响应给浏览器
//		request.getRequestDispatcher("/servlet/ServletB").include(request, response);
//		response.getWriter().write("servletA.....");
		/**
		地址栏依然是http://localhost:8080/ServletTest/servlet/ServletA
		servletB:response.getWriter().write("servletB...."+request.getAttribute("key"));
		此时客户端输出为servletB....valueservletA.....
		*/
		
//		定时刷新
//		通过Refresh头可以实现在多少秒之后跳转到url所指定的地址.
//		可以实现在同一服务器中的同一web应用内的资源跳转, 也可以实现在不同的服务器或不同的web应用内的资源跳转
//		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/index.jsp");
		
/**		
		1、请求转发（RequestDispatcher）的过程：
		客户首先发送一个请求到服务器端，服务器端发现匹配的servlet，并指定它去执行，
		当这个servlet执行完之后，它要调用getRequestDispacther()方法，把请求转发给指定的test.jsp,
		整个流程都是在服务器端完成的，而且是在同一个请求里面完成的，因此servlet和jsp共享的是同一个request，
		在servlet里面放的所有东西，在jsp中都能取出来，因此，jsp能把结果getAttribute()出来，
		getAttribute()出来后执行完把结果返回给客户端。整个过程是一个请求，一个响应。
		总结：
		一次请求, 一次响应, request对象是同一个
		地址栏不会发生变化
		只能在同一服务器同一个web应用内部资源之间进行跳转, 不能在不同的服务器或不同的web应用之间进行资源的跳转.

		2、重定向（sendRedirect）的工作原理：
		客户发送一个请求到服务器，服务器匹配servlet，这都和请求转发一样，servlet处理完之后
		调用了sendRedirect()这个方法，这个方法是response的方法，所以，当这个servlet处理完之后，
		看到response.senRedirect()方法，立即向客户端返回这个响应，响应行告诉客户端你必须要再发送一个请求，
		去访问test.jsp，紧接着客户端受到这个请求后，立刻发出一个新的请求，去请求test.jsp,这里两个请求互不干扰，
		相互独立，在前面request里面setAttribute()的任何东西，在后面的request里面都获得不了。
		可见，在sendRedirect()里面是两个请求，两个响应。
		总结：
		两次请求, 两次响应, request对象不是同一个
		地址栏会发生变化
		可以实现同一服务器同一个web应用内部的资源之间进行跳转, 也可以在不同的服务器或不同的web应用之间进行资源的跳转.
		
		3、定时刷新:
		同重定向，只是指定了多少秒之后刷新。重定向是立即跳转, 而定时刷新是指定多少秒之后再进行跳转, 
		在跳转之前的这段时间里, 我们可以向浏览器发送文本数据并维系一段时间.
		总结：
		两次请求, 两次响应, request对象不是同一个
		地址栏会发生变化
		可以实现同一服务器同一个web应用内部的资源之间进行跳转, 也可以在不同的服务器或不同的web应用之间进行资源的跳转.
		*/
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
