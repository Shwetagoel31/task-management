package com.sg.taskmgmt.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sg.taskmgmt.persistence.model.Project;
import com.sg.taskmgmt.persistence.repository.IProjectRepository;
import com.sg.taskmgmt.service.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {
	
	private IProjectRepository projectRepository;
	
	public ProjectServiceImpl(IProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}

	@Override
	public Project save(Project project) {
		if (StringUtils.isEmpty(project.getId())) {
            project.setDateCreated(LocalDate.now());
        }
		return projectRepository.save(project);
	}

	@Override
	public Iterable<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Iterable<Project> findByName(String name) {
		return projectRepository.findByNameContaining(name);
	}
    
}
