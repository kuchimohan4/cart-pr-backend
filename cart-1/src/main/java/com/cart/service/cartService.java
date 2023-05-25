package com.cart.service;

import java.util.List;

import com.cart.entities.cart;
import com.cart.entities.product;
import com.cart.exception.informationMisMatchException;
import com.cart.exception.outOfStockException;

public interface cartService {

	List<product> getProdutList();

	cart addToCart(product product,String userName) throws informationMisMatchException, outOfStockException;

	void addProduct(product product)throws informationMisMatchException;
	
	public cart addToWL(product product,String userName)throws outOfStockException;

	product updateProduct(product product) throws informationMisMatchException;

	List<cart> productsByUsername(String username);

	List<cart> orderplaced(String username);



}
