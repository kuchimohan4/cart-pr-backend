package com.cart.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class coupon {
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	@Id
	private int couponId;
	private String coupon;
	private int couponDiscount;
	private int maxLimit;
	@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime enddate;
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public int getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(int couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public int getMaxLimit() {
		return maxLimit;
	}
	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEnddate() {
		return enddate;
	}
	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}
	public coupon( String coupon, int couponDiscount, int maxLimit, LocalDateTime startDate,
			LocalDateTime enddate) {
		super();
//		this.couponId = couponId;
		this.coupon = coupon;
		this.couponDiscount = couponDiscount;
		this.maxLimit = maxLimit;
		this.startDate = startDate;
		this.enddate = enddate;
	}
	public coupon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
