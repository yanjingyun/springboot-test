package com.yjy.person.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.person.dao.PersonDao;
import com.yjy.person.entity.Person;

@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;

	@Transactional //不加事务执行save无效
	public void save(Person person) {
		personDao.save(person);
	}

	@Transactional //不加事务执行save无效
	public void deleteById(String id) {
		personDao.deleteById(id);
	}

	public List<Person> findAll() {
		return personDao.findAll();
	}
}
