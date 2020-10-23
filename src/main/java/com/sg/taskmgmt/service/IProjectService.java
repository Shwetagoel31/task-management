package com.sg.taskmgmt.service;

import java.util.List;
import java.util.Optional;

import com.sg.taskmgmt.persistence.model.Project;
import com.sg.taskmgmt.persistence.model.Task;

public interface IProjectService {

    Optional<Project> findById(Long id);

    Project save(Project project);
    
    Iterable<Project> findAll();
    
    void delete(Long id);
    
    Iterable<Project> findByName(String name);
    
    Project addTasks(Project project, List<Task> tasks);
}
