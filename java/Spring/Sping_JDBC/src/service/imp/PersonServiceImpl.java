package service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PersonDao;
import service.PersonService;
@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	PersonDao personDao;
	@Override
	public void addPerson() {
		personDao.addPerson();
	}

}
