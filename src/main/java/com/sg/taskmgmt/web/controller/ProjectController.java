package com.sg.taskmgmt.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.sg.taskmgmt.events.ProjectCreatedEvent;
import com.sg.taskmgmt.persistence.model.Project;
import com.sg.taskmgmt.persistence.model.Task;
import com.sg.taskmgmt.service.IProjectService;
import com.sg.taskmgmt.web.dto.ProjectDto;
import com.sg.taskmgmt.web.dto.TaskDto;
import com.sg.taskmgmt.web.dto.TaskListDto;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

	private IProjectService projectService;
	private ModelMapper modelMapper;
	private ApplicationEventPublisher publisher;

	public ProjectController(IProjectService projectService, ModelMapper modelMapper,
			ApplicationEventPublisher publisher) {
		super();
		this.projectService = projectService;
		this.modelMapper = modelMapper;
		this.publisher = publisher;
	}

	@GetMapping("/{id}")
	public String getProject(@PathVariable Long id, Model model) {
		Project project = projectService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		model.addAttribute("project", convertToProjectDto(project));
		return "project";
	}

	@PostMapping
	public String addProject(@Valid @ModelAttribute("project") ProjectDto projectDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "new-project";
		}
		Project project = convertToProjectEntity(projectDto);
		Project newProject = projectService.save(project);
		publisher.publishEvent(new ProjectCreatedEvent(newProject.getId()));
		return "redirect:/projects";
	}

	@GetMapping
	public String getProjects(Model model) {
		Iterable<Project> projects = projectService.findAll();
		List<ProjectDto> projectsDto = new ArrayList<>();
		projects.forEach(p -> projectsDto.add(convertToProjectDto(p)));
		model.addAttribute("projects", projectsDto);
		return "projects";
	}

	@GetMapping("/new")
	public String newProject(Model model) {
		model.addAttribute("project", new ProjectDto());
		return "new-project";
	}
	
	@GetMapping("/{id}/add-tasks")
	public String getProjectEditPage(@PathVariable Long id, Model model) {
		Project project = projectService.findById(id).orElse(new Project());
		model.addAttribute("project", project);
		
		TaskListDto tasksForm = new TaskListDto();
		
		for(int i = 1; i <= 3; i++) {
			tasksForm.addTask(new TaskDto());
		}
		
		model.addAttribute("tasksForm", tasksForm);
		return "add-tasks";
	}

	@PostMapping("/{id}/save-tasks")
	public String saveTasks(@PathVariable Long id, @ModelAttribute TaskListDto tasksForm, Model model) {
		Project project = projectService.findById(id).orElse(new Project());
		projectService.addTasks(project, tasksForm
												.getTasks()
												.stream()
												.map(t -> convertToTaskEntity(t))
												.collect(Collectors.toList()));
		model.addAttribute("project", project);
		return "redirect:/projects/" + project.getId();
	}
	private ProjectDto convertToProjectDto(Project project) {
		return modelMapper.map(project, ProjectDto.class);
	}

	private Project convertToProjectEntity(ProjectDto projectDto) {
		return modelMapper.map(projectDto, Project.class);
	}
	
	private TaskDto convertToTaskDto(Task task) {
		return modelMapper.map(task, TaskDto.class);
	}
	
	private Task convertToTaskEntity(TaskDto taskDto) {
		return modelMapper.map(taskDto, Task.class);
	}
}
