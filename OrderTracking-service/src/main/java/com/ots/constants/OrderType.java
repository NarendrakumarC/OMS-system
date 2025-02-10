package com.ots.constants;

public enum OrderType {
	BUY("BUY"),
	SELL("SELL");
	
	private final String type;
	
	OrderType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return type;
	}
	
}
