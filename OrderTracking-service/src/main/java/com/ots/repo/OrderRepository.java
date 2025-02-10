package com.ots.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ots.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>  {
		Optional<List<Order>> findByOrderType(String orderType);
		Optional<List<Order>> findByOrderStatus(String orderStatus);
		
		
}
