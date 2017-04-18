<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>05.jsp</title>
  </head>
  <%
  	String a="AAAA";
  	String b="BBBB";
  	String c="CCCC";
  	String d="DDDD";
  
   %>
  <body>
   	<%out.write(a); %><br/>
   	<%response.getWriter().write(b); %><br/>
   	<%out.write(c); %><br/>
   	<%response.getWriter().write(d); %><br/>
   	
   	<%=request.getParameter("username") %>
   	
  	 
  </body>
</html>
