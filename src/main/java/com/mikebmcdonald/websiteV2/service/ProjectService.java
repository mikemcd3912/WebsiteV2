package com.mikebmcdonald.websiteV2.service;

import java.util.List;

import com.mikebmcdonald.websiteV2.entity.Project;

public interface ProjectService {
	
	public List<Project> getProjects();
	
	public void saveProject(Project theProject);
	
	public Project getProject(int id);

	public void deleteProject(int theId);
	
}
