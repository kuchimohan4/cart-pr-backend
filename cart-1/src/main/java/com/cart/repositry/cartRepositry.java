package com.cart.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entities.cart;

@Repository
public interface cartRepositry extends JpaRepository<cart, Integer> {
	
	List<cart> findByQuantityProductIdAndStatusStartingWith(int productId, String wL);
	List<cart> findByUserName(String userName);
	List<cart> findByQuantityProductNameAndUserName(String quantityProductName, String userName);

}
