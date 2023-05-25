package com.cart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.entities.cart;
import com.cart.entities.inventry;
import com.cart.entities.product;
import com.cart.exception.informationMisMatchException;
import com.cart.exception.outOfStockException;
import com.cart.repositry.cartRepositry;
import com.cart.repositry.inventryRepositry;
import com.cart.repositry.productRepositry;


@Service
public class cartServiceImpl implements cartService {
	@Autowired
	private cartRepositry cartRepositry;
	@Autowired
	private inventryRepositry inventryRepositry;
	@Autowired
	private productRepositry productRepositry;

	@Override
	public List<product> getProdutList() {
		return productRepositry.findAll();
	}
	
	
	

	@Override
	public cart addToCart(product product,String userName) throws informationMisMatchException,outOfStockException {
		
		
		List<product> products= productRepositry.findByProductNameAndProductId(product.getProductName(),product.getProductId());
		if (products.size()==0) {
			
			throw new informationMisMatchException("there is a mis match with information u have provided");
		}
		
		product product2= products.get(0);

		if (product2.getQuantity()==0) {
			
			return addToWL(product2,userName);
			
		}
		
		List<inventry> avalibleItems=inventryRepositry.findByStatus("avaliable");
		if (avalibleItems.size()==0) {
			return addToWL(product2,userName);
		}
		
		List<cart> carlst=findByQuantityProductNameAndUserName(product.getProductName(),userName);
		if(carlst.size()>=1) {
			throw new informationMisMatchException("you have already added this product to Cart And one can order only one");
			
			
		}
		
		inventry itemDetails=avalibleItems.get(0);
		itemDetails.setStatus("booked");
		
		cart cartItem=new cart(products.get(0).getProductName(), userName, itemDetails, product2);
		
		product2.setQuantity(product2.getQuantity()-1);
		productRepositry.save(product2);
		inventryRepositry.save(itemDetails);
		return cartRepositry.save(cartItem);
	}
	
	
	
	
	

	private List<cart> findByQuantityProductNameAndUserName(String productName, String userName) {
		// TODO Auto-generated method stub
		return null;
	}




	public cart addToWL(product product,String userName)throws outOfStockException {
		
		
		List<cart> productCart=cartRepositry.findByQuantityProductIdAndStatusStartingWith(product.getProductId(),"WL");
		int max=0;
		if(!(productCart.size()==0)) {
			
			for(cart cart:productCart) {
				int num= Integer.parseInt( cart.getStatus().split("-")[1]);
				if (num>max) {
					max=num;
				}
				
			}
		}
		System.out.println(max);
		if(max>=product.getInitialQuantity()/2) {
			throw new outOfStockException("we are currently out of stock for this product sorry for the inconvince");

		}
		
		
		cart wlCart=new cart(product.getProductName(), userName,null, product);
		wlCart.setStatus("WL-"+(max+1));
		
		
		
		return cartRepositry.save(wlCart);
	}




	@Override
	public void addProduct(product product) throws informationMisMatchException {
		List<product> products=productRepositry.findByProductName(product.getProductName());
		if (products.size()==0) {
			product.setInitialQuantity(product.getQuantity());
			productRepositry.save(product);
			
		}else {
			
			throw new informationMisMatchException("a product already exists with product name :"+product.getProductName());
		}
		
		
	}
	
	@Override
	public product updateProduct(product product) throws informationMisMatchException {
		List<product> products= productRepositry.findByProductNameAndProductId(product.getProductName(),product.getProductId());
		if (products.size()==0) {
			
			throw new informationMisMatchException("there is a mis match with information u have provided");
		}
		product product2=products.get(0);
		product.setInventries(product2.getInventries());
		
		return productRepositry.save(product);
	}




	@Override
	public List<cart> productsByUsername(String username) {
		List<cart> cartlst= new ArrayList<>();
		for(cart crt:cartRepositry.findByUserName(username)) {
			if(crt.getInventry()!=null && crt.getInventry().getStatus().equals("booked")) {
				cartlst.add(crt);
			}
			
			
		}
		
		
		return cartlst;
	}




	@Override
	public List<cart> orderplaced(String username) {
		
		for(cart crt:cartRepositry.findByUserName(username)) {
			if(crt.getInventry()!=null) {
				inventry itemdetails=crt.getInventry();
				itemdetails.setStatus("ordered");
				inventryRepositry.save(itemdetails);
				crt.setInventry(itemdetails);
				cartRepositry.save(crt);
			}
			
		}
		
		return null;
	}
	

}
