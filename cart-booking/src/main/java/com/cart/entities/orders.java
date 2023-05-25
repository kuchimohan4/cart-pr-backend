package com.cart.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class orders {

	@Id
	@Column(length = 36)
	private String orderId;
	private String orderType;
	private int noOfProducts;
	private String customer;
	private List<String> productsOrdered;
	private String address;
	
	@OneToOne
	@JoinColumn(name = "billId")
	private bill bill;
	
	public bill getBill() {
		return bill;
	}
	public void setBill(bill bill) {
		this.bill = bill;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getNoOfProducts() {
		return noOfProducts;
	}
	public void setNoOfProducts(int noOfProducts) {
		this.noOfProducts = noOfProducts;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public List<String> getProductsOrdered() {
		return productsOrdered;
	}
	public void setProductsOrdered(List<String> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public orders( String orderType, int noOfProducts, String customer, List<String> productsOrdered,
			String address) {
		
		this.orderType = orderType;
		this.noOfProducts = noOfProducts;
		this.customer = customer;
		this.productsOrdered = productsOrdered;
		this.address = address;
	}
	public orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void genrateOrderId() {
		
		this.orderId=UUID.randomUUID().toString().split("-",5)[0];
	}
	
	
	
	
	
}
