package servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.ProductService;

@Component
public class ProductServlet {
	@Autowired
	ProductService productService;
	public void save(){
		productService.save();
	}
	public void error(){
		int a=1/0;
	}
	public void add(){
		new Thread(){
			public void run() {
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.run();
		System.out.println("ProductServlet add");
	}
	public void delete(){
		productService.delete();
	}
	public void select(){
		productService.select();
	}
}
