package com.mikebmcdonald.websiteV2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mikebmcdonald.websiteV2.entity.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Project> getProjects() {
		
		Session currentSession = sessionFactory.openSession();
		
		Query<Project> getList = currentSession.createQuery("from Project", Project.class);
		
//		currentSession.close();
		return getList.getResultList();
	}

	@Override
	public void saveProject(Project theProject) {
		Session currentSession = sessionFactory.openSession();
		currentSession.saveOrUpdate(theProject);
		currentSession.close();
	}

	@Override
	public Project getProject(int id) {
		Session currentSession = sessionFactory.openSession();
		currentSession.close();
		return currentSession.get(Project.class, id);
		
	}

}