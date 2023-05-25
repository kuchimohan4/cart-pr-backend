package com.cart.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entities.inventry;

@Repository
public interface inventryRepositry extends JpaRepository<inventry, Integer>{

	List<inventry> findByQuantityProductIdAndStatus(int ProductId,String status);
	List<inventry> findByStatus(String status);
}
