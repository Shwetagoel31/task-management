package com.sg.taskmgmt.service;

import java.util.Optional;

import com.sg.taskmgmt.persistence.model.Task;

public interface ITaskService {

    Optional<Task> findById(Long id);

    Task save(Task task);
}