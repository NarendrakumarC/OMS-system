package com.ots.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ots.dto.OrderVO;
import com.ots.exception.ErrorResponse;
import com.ots.exception.OrderNotProcessException;
import com.ots.service.OrderServiceImpl;

import jakarta.validation.Valid;

@RestController("/oms/orders")
public class OrderController {
	
	private static final org.slf4j.Logger _log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderServiceImpl serviceImpl;
	
	@PostMapping("/createOrder")
	public ResponseEntity<?> createOrder(@RequestBody @Valid OrderVO orderVO){
		_log.info("Received request to create order :{}", orderVO);
		try {
			 try {
	                //If any error occurs during setting order properties from orderDTO, throw exception here.
	                if (orderVO.getQuantity() < 1) {
	                    throw new IllegalArgumentException("Quantity must be greater than zero");
	                }
	              
	            } catch (IllegalArgumentException ex) {
	                throw new OrderNotProcessException("ORDER_INVALID_INPUT", "Invalid order input", ex); // Wrap original exception

	            }
			OrderVO	 order =serviceImpl.createOrder(orderVO);
			_log.info("Order created successfully: {}", order);
			return ResponseEntity.status(HttpStatus.CREATED).body(order);
		}catch(OrderNotProcessException e) {
			_log.error("Error creating order: {}", e.getErrorMessage(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(e.getErrorCode(),e.getErrorMessage()));
		}catch (Exception ex) {
			_log.error("Unexpected error in order processing: {}", ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("INTERNAL_ERROR", "An unexpected error occured"));
		}
		
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<?> getOrderById(@PathVariable(name = "orderId", required = true) Long orderId){
		 OrderVO order =   serviceImpl.getOrderById(orderId);
		 return ResponseEntity.ok(order);
	}
	
	@GetMapping("/order/type/{type}")
	public ResponseEntity<?> getOrderByType(@PathVariable(name = "type", required = true) String type){
		 List<OrderVO> order =serviceImpl.getOrderByType(type);
		  return ResponseEntity.ok(order);
	}
	
	@GetMapping("/order/status/{status}")
	public ResponseEntity<?> getOrderByStatus(@PathVariable(name = "status", required = true) String status){
		 List<OrderVO> order =serviceImpl.getOrderByStatus(status);
		  return ResponseEntity.ok(order);
	}
	
	@GetMapping("/allOrders")
	public ResponseEntity<?> getAllOrders(){
		 List<OrderVO> order =serviceImpl.getAllOrder();
		  return ResponseEntity.ok(order);
	}
	
	@DeleteMapping("/delete/{orderId}")
	public void deleteOrder(@PathVariable(name = "orderId", required = true) Long orderId){
		serviceImpl.deleteOrderById(orderId);
		_log.info("Order deleted successfully {} "+orderId);
	
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateOrder(@RequestParam Long orderId, @RequestParam int newQuantity){
		OrderVO ordervo=serviceImpl.updateOrderQuantity(orderId, newQuantity);
		_log.info("Updated Order Details {} "+ordervo);
		return ResponseEntity.ok(ordervo);
	}
	

}
