package com.mikebmcdonald.websiteV2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikebmcdonald.websiteV2.entity.Project;
import com.mikebmcdonald.websiteV2.service.ProjectService;

@Controller
public class RoutingController {
	
	@Autowired
	private ProjectService projectService;
	
	//Public page routes
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
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	
	//CRUD function routes
	@GetMapping("/addNewPjkt")
	public String addForm(Model theModel) {
		Project addition = new Project();
		theModel.addAttribute(addition);
		return "project-form";
	}
	
	@PostMapping("/saveProject")
	public String saveProject(@ModelAttribute("project") Project theProject) {
		projectService.saveProject(theProject);
		return "redirect:/Projects";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("projectId") int theId, Model theModel) {
		Project focusProject = projectService.getProject(theId);
		theModel.addAttribute("project", focusProject);
		return "project-form";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("projectId") int theId) {
		projectService.deleteProject(theId);
		return "redirect:/Projects";
	}
	
	
}
