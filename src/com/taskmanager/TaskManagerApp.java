package com.taskmanager;

import java.util.Scanner;

public class TaskManagerApp {

	public static void main(String[] args) {
		TaskService taskService = new TaskService();
		
		
		try (Scanner scanner = new Scanner(System.in)) {
			ConsoleMenu consoleMenu = new ConsoleMenu(taskService, scanner);
			consoleMenu.start();
		}
		
	}

}
