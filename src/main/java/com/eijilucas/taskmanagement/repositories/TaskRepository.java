package com.eijilucas.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eijilucas.taskmanagement.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
