package com.cart.repositry;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cart.entities.cart;

@FeignClient(name = "CART-SERVICE")
public interface cartServiceProxy {
	
	@GetMapping("/order/cartProducts/{username}")
	List<cart> productsByUsername(@PathVariable("username") String username);
	
	@GetMapping("/order/orderPlaced/{username}")
	List<cart> orderplaced(@PathVariable("username") String username);

}
