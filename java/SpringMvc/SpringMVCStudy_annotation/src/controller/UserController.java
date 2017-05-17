package controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import bean.User;

@Controller
public class UserController {
	//页面跳转	
	@RequestMapping("/toAddUser.action")
	public String toAddUser(){
		return "user";
	}
//	@RequestMapping("/addUser.action")
//	public String addUser(String name,String password,String gender,Model model){
//		System.out.println("this is param username:"+name);
//		System.out.println("this is param password:"+password);
//		System.out.println("this is param gender:"+gender);
//		model.addAttribute("msg","this is param  username:"+name+";password:"+password+";gender:"+gender);
//		return "hello";
//	}
	@RequestMapping("/addUser.action")
	public String addUserBean(User user,String[] hobbys,Model model){
		System.out.println(Arrays.toString(hobbys));
		for (String string : hobbys) {
			System.out.println(string);
		}
		model.addAttribute("msg","this is param  :"+user);
		
//		转发到Hello上
//		return "forward:/toHello.action";
		
//		重定向到hello页面上
		return "redirect:/toHello.action";

//		return "hello";
	}
	@RequestMapping("/toHello.action")
	public String toHello(){
		return "hello";
	}

	//初始化绑定
	@InitBinder
	public void InitBinder (ServletRequestDataBinder binder){
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}
