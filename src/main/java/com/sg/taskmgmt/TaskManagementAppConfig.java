package com.sg.taskmgmt;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.sg.taskmgmt.persistence.repository.IProjectRepository;
import com.sg.taskmgmt.persistence.repository.impl.ProjectRepositoryImpl;

@Configuration
public class TaskManagementAppConfig {

	
	/*
	 * @Bean
	 * 
	 * @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) public
	 * IProjectRepository singletonBean() { return new ProjectRepositoryImpl(); }
	 */
		/*
		 * @Bean
		 * 
		 * @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE) public IProj
		 *ectRepository prototypeBean() { return new ProjectRepositoryImpl(); }
	 	*/
}
