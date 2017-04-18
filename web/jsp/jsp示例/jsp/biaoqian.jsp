<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>jsp 标签</title>
  </head>
  
  <body>
   	<h3>1.jsp标签</h3>
   	<%-- <jsp:forward page="/jsp/01.jsp">
   		<jsp:param value="ZHANGSAN" name="username"/>
   	</jsp:forward> --%>
   	
    <%-- 很屌的事情！ --%>
   	<%-- <jsp:forward page="/jsp/01.jsp"></jsp:forward> --%>
   	<%-- 不报错 --%>
   	<%-- <jsp:forward page="/jsp/01.jsp">
   	</jsp:forward> --%>
   	<%-- 报错，提示没带param --%>
   	<jsp:include page="/jsp/02.jsp"></jsp:include>
  </body>
</html>
