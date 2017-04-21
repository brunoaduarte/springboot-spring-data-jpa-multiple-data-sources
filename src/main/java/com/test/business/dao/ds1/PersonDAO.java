package com.test.business.dao.ds1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.business.model.ds1.Person;

public interface PersonDAO extends JpaRepository<Person, Integer> {

}
