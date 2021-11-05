package com.mikebmcdonald.websiteV2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/AboutMe")
	public String aboutMe() {
		return "aboutMe";
	}
	
	@GetMapping("/Error")
	public String error() {
		return "error";
	}
	
	@GetMapping("/Experience")
	public String experience() {
		return "experience";
	}
	
	@GetMapping("/Projects")
	public String projects() {
		return "projects";
	}
	
	@GetMapping("/Contact")
	public String contact() {
		return "contact";
	}
}
