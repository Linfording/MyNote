<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="../error/err.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   		<h3>1.模板元素</h3>
   			01.jsp......
   		
   		<h3>2.脚本表达式</h3>
   		<% String str="hello jsp...."; %>
   		<%= "abc..." %> <br/>
   		<%= str %> <br/>
   		<%= 100+123 %> <br/>
   		<% String aa="这里声明的变量是局部变量"; %>
   		
   		<h3>3.脚本片段</h3>
   		<%for(int i=0;i<5;i++){ %>
   			hello...jsp....<br/>
   		<% }%>
   		
   		
   		<h3>4.jsp声明</h3>
   		<%! public void eat(){
   			System.out.println("eat");
   		} %>
   		<%! String strx="xxx"; %>
   		<%! String s="这里的方法和变量和servlet下的方法平级，是Servlet的成员变量"; %>
   		
   		<h4>测试:</h4>
		<% int a=1; %>
		<%! int b=1; %>

		a的值:<%= a++ %><br/>
		b的值:<%= b++ %><br/>
		
		<h3>5.注释</h3>
		<%-- <% out.write("aaa"); %> --%>
		<% //out.write("bbb"); %>
		<!-- <% out.write("ccc"); %>  -->
		<%--第一种不会进预编译；第二种会翻译但是不会执行；第三种会翻译会执行，输出到客户端，但是不会显示，是作为注释输出在客户端，在查看源码时可以看到  --%>
		<%--所以在jsp中最好用第一种jsp注释，html注释会执行出来慎用  --%>
  </body>
</html>
