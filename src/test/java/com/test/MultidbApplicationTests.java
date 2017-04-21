package com.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.business.dao.ds1.PersonDAO;
import com.test.business.dao.ds2.ProductDAO;
import com.test.business.model.ds1.Person;
import com.test.business.model.ds2.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultidbApplicationTests {
	
	@Autowired
	PersonDAO dao1;
	
	@Autowired
	ProductDAO dao2;

	@Test
	public void contextLoads() {
		
		Person p = new Person();
		p.setName(new Date().toString());
		dao1.save(p);
		
		Product pr = new Product();
		pr.setDescription(new Date().toString());
		dao2.save(pr);
		
		for (Person person : dao1.findAll()) {
			System.out.println(person.getName());
		}
		
		for (Product product : dao2.findAll()) {
			System.out.println(product.getDescription());
		}
		
	}

}
