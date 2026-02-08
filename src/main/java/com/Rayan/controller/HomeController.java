package com.Rayan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

	
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to Trading platform";
	}
	
	@GetMapping("/api")
	public String secure() {
		return "Welcome to Trading platform secure part";
	}
}
