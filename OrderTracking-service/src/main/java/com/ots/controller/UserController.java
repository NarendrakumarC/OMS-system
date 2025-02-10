package com.ots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ots.dto.UserDTO;
import com.ots.service.UserServiceImpl;

@RestController("/oms/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl serviceImpl ;

	@PostMapping("/createUser")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
	  UserDTO resp =  serviceImpl.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@GetMapping("/user")
	public ResponseEntity<UserDTO> getUserDetail(@RequestParam(name="userName",required=true) String userName ) {
		UserDTO eachUser= serviceImpl.getUser(userName);
		return ResponseEntity.ok(eachUser);
	}
	@GetMapping("/allUser")
	public ResponseEntity<List<UserDTO>> getAllUserDetails() {
	List<UserDTO> userList=	 serviceImpl.getAllUserData();
		return ResponseEntity.ok(userList);
	}
	
	@GetMapping("/userByIgnoreCase")
	public ResponseEntity<?> getUserEqualIgnoreCase(@RequestParam (name="uname", required =true) String uname){
		return ResponseEntity.ok(serviceImpl.findUserbyIgnoreCase(uname));
		
	}
	
}
