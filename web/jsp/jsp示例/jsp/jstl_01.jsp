<%@ page import="javabeen.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>jstl_01</title>
  </head>
  <body>
   	<h3>1.c_out</h3>
   	<%
   		String name="王宝强";
   		pageContext.setAttribute("name", name);
   	 %>
  	 输出常量:	<c:out value="what"></c:out><br/>
  	 输出变量:<c:out value="${name}"></c:out><br/>
  	 转义html:<c:out value="<font style='color:red'>hello</font>"></c:out><br/>
  	 不转义html:<c:out value="<font style='color:red'>hello</font>" escapeXml="false"></c:out><br/>
  	 输出默认值:<c:out value="${name1 }" default="陈羽凡"></c:out>
   	
   	<h3>2.c_set</h3>
   	添加属性到域中:
   	<c:set var="username" value="孙权" scope="request"></c:set>
   	<c:set var="username" value="刘备" scope="session"></c:set>
   	<c:set var="username" value="曹操" scope="page"></c:set>
   	<c:set var="username" value="董卓" scope="application"></c:set><br>
   	
   	${ requestScope.username }<br>
   	${ sessionScope.username }<br>
   	${ pageScope.username }<br>
   	${ applicationScope.username }<br>
   	<hr/>
   	修改域中的数据:<br>
   	request：孙权-->孙策<br>
   	<c:set var="username" value="孙策" scope="request"></c:set><br>
   	
   	输出结果：${ requestScope.username }<br>
   	<!--设置同名的属性来修改属性  -->
   	<hr/>
   	<%
   		Map map=new HashMap();
   		map.put("username", "周瑜");
   		map.put("password", "110");
   		pageContext.setAttribute("map", map);
   	 %>
   	${map.username }<br/>
   	修改域中map中属性的值:<br/>
   	<c:set target="${map }" property="usernmae" value="诸葛亮"></c:set>
   	 ${map.usernmae }
   	<hr/>
   	<%
   		User user=new User();
   		user.setUsername("黄盖");
   		user.setPassword("1316");
   		pageContext.setAttribute("user", user);
   	 %>
   	 ${user.username }<br/>
   	 修改域中javaBeen中属性的值:<br/>
	<c:set target="${user }" property="username" value="黄月英"></c:set>
	${user.username }
	
	<hr></hr>
	<h3>3.c_remove</h3>
	${username }<br/>
	<c:remove var="username"/><br/>
	${username }<br/>
	<hr>
	<h3>4.c_catch</h3>
	<!--标签用于捕获嵌套在标签体中的内容抛出的异常，其语法格式如下-->
	<c:catch var="e">
	<%
	 	/* String str=null;
		str.toString();   */
		String str="a";
		str.charAt(10);
		int i=1/0;
	 %>
   	</c:catch>
   	<%-- ${e } --%>
   	${e.message }
   	<!-- 空指针只有用e才有输出 -->
   	
   	<hr/>
   	<h3>5.c_if</h3>
   	<!--标签可以构造简单的“if-then”结构的条件表达式   -->
   	<!-- 模拟if-else  因为没有else -->
   	<c:if test="${3>5 }">yes</c:if>
   	<c:if test="${!(3>5) }">no</c:if><br/>
   	
   	检测用户是否登录:<br/>
   	<c:set var="username" value="朱德" scope="session"></c:set>
   	<c:if test="${empty sessionScope.username }">您还没有登录</c:if><br/>
   	<c:if test="${!(empty sessionScope.username) }">您已登录成功</c:if><br/>
   	${sessionScope.username }
   	<hr/>
   	<h3>!!6.c_choose</h3>
   	<!-- 标签用于指定多个条件选择的组合边界 -->
   	<c:set var="day" value="7"></c:set>
   	<c:choose>
   		<c:when test="${day==1 }">星期一</c:when>
   		<c:when test="${day==2 }">星期二</c:when>
   		<c:when test="${day==3 }">星期三</c:when>
   		<c:when test="${day==4 }">星期四</c:when>
   		<c:when test="${day==5 }">星期五</c:when>
   		<c:otherwise>周末</c:otherwise>
   	</c:choose>
   	<hr/>
   	
   	<h3>!!!!7.forEach</h3>
   	<!--标签用于对一个集合对象中的元素进行循环迭代操作，或者按指定的次数重复迭代执行标签体中的内容。  -->
   <%-- 	<%
   		for(int i=0;i<100;i++){ out.write(i+",");}
   	 %> --%>
   	<!-- step 不给默认就是1 -->
   	<c:forEach begin="1" end="100" step="1" var="i">
   		${i } ${ i==100?"":"," }
   	</c:forEach><br/>
   	<hr/>
   	数组遍历:<br/>
   	<%
   		String[] strs={"赵灵儿","李逍遥","林月如","唐钰小宝"};
   		pageContext.setAttribute("strs", strs);
   	 %>
   	<c:forEach items="${strs }" var="s">
   		${s }<br/>
   	</c:forEach>
   	
    <hr/>
    varStatus属性<br/>
          需求：让数字所在位置为3的倍数的数字变红色
          <!--varstatus用来接收遍历信息的对象  
          		index 现在指到成员的索引
          		count 现在指到的成员总数
          		first 判断是否是第一个   boolean
          		last  判断是否是最后一个  boolean
          
          -->
    <c:forEach begin="1" end="100" step="2" var="i" varStatus="status">
   		
   		<c:if test="${status.count%3==0}">
   			<font style="color:red">${i }</font>
   		</c:if>
   		<c:if test="${status.count%3!=0}">
   				${i }
   		</c:if>
   		
   		 ${status.last?"":"," }
   	</c:forEach><br/>
   	<hr/>
   	<h3>8.forTokens</h3>
   	<!-- 用来浏览一字符串中所有的成员，其成员是由定义符号所分隔的 -->
    <c:forTokens items="www.easymall.com" delims="." var="s">
    	${s }
    </c:forTokens>
    <hr/>
    <%-- <c:import> 标签,实现include操作
	<c:redirect>标签用于实现请求重定向  -->
	<!-- context指定web应用的虚拟路径 -->
	<%-- <c:redirect context="${pageContext.request.contextPath }" url="/jsp/01.jsp"></c:redirect> --%>
   </body>
</html>
