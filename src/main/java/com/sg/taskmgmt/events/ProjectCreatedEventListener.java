package com.sg.taskmgmt.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProjectCreatedEventListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProjectCreatedEventListener.class);
	
	@EventListener
	public void handleProjectCreatedEvent(ProjectCreatedEvent event) {
		LOG.info("New project created with ID {}", event.getProjectId());
	}
}
