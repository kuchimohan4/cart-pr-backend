package com.cart.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entities.bill;

@Repository
public interface billRepository extends JpaRepository<bill, Integer> {

}
