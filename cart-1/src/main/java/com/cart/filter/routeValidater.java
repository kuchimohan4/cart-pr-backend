package com.cart.filter;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;


@Component
public class routeValidater {
	
	public static final  List<String> openApiEndPoints=List.of(
			"order/products",
			"order/cartProducts/",
			"order/orderPlaced/"
			);
	
	 public Predicate<HttpServletRequest> isSecured = request ->
     openApiEndPoints.stream().noneMatch(uri -> request.getRequestURI().contains(uri));
	

}
