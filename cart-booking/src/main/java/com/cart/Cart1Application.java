package com.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Cart1Application {

	public static void main(String[] args) {
		SpringApplication.run(Cart1Application.class, args);
	}

}
