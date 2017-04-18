<%@page import="javabeen.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>jsp 标签</title>
  </head>
  
  <body>
   	<h3>获取数据</h3>
   	<%
   		String name ="刘备";
   		pageContext.setAttribute("name", name);
   		pageContext.removeAttribute("name",PageContext.PAGE_SCOPE);
   		pageContext.setAttribute("name", "关羽",PageContext.REQUEST_SCOPE);
   	 %>
   	${ name }
   	<br/>
   	${ "aaa" }
   	
   	<hr/>
   	
   	<%
   		String[] strs={"zhangsan","lisi","wangwu"};
   		pageContext.setAttribute("strs", strs);
   	 %>
   	
   	${strs[0] }
   	${strs[1] }
   	${strs[2] }
   	<hr/>
   	<%
   		List list= new ArrayList();
   		list.add("张三");
   		list.add("李四");
   		list.add("王五");
   		list.add("666");
   		pageContext.setAttribute("list", list);
   	 %>
<%-- 	${ list }
	${ list[0] } --%>
	<%--不能使用方法，因为他不认得！！他只认识pageContext --%>
	
	<hr/>
	<%
		Map map=new HashMap();
		map.put("name", "刘欢");
		map.put("addr", "东北");
		pageContext.setAttribute("map", map);
	 %>
	${map }
	${ map["name"] }
	${ map.addr }
	
	
	<%-- 
		el表达式
		只能获取不能设置!!!
		只能获取不能遍历!!! --%>
		
		<hr/>
		
		<%
		User user=new User();
		user.setUsername("张三丰");
		user.setPassword("1234");
		pageContext.setAttribute("user", user); 
		 %>
		${user }
		${user.username }
		${user.password }
		${user.eat }
		${user.say }
		${user.cat }
		${user.username }
	
  </body>
</html>
