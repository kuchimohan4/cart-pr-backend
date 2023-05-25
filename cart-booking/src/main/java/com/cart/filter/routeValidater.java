package com.cart.filter;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class routeValidater {
	
	public static final  List<String> openApiEndPoints=List.of(
			"/products"
			);
	
	public Predicate<ServerHttpRequest> isSecured=
			request -> openApiEndPoints
			.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
	

}
