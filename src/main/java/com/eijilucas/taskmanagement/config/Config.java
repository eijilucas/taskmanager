package com.eijilucas.taskmanagement.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.eijilucas.taskmanagement.entities.Task;
import com.eijilucas.taskmanagement.repositories.TaskRepository;

@Configuration
public class Config implements CommandLineRunner{

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Task task1 = new Task(null, "HTML PAGE", "Criar uma página HTML", true);
		Task task2 = new Task(null, "Rest API", "Criar uma API Rest", false);
		
		taskRepository.saveAll(Arrays.asList(task1, task2));
		
	}

}
