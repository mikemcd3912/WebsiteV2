package com.mikebmcdonald.websiteV2.dao;

import java.util.List;

import com.mikebmcdonald.websiteV2.entity.Project;

public interface ProjectDAO {

	public List<Project> getProjects();
	
	public void saveProject(Project theProject);
	
	public Project getProject(int id);

	void deleteProject(int id);
	
}
