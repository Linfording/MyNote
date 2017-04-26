<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>欢迎注册EasyMall</title>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="css/regist.css"/>
	<script src="./js/jquery-3.2.1.js"></script>
 	<script type="text/javascript">
 		$().ready(function(){
			$("input[type!=submit]").blur(function(){
				if($(this).is("input[name=username]")){
					if(formObj.isNull("#username_msg","请输入用户名")){
//						不为空，ajax验证，判断用户名是否存在
						var username=$("input[name=username]").val();
						//方式一：jquery.load
						<%-- $("#username_msg").load("<%= request.getContextPath() %>/AjaxCheckUsernameServlet",{username:username},function(responseText, textStatus, jqXHR){
							//responseText, textStatus, jqXHR
							// responseText 是响应(返回)的原始文本数据
    						// textStatus 可能是 "success"、 "notmodified"、 "error"、 "timeout"、 "abort"或"parsererror"中的一个
    						// jqXHR 是经过jQuery封装的XMLHttpRequest对象(保留其本身的所有属性和方法)
							//alert(jqXHR);
						}); --%>
						$("#username_msg").load("<%= request.getContextPath() %>/AjaxCheckUsernameServlet",{username:username});
						//方式二：jquery.post
						<%-- $.post("<%= request.getContextPath() %>/AjaxCheckUsernameServlet", {username: username}, function(result){
						$("#username_msg").html(result);
						}); --%>
						//方式三：jquery.get
						<%--  $.get("<%= request.getContextPath() %>/AjaxCheckUsernameServlet", {username: username}, function(result){
						$("#username_msg").html(result);
						});  --%>
						//方式四：底层 AJAX 实现
						//这个是jQuery 底层 AJAX 实现，一般不会需要用到，可以看到非常大的灵活性，所以在有特殊需求的时候可以查看API使用
						<%-- $.ajax({
								"url":"<%= request.getContextPath() %>/AjaxCheckUsernameServlet",
								"type":"post",
								"data":{username:username},
								"dataType":"text",
								"success":function(obj){
									//处理服务器返回的数据
									/*
										obj是服务器返回的数据。
										如果服务器返回的是json字符串，
										会自动转换成对应的javascript对象。
									*/
									$("#username_msg").html(obj);
								},
								"error":function(){
									//服务器出错，在这儿处理
									alert("服务器炸了");
								}
						}); --%>
					};
				}
				if($(this).is("input[name=password]")){
					formObj.checkpassword("#password_msg","请输入密码");
				}
				if($(this).is("input[name=password2]")){
					formObj.checkpassword("#password2_msg","请输入确认密码");
				}
				if($(this).is("input[name=nickname]")){
					formObj.isNull("#nickname_msg","请输入昵称");
				}
				if($(this).is("input[name=email]")){
					formObj.checkEmail();
				}
				if($(this).is("input[name=valistr]")){
					formObj.checkValistr();
				}
			});
			$("form").submit(function (){
				return formObj.checkAll();
			});
			$("img").click(function(){
			//给验证码img不一样的访问地址，这样浏览器会自动去加载新的验证码
				this.src=this.src+"?time="+new Date().getTime();
			});
		});
		var formObj={
				checkAll:function(){
					flag=this.isNull("#username_msg","请输入用户名")&this.checkpassword("#password_msg","请输入密码")
					&this.checkpassword("#password2_msg","请输入确认密码")&this.isNull("#nickname_msg","请输入昵称")
					&this.checkEmail()&this.checkValistr(); 
					if (!flag) {
						return false;
					}
					return true;
				},
				checkValistr:function (){
					formObj.setMsg("#valistr_msg","");
					if($.isEmptyObject($.trim($("input[name=valistr]").val()))){
						formObj.setMsg("#valistr_msg","请输入验证码");
						return false;
					}
					return true;
				},
				checkEmail:function (){
					this.setMsg("#email_msg","");
					if($.isEmptyObject($("input[name=email]").val())){
						this.setMsg("#email_msg","请输入邮箱");
						return false;
					}else{
						if(!/^\w+@\w+(\.\w+)+$/.test($("input[name=email]").val())){
							this.setMsg("#email_msg","请输入正确的邮箱");
						return false;
						}
						return true
					}
				},
				checkpassword:function (name,msg){
					this.setMsg(name,"");
					if($.isEmptyObject($.trim($(name).prev().val()))){
						this.setMsg(name,msg);
						return false;
					} else{
						var psw=$("input[name=password]").val();
						var psw2=$("input[name=password2]").val();
						if(psw2!= "" && psw != psw2){
							this.setMsg("#password2_msg","两次密码不一致");
							return false;
						}else if (psw == psw2) {
							setMsg("#password2_msg","");
							return true;
						}
					}
				},
				isNull:function (name,msg){
					this.setMsg(name,"");
					if($.isEmptyObject($.trim($(name).prev("input").val()))){
						//为空，返回false
						this.setMsg(name,msg);
							return false
						}
//					不为空，true
					return true;
				},
				setMsg:function (name,msg){
					$(name).text(msg);
				}
		}
 	</script>
 		
</head>
<body>
	<h1>欢迎注册EasyMall</h1>
	<form action="<%=request.getContextPath() %>/RegistServlet" method="POST" onsubmit="">
		<table>
			<tr>
				<td class="tds">用户名：</td>
				<td><input type="text" name="username" value="<%=request.getParameter("username")==null?"":request.getParameter("username") %>">
				<span class="errfont" id="username_msg"><%=request.getAttribute("usernameerr")==null?"":request.getAttribute("usernameerr") %></span>
				</td>
			</tr>
			<tr>
				<td class="tds">密码：</td>
				<td><input type="password" name="password" value="<%=request.getParameter("password")==null?"":request.getParameter("password") %>">
				<span class="errfont" id="password_msg"><%=request.getAttribute("passworderr")==null?"":request.getAttribute("passworderr") %></span>
				</td>
			</tr>
			<tr>
				<td class="tds">确认密码：</td>
				<td><input type="password" name="password2" value="<%=request.getParameter("password2")==null?"":request.getParameter("password2") %>">
				<span class="errfont" id="password2_msg"><%=request.getAttribute("password2err")==null ? "":request.getAttribute("password2err") %></span>
				</td>
			</tr>
			<tr>
				<td class="tds">昵称：</td>
				<td><input type="text" name="nickname" value="<%=request.getParameter("nickname")==null?"":request.getParameter("nickname") %>">
				<span class="errfont" id="nickname_msg"><%=request.getAttribute("nicknameerr")==null?"":request.getAttribute("nicknameerr") %></span>
				</td>
			</tr>
			<tr>
				<td class="tds">邮箱：</td>
				<td><input type="text" name="email" value="<%=request.getParameter("email")==null?"":request.getParameter("email") %>">
				<span class="errfont" id="email_msg"><%=request.getAttribute("emailerr")==null?"":request.getAttribute("emailerr") %></span>
				</td>
			</tr>
			<tr>
				<td class="tds">验证码：</td>
				<td><input type="text" name="valistr">
				<img id="yzm_img" src="<%=request.getContextPath() %>/ValiImageServlet" style="cursor: pointer"/>
				<span class="errfont" id="valistr_msg"><%=request.getAttribute("valistrerr")==null?"":request.getAttribute("valistrerr") %></span>
				</td>
			</tr>
			<%--<script type="text/javascript">
			//由于用了jsp的特性，所以此段代码无法放入.js文件内,js有另外写法
				 $("img").click(function(){
					this.src="<%=request.getContextPath() %>/ValiImageServlet?time="+new Date().getTime();
				}); 
			</script> --%>
			<tr>
				<td colspan="2">
					<input type="submit" value="注册用户"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
