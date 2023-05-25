package com.cart.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entities.product;

@Repository
public interface productRepositry extends JpaRepository<product, Integer> {
	
	List<product> findByProductNameAndProductId(String productName,int productId);
	List<product> findByProductName(String productName);

}
