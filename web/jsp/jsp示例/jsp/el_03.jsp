<%@page import="javabeen.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>el表达式</title>
  </head>
  
  <body>
   	<h3>获取常用开发对象</h3>
  	<!--1.pageContext  -->
  	${pageContext.request.contentType }
  	${pageContext.request.remoteAddr }
  	
  	<!--2.4个作用域  -->
  	<hr/>
  	<% pageContext.setAttribute("addr", "pagecontext"); %>
  	<% request.setAttribute("addr", "request"); %>
  	<% session.setAttribute("addr", "session"); %>
  	<% application.setAttribute("addr", "servletecontext"); %>
 
 	${applicationScope.addr } 	
 	${sessionScope.addr } 	
 	${requestScope.addr } 	
 	${pageScope.addr } 	
 	
 	<!--3.param  -->
 	<hr/>
 	<%=request.getParameter("username") %>
 	<%=request.getParameter("password") %>
 	
 	<%--http://localhost/SessionCookie/jsp/el_03.jsp?username=zhangsan&password=1234&like=zuqiu&like=lanqiu --%>
	 ${param["username"] }
	 ${param["password"] }
	 ${param.username }
	 ${param.password }
	 
	 ${ paramValues.like[0] }
	 ${ paramValues.like[1] }
	 
	 <!--4.cookie -->
	 <hr/>
	 
	 cookieName: ${cookie.JSESSIONID.name }
	 cookieValue: ${cookie.JSESSIONID.value }
	 
	 <!--5.initParam  -->
	 <hr/>
	 ${initParam.username }
	 
  </body>
</html>
