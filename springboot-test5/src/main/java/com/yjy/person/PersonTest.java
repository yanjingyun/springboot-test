package com.yjy.person;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yjy.Application;
import com.yjy.person.entity.Person;
import com.yjy.person.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonTest {

	@Autowired
	private PersonService personService;
	
	@Test
	public void testAdd() {
		Person person = new Person("testAA", 25);
		personService.save(person);
	}
	
	@Test
	public void testUpdate() {
		Person person = new Person("testAA", 23);
		person.setId("297ea8576c0f1c73016c0f1c7c110000");
		personService.save(person);
	}
	
	@Test
	public void testDelete() {
		String personId = "297ea8576c0f1c73016c0f1c7c110000";
		personService.deleteById(personId); //仅逻辑删除
	}
	
	@Test
	public void testFindAll() {
		List<Person> list = personService.findAll();
		for (Person person : list) {
			System.out.println(person);
		}
	}
}
