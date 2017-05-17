package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView=new ModelAndView();
//		准备数据
//		.addObject(String attributeName, Object attributeValue)
		modelAndView.addObject("msg","Hello SpringMVC");
//		准备页面名称
//		.setViewName(String viewName)
		modelAndView.setViewName("hello");
		
		return modelAndView;
	}

}
