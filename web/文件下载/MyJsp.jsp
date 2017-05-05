<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h3>文件下载</h3>
    <h4>1.直接建立资源连接</h4>
    <a href="${pageContext.request.contextPath }/1.jpg">1.jpg</a><br>
    <a href="${pageContext.request.contextPath }/2.txt">2.txt</a><br>
    <a href="${pageContext.request.contextPath }/3.pptx">3.ppt</a><br>
	若为浏览器能够解析的资源，浏览器会直接打开，不会提供下载，比如图片,html,txt这些资源都会直接打开，虽然这样跟下载没什么差但是用户体验不太好<br>
	目前仅用在少量文件类型，比如zip等等
    <h4>2.IO流的方式</h4>
    <a href="${pageContext.request.contextPath }/FileDownServlet?filename=1.jpg">1.jpg</a><br>
    <a href="${pageContext.request.contextPath }/FileDownServlet?filename=美图.jpg">美图.jpg</a><br>
    <a href="${pageContext.request.contextPath }/FileDownServlet?filename=2.txt">2.txt</a><br>
    <a href="${pageContext.request.contextPath }/FileDownServlet?filename=3.ppt">3.ppt</a><br>
    <h4>3.文件转发方式</h4>
    <a href="${pageContext.request.contextPath }/FileDownServlet2?filename=1.jpg">1.jpg</a><br>
    <a href="${pageContext.request.contextPath }/FileDownServlet2?filename=美图.jpg">美图.jpg</a><br>
    <a href="${pageContext.request.contextPath }/FileDownServlet2?filename=2.txt">2.txt</a><br>
    <a href="${pageContext.request.contextPath }/FileDownServlet2?filename=3.ppt">3.ppt</a><br>
  </body>
</html>
