package com.taskmanager;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerApp {

	public static void main(String[] args) {
		List<Task> tasks = new ArrayList<>();
		
		Task studyTask = new Task(
				"Study Java Collections",
				"Understand how to store multiple tasks.",
				Priority.HIGH
		);
		
		Task shoppingTask = new Task(
				"Buy Groceries",
				"Get milk, bread and fruit.",
				Priority.MEDIUM
		);
		
		tasks.add(studyTask);
		tasks.add(shoppingTask);
		
		System.out.println("Number of tasks: " + tasks.size());
		
		for (Task task : tasks) {
			System.out.println("Title: " + task.getTitle());
			System.out.println("Description: " + task.getDescription());
			System.out.println("Priority: " + task.getPriority());
			System.out.println("Completed: " + task.isCompleted());
			System.out.println();
		}
		
	}

}
