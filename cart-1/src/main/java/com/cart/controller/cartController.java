package com.cart.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.entities.cart;
import com.cart.entities.inventry;
import com.cart.entities.product;
import com.cart.exception.informationMisMatchException;
import com.cart.exception.outOfStockException;
import com.cart.repositry.productRepositry;
import com.cart.service.cartService;

@RestController
@RequestMapping("/order")
public class cartController {

	@Autowired
	private cartService cartService;
	@Autowired
	productRepositry pRepositry;

	@GetMapping("/products")
	List<product> getProdutList() {

		return cartService.getProdutList();

	}
	
	@GetMapping("/cartProducts/{username}")
	List<cart> productsByUsername(@PathVariable("username") String username) {

		return cartService.productsByUsername(username);

	}

	@PostMapping("/addToCart")
	cart addToCart(/* @RequestBody product product */@RequestAttribute("productId") int prodId,
			@RequestAttribute("productName") String productName, @RequestAttribute("userName") String userName)
			throws informationMisMatchException, outOfStockException {

		product prod = new product();
		prod.setProductId(prodId);
		prod.setProductName(productName);

		return cartService.addToCart(prod, userName);

	}

	@PostMapping("/addProduct")
	List<product> addProduct(@RequestBody product product) throws informationMisMatchException {

		cartService.addProduct(new product(product.getProductName(), product.getQuantity(),product.getInitialQuantity(), product.getDate(),
				new ArrayList<inventry>()));

		return getProdutList();

	}

	@PutMapping("/updateProduct")
	product updateProduct(@RequestBody product product) throws informationMisMatchException {

		return cartService.updateProduct(product);

	}
	
	
	@GetMapping("/orderPlaced/{username}")
	List<cart> orderplaced(@PathVariable("username") String username) {

		return cartService.orderplaced(username);

	}

	@GetMapping("/timeError")
	public ResponseEntity<String> hello(@RequestAttribute("saleTime") String saleTime) {
		System.out.println(saleTime);

		return new ResponseEntity<String>("Sale hasn't started at please revesit after :" + saleTime,
				HttpStatus.BAD_REQUEST);
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
