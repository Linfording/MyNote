<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>pageContext</title>
  </head>
  <body>	
  	 <h3>作为入口对象获得其他八个对象</h3>
  	 web应用的根路径：${ pageContext.request.contextPath }
  	 
  	 <h3>本身也是一个域对象</h3>
  	 <%pageContext.setAttribute("name", "aauser"); %>
  	 <%=pageContext.getAttribute("name") %>
  	 
  	 <h3>入口对象, 可以操作其他三大作用域</h3>
  	 <% pageContext.setAttribute("addr", "beijing"); %>
  	 <% pageContext.setAttribute("addr", "shanghai",PageContext.APPLICATION_SCOPE); %>
  	 <% pageContext.setAttribute("addr", "shenzhen",PageContext.REQUEST_SCOPE); %>
  	 <% pageContext.setAttribute("addr", "guangzhou",PageContext.SESSION_SCOPE); %>
  	 <%--第三个参数代表存入哪个作用域
  	 PageContext.APPLICATION_SCOPE
	 PageContext.SESSION_SCOPE
	 PageContext.REQUEST_SCOPE
	 PageContext.PAGE_SCOPE 
  	  --%>
  	 <%=pageContext.getAttribute("addr") %>
  	 <%=pageContext.getAttribute("addr",PageContext.APPLICATION_SCOPE) %>
  	 <%=pageContext.getAttribute("addr",PageContext.REQUEST_SCOPE) %>
  	 <%=pageContext.getAttribute("addr",PageContext.SESSION_SCOPE) %>
  	 <%--输出beijing shanghai shenzhen guangzhou --%>
  	 <%--第二个参数，从哪个域中拿属性 --%>
  	 <hr/>
  	 <%=pageContext.findAttribute("name") %>
  	 <%=pageContext.findAttribute("addr") %>
  	 
  	 <% pageContext.removeAttribute("addr",PageContext.PAGE_SCOPE); %>
  	 
  	 <%=pageContext.findAttribute("addr") %>
  	 <%--输出aauser beijing shenzhen --%>
  	 <%--按照由小到大的顺序在四大作用域中搜寻指定名称的属性, 如果找到就返回, 如果都找不到就返回一个 --%>
  	 
  	 <% pageContext.removeAttribute("addr"); %>
  	 
  	 <%=pageContext.findAttribute("addr") %>
  	 <%--输出null --%>
  	 <%--remove删除的时候，如果不指定删除哪个域，将会把所有域中的指定名称属性删除掉 --%>
  	 
  	 <h3>提供了便捷方法实现请求转发和包含</h3>
  	 转发：
  	<% pageContext.forward("/jsp/0111111.jsp"); %>
  	 <hr/>
  	 <hr/>
  	 
  	 包含前...<br/>
  	 
  	 <%--pageContext.include("/jsp/01.jsp"); --%>
  	 
  	 包含后...<br/>
  
  </body>
</html>
