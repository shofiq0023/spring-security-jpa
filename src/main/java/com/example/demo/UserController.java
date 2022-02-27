package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("/")
	public String home() {
		return "<h1>This is home page</h1>";
	}
	
	// any one can access this
	@GetMapping("/all")
	public String index() {
		return "This url is open for all";
	}
	
	// only the role with ADMIN can access this
	@GetMapping("/admin")
	public String admin() {
		return "This url is for admin";
	}
	
	// only the role with USER can access this
	@GetMapping("/user")
	public String user() {
		return "This url is for users";
	}
}
