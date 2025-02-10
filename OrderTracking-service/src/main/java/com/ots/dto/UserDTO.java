package com.ots.dto;

import com.ots.domain.User;

public class UserDTO {

	private String userName;
	
	private String passwordHash;
	
	private String email;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDTO(String userName, String passwordHash, String email) {
		this.userName = userName;
		this.passwordHash = passwordHash;
		this.email = email;
	}

	public UserDTO() {
	}
	
	public UserDTO(User otheruser) {
		this.userName = otheruser.getUserName();
		this.email = otheruser.getEmail();
		this.passwordHash = otheruser.getPasswordHash();
	}
	
}
