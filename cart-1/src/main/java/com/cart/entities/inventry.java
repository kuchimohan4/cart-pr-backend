package com.cart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class inventry {
	
	@Id
	private int itemId;
	private double price;
	private String itemSpecifications;
	private String status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="productId")
	private product quantity;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemSpecifications() {
		return itemSpecifications;
	}

	public void setItemSpecifications(String itemSpecifications) {
		this.itemSpecifications = itemSpecifications;
	}

	public product getQuantity() {
		return quantity;
	}

	public void setQuantity(product quantity) {
		this.quantity = quantity;
	}

	public inventry() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public inventry(int itemId, double price, String itemSpecifications, String status, product quantity) {
		super();
		this.itemId = itemId;
		this.price = price;
		this.itemSpecifications = itemSpecifications;
		this.status = status;
		this.quantity = quantity;
	}



	
	
	

}
