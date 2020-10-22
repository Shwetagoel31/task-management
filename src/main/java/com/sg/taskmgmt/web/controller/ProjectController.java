package com.sg.taskmgmt.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sg.taskmgmt.events.ProjectCreatedEvent;
import com.sg.taskmgmt.persistence.model.Project;
import com.sg.taskmgmt.service.IProjectService;
import com.sg.taskmgmt.web.dto.ProjectDto;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {
	
	private IProjectService projectService;
	private ModelMapper modelMapper;
	private ApplicationEventPublisher publisher;

	public ProjectController(IProjectService projectService, ModelMapper modelMapper, ApplicationEventPublisher publisher) {
		super();
		this.projectService = projectService;
		this.modelMapper = modelMapper;
		this.publisher = publisher;
	}
	
	@GetMapping("/{id}")
	public ProjectDto findOne(@PathVariable Long id) {
		Project project = projectService.findById(id)
										.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return convertToProjectDto(project);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectDto create(@RequestBody ProjectDto projectDto) {
		Project project = convertToProjectEntity(projectDto);
		project.setDateCreated(LocalDate.now());
		Project newProject = projectService.save(project);
		publisher.publishEvent(new ProjectCreatedEvent(newProject.getId()));
		return convertToProjectDto(newProject);
	}
	
	@GetMapping
	public Collection<ProjectDto> findAll(){
		Iterable<Project> projects = projectService.findAll();
		List<ProjectDto> projectsDto = new ArrayList<>();
		projects.forEach(p -> projectsDto.add(convertToProjectDto(p)));
		return projectsDto;
	}
	
	@GetMapping("/by-name")
	public Collection<ProjectDto> findProjects(@RequestParam(required = false, defaultValue = "") String name){
		Iterable<Project> projects = projectService.findByName(name);
		List<ProjectDto> projectsDto = new ArrayList<>();
		projects.forEach(p -> projectsDto.add(convertToProjectDto(p)));
		return projectsDto;
	}
	
	@PutMapping("/{id}")
	public ProjectDto update(@RequestBody ProjectDto updatedProjectDto) {
		Project updatedProject = convertToProjectEntity(updatedProjectDto);
		return convertToProjectDto(projectService.save(updatedProject));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		projectService.delete(id);
	}
	
	private ProjectDto convertToProjectDto(Project project) {
		return modelMapper.map(project, ProjectDto.class);
	}
	
	private Project convertToProjectEntity(ProjectDto projectDto) {
		return modelMapper.map(projectDto, Project.class);
	}
}
