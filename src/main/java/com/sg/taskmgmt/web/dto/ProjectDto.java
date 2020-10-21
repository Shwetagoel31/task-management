package com.sg.taskmgmt.web.dto;

import java.time.LocalDate;
import java.util.Set;

import com.sg.taskmgmt.persistence.model.Task;

public class ProjectDto {
	
	private Long id;
	
    private String name;
    
    private LocalDate dateCreated;
    
    private Set<Task> tasks;

	public ProjectDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectDto(Long id, String name, LocalDate dateCreated) {
		super();
		this.id = id;
		this.name = name;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
