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
//		�����ض���
//		response.setStatus(302);
//		response.setHeader("location", request.getContextPath()+"/servlet/ServletB");
//		�������������һ��ȼ�
//		response.sendRedirect(request.getContextPath()+"/servlet/ServletB");
		/**
		��ַ����http://localhost:8080/ServletTest/servlet/ServletB
		servletB:response.getWriter().write("servletB...."+request.getAttribute("key"));
		��ʱ�ͻ������ΪservletB....null
		*/
		
//		����ת��
//		����ת��: ʵ����Դ����ת, �Ƿ������ڲ���ͬһwebӦ���ڵ���Դ��ת
//		request.getRequestDispatcher("/servlet/ServletB").forward(request, response);
//		response.getWriter().write("servletA.....");
		/**
		 ��ַ��http://localhost:8080/ServletTest/servlet/ServletA
		servletB:response.getWriter().write("servletB...."+request.getAttribute("key"));
		��ʱ�ͻ������ΪservletB.....value
		һ������, һ����Ӧ, ��ַ�����ᷢ���仯
		**�����ת��֮ǰ, ���κ�����д����response�������е��ǻ�û�д�������, ��ת��ʱ, �������е����ݽ��ᱻ���.
		response.flushBuffer();
		**�����ת��֮ǰ, ���κ�����д����response��������, �����Ѿ�����������, ת������ʧ��!!
		**����ת�����!!!���ǿ��Զ���ת��.
		**ת�����񷽷��ĵ���, ��ת���ɹ�֮��, ת������Ĵ��뻹�����ִ��!!
		*/
		
//		��������
//		��ν������������Ƿ������ڲ�����Դ�ϲ�������
//		������������AServlet, AServlet���ܶ����Ĵ����������, ��ҪBServlet��æ����, ������AServlet�н�BServlet��������, ��A��Bһ����, ����A��B����Ľ��һ����Ӧ�������
//		request.getRequestDispatcher("/servlet/ServletB").include(request, response);
//		response.getWriter().write("servletA.....");
		/**
		��ַ����Ȼ��http://localhost:8080/ServletTest/servlet/ServletA
		servletB:response.getWriter().write("servletB...."+request.getAttribute("key"));
		��ʱ�ͻ������ΪservletB....valueservletA.....
		*/
		
//		��ʱˢ��
//		ͨ��Refreshͷ����ʵ���ڶ�����֮����ת��url��ָ���ĵ�ַ.
//		����ʵ����ͬһ�������е�ͬһwebӦ���ڵ���Դ��ת, Ҳ����ʵ���ڲ�ͬ�ķ�������ͬ��webӦ���ڵ���Դ��ת
//		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/index.jsp");
		
/**		
		1������ת����RequestDispatcher���Ĺ��̣�
		�ͻ����ȷ���һ�����󵽷������ˣ��������˷���ƥ���servlet����ָ����ȥִ�У�
		�����servletִ����֮����Ҫ����getRequestDispacther()������������ת����ָ����test.jsp,
		�������̶����ڷ���������ɵģ���������ͬһ������������ɵģ����servlet��jsp�������ͬһ��request��
		��servlet����ŵ����ж�������jsp�ж���ȡ��������ˣ�jsp�ܰѽ��getAttribute()������
		getAttribute()������ִ����ѽ�����ظ��ͻ��ˡ�����������һ������һ����Ӧ��
		�ܽ᣺
		һ������, һ����Ӧ, request������ͬһ��
		��ַ�����ᷢ���仯
		ֻ����ͬһ������ͬһ��webӦ���ڲ���Դ֮�������ת, �����ڲ�ͬ�ķ�������ͬ��webӦ��֮�������Դ����ת.

		2���ض���sendRedirect���Ĺ���ԭ��
		�ͻ�����һ�����󵽷�������������ƥ��servlet���ⶼ������ת��һ����servlet������֮��
		������sendRedirect()������������������response�ķ��������ԣ������servlet������֮��
		����response.senRedirect()������������ͻ��˷��������Ӧ����Ӧ�и��߿ͻ��������Ҫ�ٷ���һ������
		ȥ����test.jsp�������ſͻ����ܵ������������̷���һ���µ�����ȥ����test.jsp,�����������󻥲����ţ�
		�໥��������ǰ��request����setAttribute()���κζ������ں����request���涼��ò��ˡ�
		�ɼ�����sendRedirect()��������������������Ӧ��
		�ܽ᣺
		��������, ������Ӧ, request������ͬһ��
		��ַ���ᷢ���仯
		����ʵ��ͬһ������ͬһ��webӦ���ڲ�����Դ֮�������ת, Ҳ�����ڲ�ͬ�ķ�������ͬ��webӦ��֮�������Դ����ת.
		
		3����ʱˢ��:
		ͬ�ض���ֻ��ָ���˶�����֮��ˢ�¡��ض�����������ת, ����ʱˢ����ָ��������֮���ٽ�����ת, 
		����ת֮ǰ�����ʱ����, ���ǿ���������������ı����ݲ�άϵһ��ʱ��.
		�ܽ᣺
		��������, ������Ӧ, request������ͬһ��
		��ַ���ᷢ���仯
		����ʵ��ͬһ������ͬһ��webӦ���ڲ�����Դ֮�������ת, Ҳ�����ڲ�ͬ�ķ�������ͬ��webӦ��֮�������Դ����ת.
		*/
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
