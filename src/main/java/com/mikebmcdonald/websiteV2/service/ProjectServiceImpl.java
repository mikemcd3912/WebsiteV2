package com.mikebmcdonald.websiteV2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikebmcdonald.websiteV2.dao.ProjectDAO;
import com.mikebmcdonald.websiteV2.entity.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;
	
	@Override
	public List<Project> getProjects() {
		return projectDAO.getProjects();
	}

	@Override
	public void saveProject(Project theProject) {
		projectDAO.saveProject(theProject);

	}

	@Override
	public Project getProject(int id) {
		return projectDAO.getProject(id);
	}

}
