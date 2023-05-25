package com.cart.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.cart.entities.product;
import com.cart.repositry.productRepositry;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

@Component
public class saleTimefilter implements Filter {
	
	private int prodId=0;
	private String prodName=" ";
	private String saleTime;

	@Autowired
	private productRepositry productRepositry;
	
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		
		if (httpServletRequest.getRequestURI().contains("addToCart")) {

			
//			ContentCachingRequestWrapper request1 = new ContentCachingRequestWrapper(httpServletRequest);
			
			
			
			BufferedReader bReader = httpServletRequest.getReader();
			String line;
			String prodIdinfo="";
			boolean valid = false;
			while ((line = bReader.readLine()) != null) {
				if (line.contains("productName")) {
					valid = isValid(line);
					break;
				}
				if (line.contains("productId")) {
					prodIdinfo=line;
					
				}

			}
			
			int prodid = Integer.parseInt(prodIdinfo.split(": ")[1].replace("\"", "").replace(",", ""));
			httpServletRequest.setAttribute("productId", prodid);
			httpServletRequest.setAttribute("productName", prodName);
			if (valid) {
				String newuri = "/order/timeError";
				HttpServletRequest wRequest = new HttpServletRequestWrapper(httpServletRequest) {
					@Override
					public String getMethod() {
						return "GET";
					}
				};
				wRequest.setAttribute("saleTime", saleTime);
				httpServletRequest.getRequestDispatcher(newuri).forward(wRequest, response);
			} else {
				chain.doFilter(httpServletRequest, response);

			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public boolean isValid(String productname) {
		
		System.out.println(productname);
		String productnam = productname.split(": ")[1].replace("\"", "").replace(",", "");
		prodName=productnam;
		List<product> products = productRepositry.findByProductName(productnam);
		if (products.size() == 0) {
			return false;
		} else {
			product product = products.get(0);
			saleTime=product.getDate().toString();
			System.out.println(product.getDate().compareTo(LocalDateTime.now()));
			if (product.getDate().compareTo(LocalDateTime.now()) >= 0) {
				return true;
			} else {
				return false;
			}

		}

	}

}
