package dao.imp;

import org.springframework.stereotype.Component;

import dao.ProductDao;
@Component("productDao")
public class ProductDaoImpl implements ProductDao{

	@Override
	public void save() {
		System.out.println("save to database");
	}

	@Override
	public void delete() {
		System.out.println("save from database");
		
	}

	@Override
	public void select() {
		System.out.println("select database");
		
	}

}
