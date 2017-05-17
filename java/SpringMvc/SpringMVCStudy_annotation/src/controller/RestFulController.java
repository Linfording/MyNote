package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestFulController {
	@RequestMapping("/rest/{name}/{age}.action")
	public String rest(@PathVariable String name,@PathVariable Integer age,Model model){
		String msg="this is param:"+name+";age:"+age;
		model.addAttribute("msg",msg);
		return "hello";
	}
}
