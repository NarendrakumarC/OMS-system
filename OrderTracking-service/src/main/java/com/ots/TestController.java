package com.ots;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/hello")
	public String welcome() {
		return "Welcome to Order Tracking System";
	}
}
