package com.ots.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public UserNotFoundException(String str) {
		this.msg = str;
	}

}
