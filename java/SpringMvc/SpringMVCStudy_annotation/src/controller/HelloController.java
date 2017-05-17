package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController{

//	针对用户输入的url--匹配对应的方法
	@RequestMapping("/hello.action")
	public String hello(Model model){
		//Model对象用来存储数据
		model.addAttribute("msg", "Hello SpringMVC annotation");
		
		//返回View
		return "hello";//返回用户的展现页面
	}
	
	@RequestMapping("/helloSpringMVC.action")
	public String helloSpringMVC(Model model){
		//Model对象用来存储数据
		model.addAttribute("msg", "helloSpringMVC annotation");
		
		//返回View
		return "hello";//返回用户的展现页面
	}
	@RequestMapping("/args.action")
	public String argsMethod(Model model,String name,Integer age){
		
		model.addAttribute("msg","this is param name:"+name+";age="+age);
		
		return "hello";
	}
}
