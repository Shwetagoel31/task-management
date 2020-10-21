package com.sg.taskmgmt.persistence.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private LocalDate dateCreated;
	
	private LocalDate dueDate;
	
	private TaskStatus status;

	public Task(String name, String description, LocalDate dateCreated, LocalDate dueDate) {
		super();
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.dueDate = dueDate;
	}

	public Task(String name, String description, LocalDate dateCreated, LocalDate dueDate, TaskStatus status) {
		super();
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Task(Task task) {
		this(task.getName(), task.getDescription(), task.getDateCreated(), task.getDueDate());
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", status=" + status + "]\n";
	}
}
