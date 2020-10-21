package com.sg.taskmgmt.persistence.repository;

import java.util.Optional;

import com.sg.taskmgmt.persistence.model.Project;

public interface IProjectRepository {
	
	 Optional<Project> findById(Long id);
	 Project save(Project project);

}
