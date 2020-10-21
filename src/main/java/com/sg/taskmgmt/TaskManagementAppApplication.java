package com.sg.taskmgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sg.taskmgmt.service.IProjectService;

@SpringBootApplication
public class TaskManagementAppApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskManagementAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementAppApplication.class, args);
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.sg.taskmgmt.persistence.repository");
		ctx.scan("com.sg.taskmgmt.service");
		
		LOG.info("context created with ID {}", ctx.getId());
		
		IProjectService projectService = ctx.getBean("projectServiceImpl", IProjectService.class);
		
		LOG.info("{}", projectService.findById(1L));
		
		LOG.info("Hello Ankur!");
		
	}

}
