package com.sg.taskmgmt.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sg.taskmgmt.persistence.model.Project;

public interface IProjectRepository extends PagingAndSortingRepository<Project, Long>{
	public Iterable<Project> findByNameContaining(String name);
}
