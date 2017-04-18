<%@page import="javabeen.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>el表达式</title>
  </head>
  
  <body>
   	<h3>执行运算</h3>
  	${3+5 }
  	${3-5 }
  	${3+"5" }
  	<!-- 输出 8， -2， 8 -->
  	<!-- +只作为运算符，不做为连接符 -->
  	<!-- 报错 -->
  	<%-- ${3+"a" } --%>
  	
  	<hr/>
	<%
		String str;
		str="a";
		str=null;
		str="";
		pageContext.setAttribute("str", str);
	 %>
	 ${empty str }
	 
	 <hr/>
	 ${3>5}
	 ${3 gt 5}
	 ${3<5}
	 ${3 lt 5}
	 <%-- 
	 	> gt
		< lt
		>= ge
		<= le
		!= ne
		== eq
	  --%>
	 <hr/>
	 ${3>5 && 3<5 }
	 ${3>5 || 3<5 }
	 ${3>5 and 3<5 }
	 ${3>5 or 3<5 }
	 <%--
	 逻辑运算
		&&	and
		||	or
		!	not
	  --%>
	 
	 <hr/>
	 <%
		String[] strs={};
		Integer[] integers={};
		int[] ints={};
		int[] ints2={1,2,3};
		
		pageContext.setAttribute("strs", strs);
		pageContext.setAttribute("integers", integers);
		pageContext.setAttribute("ints", ints);
		pageContext.setAttribute("ints2", ints2);
	 %>
  	 ${empty str }
  	 ${empty integers }
  	 ${empty ints }
  	 ${empty ints2 }
  	 <!--基本类型的数组  返回为false 无法用于基本类型数组的为空判断-->
  	 <%-- 
  	 	判断对象是否为null, 
		判断字符串是否为空字符串, 
		判断数组是否为空数组, 
		判断集合中是否没有任何元素, 
		判断域对象中是否没有任何属性
  	  --%>
  </body>
</html>
