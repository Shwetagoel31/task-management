package com.sg.taskmgmt.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.sg.taskmgmt.persistence.model.Task;

public interface ITaskRepository extends CrudRepository<Task, Long>{
}
