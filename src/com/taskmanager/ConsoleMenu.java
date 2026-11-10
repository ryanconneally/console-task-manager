package com.taskmanager;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class ConsoleMenu {
	
	private final TaskService taskService;
	private final Scanner scanner;
	
	public ConsoleMenu(TaskService taskservice, Scanner scanner) {
		this.taskService = taskservice;
		this.scanner = scanner;
	}
	
	public void start() {
		boolean running = true;
		
		while(running) {
			displayMenu();
			
			String choice = scanner.nextLine().strip();
			
			switch(choice) {
			case "1" -> addTask();
			case "2" -> viewTasks();
			case "3" -> findTask();
			case "4" -> System.out.println("Complete task selected.");
			case "5" -> System.out.println("Delete task selected.");
			case "0" -> {
				running = false;
				System.out.println("Goodbye!");
			}
			default -> System.out.println("Invalid option. Please choose a listed number");
			}
			
			System.out.println();
		}
	}
	
	private void displayMenu() {
		System.out.println("===Task Manager===");
		System.out.println("1. Add Task");
		System.out.println("2. View tasks");
		System.out.println("3. Find tasks");
		System.out.println("4. Complete task");
		System.out.println("5. Delete task");
		System.out.println("0. Exit");
		System.out.println("Choose an option: ");
	}
		
	private void viewTasks() {
		List<Task> tasks = taskService.getTasks();
		
		if(tasks.isEmpty()) {
			System.out.println("No tasks available");
			return;
		}
		
		System.out.println("===Your Tasks===");
		
		for (Task task : tasks) {
			displayTasks(task);
			System.out.println();
		}
	}
	
	private void addTask() {
		System.out.println("Title: ");
		String title = scanner.nextLine();
		
		System.out.println("Description (optional)");
		String description = scanner.nextLine();
		
		Priority priority = readPriority();
		
		try {
			Task task = taskService.createTask(title, description, priority);
			System.out.println("Task created with id: " + task.getId());
		} catch (IllegalArgumentException exception) {
			System.out.println("Could not create task: " + exception.getMessage());
		}
	}
	
	private Priority readPriority() {
		while(true) {
			System.out.println("Choose a priority:");
			System.out.println("1. Low");
			System.out.println("2. Medium");
			System.out.println("3. High");
			System.out.println("Priority: ");
			
			String choice = scanner.nextLine().strip();
			
			switch(choice) {
			case "1" -> {
				return Priority.LOW;
			}
			case "2" -> {
				return Priority.MEDIUM;
			}
			case "3" -> {
				return Priority.HIGH;
			}
			default -> System.out.println("Invalid Priority. PLease choose 1,2 or 3.");
			}
		}
	}
	
	private long readTaskId() {
		while(true) {
			System.out.println("Enter Task Id: ");
			String input = scanner.nextLine().strip();
			
			try {
				long id = Long.parseLong(input);
				if(id > 0) {
					return id;
				}
				
				System.out.println("Task id must be greater than zero.");
			} catch (NumberFormatException exception) {
				System.out.println("Please enter a valid whole-number id");
			}
		}
	}
	
	private void findTask() {
		long id = readTaskId();
		
		Optional<Task> result = taskService.findTaskById(id);
		
		if(result.isEmpty()) {
			System.out.println("No task found with id: " + id);
			return;
		}
		
		Task task = result.orElseThrow();
		
		displayTasks(task);
	}
	
	private void displayTasks(Task task) {
		System.out.println("ID: " + task.getId());
		System.out.println("Title: " + task.getTitle());
		System.out.println("Description: " + task.getDescription());
		System.out.println("Priority: " + task.getPriority());
		System.out.println("Completed: " + task.isCompleted());
	}

}
