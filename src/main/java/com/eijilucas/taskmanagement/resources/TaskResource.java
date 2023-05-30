package com.eijilucas.taskmanagement.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eijilucas.taskmanagement.entities.Task;
import com.eijilucas.taskmanagement.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<List<Task>> findAll() {
		List<Task> tasks = taskService.findAll();
		return ResponseEntity.ok().body(tasks);
	}
	
	@GetMapping(value = ("/{id}"))
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		Task task = taskService.findById(id);
		return ResponseEntity.ok().body(task);
	}
	
	@PostMapping
	public ResponseEntity<Task> insert(@RequestBody Task obj) {
		obj = taskService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value=("/{id}"))
	public ResponseEntity<Task> deleteById(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value=("/{id}"))
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task obj) {
		obj = taskService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
