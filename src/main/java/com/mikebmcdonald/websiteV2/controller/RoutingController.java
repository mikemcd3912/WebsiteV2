package com.mikebmcdonald.websiteV2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mikebmcdonald.websiteV2.entity.Project;
import com.mikebmcdonald.websiteV2.service.ProjectService;

@Controller
public class RoutingController {
	
	@Autowired
	private ProjectService projectService;
	
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
	public String projects(Model theModel) {
		List<Project> projectList =  projectService.getProjects();
		theModel.addAttribute("project", projectList);
		return "projects";
	}
	
	@GetMapping("/Contact")
	public String contact() {
		return "contact";
	}
}
