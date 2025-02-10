package com.ots.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.ots.constants.OrderStatus;
import com.ots.constants.OrderType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_Id")
	private Long orderId;
	
	@Column(name = "order_type")
	private String orderType;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@JoinColumn(name = "user_id")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@Column(name = "stock_id")
	private Long stockId;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@PrePersist
	public void onCreate() {
		ZonedDateTime dt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		this.createdAt = dt.toLocalDateTime();
		this.orderStatus = OrderStatus.PENDING.toString();
		this.orderType = OrderType.BUY.toString();
	}
	

}
