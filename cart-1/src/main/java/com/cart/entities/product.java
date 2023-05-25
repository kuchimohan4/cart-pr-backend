package com.cart.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class product {
	
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	@Id
    private int productId;
    private String productName;
    private int quantity;
    private int initialQuantity;
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;
    
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "productId")
    private List<inventry> inventries;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<inventry> getInventries() {
		return inventries;
	}

	public void setInventries(List<inventry> inventries) {
		this.inventries = inventries;
	}
	

	public int getInitialQuantity() {
		return initialQuantity;
	}

	public void setInitialQuantity(int initialQuantity) {
		this.initialQuantity = initialQuantity;
	}

	public product( String productName, int quantity, LocalDateTime date, List<inventry> inventries) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.date = date;
		this.inventries = inventries;
	}

	
	public product( String productName, int quantity, int initialQuantity, LocalDateTime date,
			List<inventry> inventries) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.initialQuantity = initialQuantity;
		this.date = date;
		this.inventries = inventries;
	}

	public product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    
	

}
