package com.sg.taskmgmt.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.taskmgmt.persistence.model.Task;

@SpringBootTest
public class TaskRepositoryIntegrationTest {
	
	@Autowired
	private ITaskRepository taskRepository;

	@Test
	public void givenNewTask_thenSaveSuccess() {
		Task task = new Task("First Task", "First Task", LocalDate.now(), LocalDate.now().plusDays(2));
		Task savedTask = taskRepository.save(task);
		
		assertEquals(task, savedTask);
	}
	
	@Test
	public void givenNewTask_whenTaskSaved_thenFindByIdSuccess() {
		Task task = new Task("First Task", "First Task", LocalDate.now(), LocalDate.now().plusDays(2));
		Task savedTask = taskRepository.save(task);
		Optional<Task> retrievedTask = taskRepository.findById(savedTask.getId());
		
		assertEquals(task, retrievedTask.get());
		
	}

}
