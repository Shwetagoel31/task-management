package com.sg.taskmgmt.web.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.taskmgmt.persistence.model.Project;
import com.sg.taskmgmt.service.IProjectService;
import com.sg.taskmgmt.web.dto.ProjectDto;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {
	
	private IProjectService projectService;
	private ModelMapper modelMapper;

	public ProjectController(IProjectService projectService, ModelMapper modelMapper) {
		super();
		this.projectService = projectService;
		this.modelMapper = modelMapper;
	}
	
	private ProjectDto convertToProjectDto(Project project) {
		return modelMapper.map(project, ProjectDto.class);
	}
	
	private Project convertToProjectEntity(ProjectDto projectDto) {
		return modelMapper.map(projectDto, Project.class);
	}
}
