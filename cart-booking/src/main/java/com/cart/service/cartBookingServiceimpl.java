package com.cart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cart.entities.bill;
import com.cart.entities.cart;
import com.cart.entities.coupon;
import com.cart.entities.orders;
import com.cart.entities.userInput;
import com.cart.exception.informationMisMatchException;
import com.cart.exception.outOfStockException;
import com.cart.repositry.billRepository;
import com.cart.repositry.cartServiceProxy;
import com.cart.repositry.couponRepository;
import com.cart.repositry.orderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
@Retry(name = "sample-api", fallbackMethod = "cartfallback")
public class cartBookingServiceimpl implements cartBooingService {
	private Logger logger = LoggerFactory.getLogger(cartBookingServiceimpl.class);

	@Autowired
	private orderRepository orderRepository;
	
	@Autowired
	private billRepository billRepository;

	@Autowired
	private couponRepository couponRepository;
	
	@Autowired
	private cartServiceProxy proxy;

	@CircuitBreaker(name = "placeOrder", fallbackMethod = "cartfallback")
	@Override
	public orders placeOrder(userInput order, String userName) throws outOfStockException, informationMisMatchException {


		List<cart> cartItems = proxy.productsByUsername(userName);
		if (cartItems.size() == 0) {
			throw new outOfStockException("No products avaliable in ur Cart");
		}
		List<String> Products = new ArrayList<>();
		double finalPrice=0;
		for (cart cart : cartItems) {
			Products.add(cart.getProductName());
			finalPrice +=cart.getInventry().getPrice();
		}
//		System.out.println(finalPrice);
		orders finalOrder = new orders(order.getOrderType(), Products.size(), userName, Products, order.getAddress());
		
		finalOrder.genrateOrderId();
		genrateBill(finalOrder,finalPrice,order.getCuopon());
		logger.info(userName+" had sucessfully placed order");
		return finalOrder;
	}
	
	
	
	@CircuitBreaker(name = "placeOrder", fallbackMethod = "cartfallback")
	public orders genrateBill(orders orders, double finalPrice,String cuopon) throws informationMisMatchException {
		
//		couponRepository.save(new coupon( "DEWALI10", 10, 1000, LocalDateTime.of(2023, 04, 15, 0, 0), LocalDateTime.of(2023, 06, 15, 0, 0)));
		List<coupon> coupons= couponRepository.findByCoupon(cuopon);
		if(coupons.size()==0) {
			throw new informationMisMatchException("Invalid cuopon: "+cuopon);
		}
		coupon coupon1=coupons.get(0);
		if (coupon1.getEnddate().compareTo(LocalDateTime.now())<0) {
			throw new informationMisMatchException("cupon expired: "+cuopon);
			
		}
		double totalDiscount =finalPrice*coupon1.getCouponDiscount() < coupon1.getMaxLimit() ? finalPrice*coupon1.getCouponDiscount():coupon1.getMaxLimit();
		proxy.orderplaced(orders.getCustomer());
		orderRepository.save(orders);
		bill bill=new bill(finalPrice, finalPrice-totalDiscount, "Cash",coupon1,totalDiscount , orders);
		billRepository.save(bill);
		orders.setBill(bill);
		orderRepository.save(orders);
	
		return orders;
		
	}
	

	public orders cartfallback(userInput order, String userName, Throwable throwable) {

		
		logger.error("cart-service seems down");
		orders order1=new orders();
		
		
		order1.setOrderId("invalid");
		order1.setCustomer(throwable.getMessage());
		return order1;
	}

}
