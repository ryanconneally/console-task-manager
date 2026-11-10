package com.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;	

public class TaskManagerApp {

	public static void main(String[] args) {
		TaskService taskService = new TaskService();
		
		taskService.createTask("Study Java Collections", "Undestand how to store multiple tasks", Priority.HIGH);
		taskService.createTask("Buy Groceries", "Bread, Milk and fruit", Priority.MEDIUM);
		
		System.out.println("Count before deletion: " + taskService.getTasks().size());
		
		boolean deleted = taskService.deleteTask(2);
		
		System.out.println("First Deletion: " + deleted);
		System.out.println("Count After first deleion: " + taskService.getTasks().size());
		boolean deletedAgain = taskService.deleteTask(2);
		System.out.println("Second deletion: " + deletedAgain);
		System.out.println("Count after second deletion: " + taskService.getTasks().size());
		
		Task newTask = taskService.createTask("Java programming", "Learning Java programming", Priority.LOW);
		System.out.println("Task ID: " + newTask.getId());
		
		for (Task task : taskService.getTasks()) {
			System.out.println("ID: " + task.getId());
			System.out.println("Title: " + task.getTitle());
			System.out.println("Description: " + task.getDescription());
			System.out.println("Priority: " + task.getPriority());
			System.out.println("Completed: " + task.isCompleted());
			System.out.println();
		}
		
		long searchId = 1;
		
		Optional<Task> result = taskService.findTaskById(searchId);
		
		if (result.isPresent()) {
			Task foundTask = result.orElseThrow();
			System.out.println("Found Task: " + foundTask.getTitle());
			
		} else {
			System.out.println("No task found with id: " + searchId);
		}
		
	}

}
