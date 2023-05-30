package com.eijilucas.taskmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eijilucas.taskmanagement.entities.Task;
import com.eijilucas.taskmanagement.repositories.TaskRepository;
import com.eijilucas.taskmanagement.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> findAll() {
		return taskRepository.findAll();
	}
	
	public Task findById(Long id) {
		Optional<Task> obj = taskRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Task insert(Task obj) {
		return taskRepository.save(obj);
	}
	
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
	
	public Task update(Long id, Task obj) {
		try {
			Task entity = taskRepository.getReferenceById(id);
			updateData(entity, obj);
			return taskRepository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Task entity, Task obj) {
		entity.setTitle(obj.getTitle());
		entity.setDescription(obj.getDescription());
		entity.setCompleted(obj.isCompleted());
	}
}
