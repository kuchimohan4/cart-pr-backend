package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.entities.orders;
import com.cart.entities.userInput;
import com.cart.exception.informationMisMatchException;
import com.cart.exception.outOfStockException;
import com.cart.service.cartBooingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
@RequestMapping("/booking")
public class cartController {

	@Autowired
	private cartBooingService cartService;
	
	
	@PostMapping("/placeOrder")
	public ResponseEntity<?>  placeOrder(@RequestBody userInput userInput,@RequestAttribute("userName") String userName) throws outOfStockException, informationMisMatchException 
	{
		orders orders=cartService.placeOrder(userInput,userName);
		if(orders.getOrderId().equals("invalid")) {
			if(orders.getCustomer()!=null) {
				return new ResponseEntity(orders.getCustomer(),HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			return new ResponseEntity("sorry for inconvinace but server seems down",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(orders,HttpStatus.OK);
		
	}
	
	

	@GetMapping("/unauthorized")
	public ResponseEntity<String> unauthorized() {

		return new ResponseEntity<String>("invalid Jwt token or expired", HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/missingAuth")
	public ResponseEntity<String> missingAuth() {

		return new ResponseEntity<String>(
				"Missing JWT token. please provide one. if u dont have one please get one using /auth/token",
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(outOfStockException.class)
	public ResponseEntity<String> handleException(outOfStockException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(informationMisMatchException.class)
	public ResponseEntity<String> handleException1(informationMisMatchException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

}
