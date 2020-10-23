package com.sg.taskmgmt.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.taskmgmt.persistence.model.Project;

@SpringBootTest
public class ProjectRepositoryIntegrationTest {

	@Autowired
	private IProjectRepository projectRepository;

	@Test
	public void givenNewProject_thenSaveSuccess() {
		Project project = new Project("First Project", LocalDate.now());
		Project savedProject = projectRepository.save(project);

		assertEquals(project, savedProject);
	}

	@Test
	public void givenNewProject_whenProjectSaved_thenFindByIdSuccess() {
		Project project = new Project("Second Project", LocalDate.now());
		Project savedProject = projectRepository.save(project);

		Optional<Project> retrievedProject = projectRepository.findById(savedProject.getId());

		assertEquals(project, retrievedProject.get());

	}

}
