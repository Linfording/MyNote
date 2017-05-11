package service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import anno.PrivilegeInfo;
import anno.Transactiona;

import dao.ProductDao;

import service.ProductService;
@Component("productService")
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactiona
	@PrivilegeInfo("insert")
	public void save() {
		productDao.save();
	}

	@Override
	@Transactiona
	@PrivilegeInfo("delete")
	public void delete() {
		productDao.delete();
	}

	@Override
//	@PrivilegeInfo("select")
	public void select() {
		productDao.select();
	}
	
	
}
