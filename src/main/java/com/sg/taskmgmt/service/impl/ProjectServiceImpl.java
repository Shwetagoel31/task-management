package com.sg.taskmgmt.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sg.taskmgmt.persistence.model.Project;
import com.sg.taskmgmt.persistence.model.Task;
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
            project.setId(new Random().nextLong());
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

	@Override
	public Project addTasks(Project project, List<Task> tasks) {
		project.getTasks()
				.addAll(tasks.stream()
							.filter(t -> !StringUtils.isEmpty(t.getName()))
							.collect(Collectors.toSet()));
		return projectRepository.save(project);
	}
    
}
