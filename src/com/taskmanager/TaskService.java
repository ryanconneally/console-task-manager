package com.taskmanager;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class TaskService {
	
	private final List<Task> tasks = new ArrayList<>();
	private long nextId = 1;
	
	public Task createTask(String title, String description, Priority priortity) {
		Task task = new Task(nextId, title, description, priortity);
		
		tasks.add(task);
		nextId++;
		
		return task;	
	}
	
	public List<Task> getTasks() {
		return List.copyOf(tasks);
	}
	
	public Optional<Task> findTaskById(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Task Id must be greater than zero!");
		}
		
		for(Task task : tasks) {
			if(task.getId() == id) {
				return Optional.of(task);
			}
		}
		
		return Optional.empty();
	}
	
	public boolean completeTask(long id) {
		Optional<Task> result = findTaskById(id);
		
		if(result.isEmpty()) {
			return false;
		}
		
		Task task = result.orElseThrow();
		task.markCompleted();
		
		return true;
	}
	
	public boolean deleteTask(long id) {
		Optional<Task> result = findTaskById(id);
		
		if(result.isEmpty()) {
			return false;
		}
		
		Task task = result.orElseThrow();
		
		return tasks.remove(task);
		
	}
	
	public boolean changeTaskTitle(long id, String title) {
		Optional<Task> result = findTaskById(id);
		
		if(result.isEmpty()) {
			return false;
		}
		
		Task task = result.orElseThrow();
		task.changeTitle(title);
		
		return true;
	}
	
	public boolean changeTaskPriority(long id, Priority priority) {
		Optional<Task> result = findTaskById(id);
		
		if(result.isEmpty()) {
			return false;
		}
		
		Task task = result.orElseThrow();
		task.changePriority(priority);
		
		return true;
	}
	
	public boolean changeTaskDescription(long id, String description) {
		Optional<Task> result = findTaskById(id);
		
		if(result.isEmpty()) {
			return false;
		}
		
		Task task = result.orElseThrow();
		task.changeDescription(description);
		
		return true;
	}

}
