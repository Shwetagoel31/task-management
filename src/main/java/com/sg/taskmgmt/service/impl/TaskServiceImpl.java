package com.sg.taskmgmt.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sg.taskmgmt.persistence.model.Task;
import com.sg.taskmgmt.persistence.repository.ITaskRepository;
import com.sg.taskmgmt.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {
	
	private ITaskRepository taskRepository;
	
	public TaskServiceImpl(ITaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}
}