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
//		�����ض���
//		response.sendRedirect(request.getContextPath()+"/servlet/ServletB");
//		��ַ����http://localhost:8080/ServletTest/servlet/ServletB
//		��ʱ�ͻ������ΪservletB....
		
//		����ת��
//		����ת��: ʵ����Դ����ת, �Ƿ������ڲ���ͬһwebӦ���ڵ���Դ��ת
//		request.getRequestDispatcher("/servlet/ServletB").forward(request, response);
//		response.getWriter().write("servletA.....");
//		��ַ��http://localhost:8080/ServletTest/servlet/ServletA
//		��ʱ�ͻ������ΪservletB.....
//		һ������, һ����Ӧ, ��ַ�����ᷢ���仯
//		**�����ת��֮ǰ, ���κ�����д����response�������е��ǻ�û�д�������, ��ת��ʱ, �������е����ݽ��ᱻ���.
//		**�����ת��֮ǰ, ���κ�����д����response��������, �����Ѿ�����������, ת������ʧ��!!
//		**����ת�����!!!���ǿ��Զ���ת��.
//		**ת�����񷽷��ĵ���, ��ת���ɹ�֮��, ת������Ĵ��뻹�����ִ��!!
		
//		��������
//		��ν������������Ƿ������ڲ�����Դ�ϲ�������
//		������������AServlet, AServlet���ܶ����Ĵ����������, ��ҪBServlet��æ����, ������AServlet�н�BServlet��������, ��A��Bһ����, ����A��B����Ľ��һ����Ӧ�������
//		request.getRequestDispatcher("/servlet/ServletB").include(request, response);
//		response.getWriter().write("servletA.....");
//		��ַ����Ȼ��http://localhost:8080/ServletTest/servlet/ServletA
//		��ʱ�ͻ������ΪservletB....servletA.....
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
