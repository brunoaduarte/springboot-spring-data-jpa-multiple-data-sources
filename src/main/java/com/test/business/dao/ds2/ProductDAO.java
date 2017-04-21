package com.test.business.dao.ds2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.business.model.ds2.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

}
