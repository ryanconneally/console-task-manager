package com.taskmanager;

public class Task {
	
	private String title;
	private String description;
	private boolean completed;
	private Priority priority;
	
	public Task(String title, String description, Priority priority) {
		if(title == null || title.isBlank()) {
			throw new IllegalArgumentException("Task title must not be blank.");
		}
		
		if (priority == null) {
			throw new IllegalArgumentException("Task priority must not be null.");
		}
		
		
		this.title = title.strip();
		this.description = description == null ? "" : description.strip();
		this.priority = priority;
		this.completed = false;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void markCompleted() {
		completed = true;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public void changePriority(Priority priority) {
		if (priority == null) {
			throw new IllegalArgumentException("Task priority must not be null.");
		}
		
		this.priority = priority;
	}

}
