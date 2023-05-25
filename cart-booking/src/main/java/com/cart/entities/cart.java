package com.cart.entities;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class cart {
	

	private int id;
	private String ProductName;
	private String  userName;
	private String status;
	private inventry inventry;
	private product quantity;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}



	public inventry getInventry() {
		return inventry;
	}

	public void setInventry(inventry inventry) {
		this.inventry = inventry;
	}

	public product getQuantity() {
		return quantity;
	}

	public void setQuantity(product quantity) {
		this.quantity = quantity;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public cart( String productName, String userName, com.cart.entities.inventry inventry, product quantity) {
		super();
		ProductName = productName;
		this.userName = userName;
		this.inventry = inventry;
		this.quantity = quantity;
	}

	public cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
