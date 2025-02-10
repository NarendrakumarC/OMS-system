package com.ots.exception;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;
	
	public OrderNotFoundException() {
		
	}

	public OrderNotFoundException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	

}
