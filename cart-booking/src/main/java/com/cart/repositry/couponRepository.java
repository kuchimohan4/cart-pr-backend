package com.cart.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entities.coupon;

@Repository
public interface couponRepository extends  JpaRepository<coupon, Integer> {
	
	List<coupon> findByCoupon(String cupon);

}
