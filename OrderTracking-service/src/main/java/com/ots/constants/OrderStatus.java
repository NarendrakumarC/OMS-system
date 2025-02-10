package com.ots.constants;

public enum OrderStatus {
	PENDING("PENDING"),
	COMPLETED("COMPLETED"),
	CANCELED("CANCELED");
	
	private final String status;

	private OrderStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
}
