package com.ots.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ots.domain.Order;
import com.ots.domain.User;
import com.ots.dto.OrderVO;
import com.ots.exception.OrderNotFoundException;
import com.ots.exception.OrderNotProcessException;
import com.ots.exception.UserNotFoundException;
import com.ots.repo.OrderRepository;
import com.ots.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	private final OrderRepository orderRepo;
	
	public OrderServiceImpl(OrderRepository repo) {
		this.orderRepo = repo;
	}

	@Autowired
	private UserRepository userRepo;
	
	public OrderVO createOrder(OrderVO ordervo) {
		// *** THE KEY STEP ***
        User user = userRepo.findById(ordervo.getUserId()) // Get the User entity
                .orElseThrow(() -> new UserNotFoundException("User not found")); // Handle if user doesn't exist
		Order order = new Order();
		BeanUtils.copyProperties(ordervo, order);
		order.setUser(user);
		orderRepo.save(order);
		return ordervo;
	}
	
	public List<OrderVO> getAllOrder(){
		List<Order> orderlist=	orderRepo.findAll();
		List<OrderVO> voList = orderlist.stream().map(o->new OrderVO(o.getOrderType(), o.getQuantity(), o.getTotalPrice(),o.getOrderStatus(),o.getUser().getUserId(), o.getStockId()))
				.collect(Collectors.toList());
		return voList;
	}
	
	public OrderVO getOrderById(Long id) {
			Optional<Order>	 order =orderRepo.findById(id);
		OrderVO orderVo = new OrderVO(order.get());
		return orderVo;
	}
	
	public List<OrderVO> getOrderByStatus(String status) {
		Optional<List<Order>> ord =	orderRepo.findByOrderStatus(status);
		List<Order> orders =ord.get();
	       List<OrderVO> orderVo = orders.stream().map(o -> new OrderVO(o.getOrderType(), o.getQuantity(), 
	    		   o.getTotalPrice(),o.getOrderStatus(),o.getUser().getUserId(), o.getStockId())).collect(Collectors.toList());
			return orderVo;
	}
	
	public List<OrderVO> getOrderByType(String type) {
		Optional<List<Order>> order = orderRepo.findByOrderType(type) ;
         List<Order> orders =order.get();
       List<OrderVO> orderVo = orders.stream().map(o -> new OrderVO(o.getOrderType(), o.getQuantity(), 
    		   o.getTotalPrice(),o.getOrderStatus(),o.getUser().getUserId(), o.getStockId())).collect(Collectors.toList());
		return orderVo;
	}
	
	public void deleteOrderById(Long Id) {
		  orderRepo.deleteById(Id);
	}
	
	@Transactional
	public OrderVO updateOrderQuantity(Long orderId, int newQuantity) {
		  log.info("Updating order quantity for order ID: {} to {}", orderId, newQuantity);
		  Optional<Order> order	=  orderRepo.findById(orderId);
		  if (order.isPresent()) {
	            Order o = order.get();

	            if (newQuantity <= 0) {
	                throw new IllegalArgumentException("Quantity must be greater than zero."); // Or a custom exception
	            }
	            o.setQuantity(newQuantity);
	            Order updatedOrder = orderRepo.save(o); // Save the updated order
	            log.info("Order quantity updated successfully: {}", updatedOrder);
	            OrderVO orderVO = new OrderVO();
	            BeanUtils.copyProperties(o, orderVO);
	            return orderVO;
	        } else {
	            log.warn("Order not found with ID: {}", orderId);
	            throw new OrderNotFoundException("Order not found with ID: " + orderId); // Custom exception
	        }
	}
	
}
