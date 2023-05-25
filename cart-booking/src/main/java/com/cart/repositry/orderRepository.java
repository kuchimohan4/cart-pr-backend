package com.cart.repositry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entities.orders;

@Repository
public interface orderRepository extends JpaRepository<orders,String> {

}
