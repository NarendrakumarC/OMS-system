package com.ots.dto;

import com.ots.domain.Order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderVO {
	@NotNull(message = "Type is required")
	private String orderType;

	@NotNull(message = "Quantity is required")
	@Positive(message = "Quantity must be positive number")
	private Integer quantity;
	
	@NotNull(message = "TotalPrice is required")
	@DecimalMin(value = "0.01", message = "Price must be at least 0.01")
	private Double totalPrice;
	
	private String orderStatus;
	
	@NotNull(message = "User Id is required")
	@Positive
	private Long userId;
	
	@NotNull(message = "Stock Id is required")
	@Positive
	private Long stockId;
	
	public OrderVO(String orderType, Integer quantity, Double totalPrice, String orderStatus, Long userId, Long stockId) {
		this.orderType = orderType;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.userId = userId;
		this.stockId = stockId;
	}
	
	public OrderVO(Order other) {
		this.orderStatus = other.getOrderStatus();
		this.orderType = other.getOrderType();
		this.quantity = other.getQuantity();
		this.totalPrice = other.getTotalPrice();
		this.userId = other.getUser().getUserId();
		this.stockId = other.getStockId();
	}
	
	

	public OrderVO() {
		// TODO Auto-generated constructor stub
	}

	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	
	

}
