package com.cart.service;

import java.util.List;

import com.cart.entities.cart;
import com.cart.entities.orders;
import com.cart.entities.product;
import com.cart.entities.userInput;
import com.cart.exception.informationMisMatchException;
import com.cart.exception.outOfStockException;

public interface cartBooingService {

	orders placeOrder(userInput order, String userName) throws outOfStockException, informationMisMatchException;



}
