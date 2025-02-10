package com.ots.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderNotProcessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log= LoggerFactory.getLogger(OrderNotProcessException.class);
	private final String errorCode;
	private final String errorMessage;
	
	
	public  OrderNotProcessException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		log.error("Order processing exception occurred - Code:{}, Message:{}", errorCode, errorMessage);
	}
	
	 public OrderNotProcessException(String errorCode, String errorMessage, Throwable cause) {
	        super(errorMessage, cause); // Include the cause (original exception)
	        this.errorCode = errorCode;
	        this.errorMessage = errorMessage;
	        log.error("Order processing exception occurred - Code: {}, Message: {}, Cause: {}", errorCode, errorMessage, cause.getMessage(), cause); // Log with cause
	    }

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	 

}
