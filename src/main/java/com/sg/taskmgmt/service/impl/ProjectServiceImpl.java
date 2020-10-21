package com.sg.taskmgmt.service.impl;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.sg.taskmgmt.persistence.model.Project;
import com.sg.taskmgmt.persistence.repository.IProjectRepository;
import com.sg.taskmgmt.service.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {
	//public class ProjectServiceImpl implements IProjectService, ApplicationContextAware {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private IProjectRepository projectRepository;
    
	/*
	 * @Autowired private IProjectRepository projectRepository2;
	 */

	@Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }
    
    @PostConstruct
    public void afterPropertiesSet() {
    	LOG.info("POST CONSTRUCT in ProjectServiceImpl called");
    }
    
    @PreDestroy
    public void destroy() {
    	LOG.info("PRE DESTROY in ProjectServiceImpl called");
    }

	/*
	 * @Override public void setApplicationContext(ApplicationContext
	 * applicationContext) throws BeansException { // TODO Auto-generated method
	 * stub LOG.info("CONTEXT WITH ID {} SET", applicationContext.getId());
	 * 
	 * }
	 */
    
}
