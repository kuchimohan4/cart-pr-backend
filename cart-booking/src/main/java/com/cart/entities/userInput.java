package com.cart.entities;

public class userInput {

	private String orderType;
	private String address;
	private String cuopon;
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCuopon() {
		return cuopon;
	}
	public void setCuopon(String cuopon) {
		this.cuopon = cuopon;
	}
	public userInput(String orderType, String address, String cuopon) {
		super();
		this.orderType = orderType;
		this.address = address;
		this.cuopon = cuopon;
	}
	public userInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
