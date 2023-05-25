package com.cart.entities;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class bill {
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	@Id
	private int billId;
	private double totalAmount;
	private double payableAmount;
	private String paymentMode;
	@OneToOne
	@JoinColumn(name = "couponId")
	private coupon coupon;
	private double discountAmount;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "orderId")
	private orders order;
	public bill( double totalAmount, double payableAmount, String paymentMode,
			com.cart.entities.coupon coupon, Double discountAmount, orders order) {
		super();
//		this.billId = billId;
		this.totalAmount = totalAmount;
		this.payableAmount = payableAmount;
		this.paymentMode = paymentMode;
		this.coupon = coupon;
		this.discountAmount = discountAmount;
		this.order = order;
	}
	public bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(coupon coupon) {
		this.coupon = coupon;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public orders getOrder() {
		return order;
	}
	public void setOrder(orders order) {
		this.order = order;
	}
	
	

}
