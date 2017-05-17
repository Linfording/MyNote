<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
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
   	<form action="${pageContext.request.contextPath }/addUser.action" method="post">
   		<table width="350px" height="120px" border="1px" align="center">
   			<tr align="center">
   				<td colspan="2"><h3>用户注册信息</h3></td>
   			</tr>
   			<tr align="center">
   				<td>用户名:</td>
   				<td><input type="text" name="name"/></td>
   			</tr>
   			<tr align="center">
   				<td>密码:</td>
   				<td><input type="password" name="password"/></td>
   			</tr>
   			<tr align="center">
   				<td>性别:</td>
   				<td><input type="radio" name="gender" value="男"/>男
   				<input type="radio" name="gender" value="女"/>女</td>
   			</tr>
   			<tr align="center">
   				<td>年龄:</td>
   				<td><input type="text" name="userInfo.age"/></td>
   			</tr>
   			<tr align="center">
   				<td>生日:</td>
   				<td><input type="text" name="userInfo.birthday"/></td>
   			</tr> 
   			<tr align="center">
   				<td>爱好:</td>
   				<td>
   					<input type="checkbox" name="hobbys" value="足球"/>足球
   					<input type="checkbox" name="hobbys" value="篮球"/>篮球
   					<input type="checkbox" name="hobbys" value="羽毛球"/>羽毛球
   					<input type="checkbox" name="hobbys" value="游泳"/>游泳
   				</td>
   			</tr> 
   			<tr align="center">
   				<td colspan="2">
   					<input type="submit" value="提交"/>
   				</td>
   			</tr>
   		</table>
   	</form>
  </body>
</html>
