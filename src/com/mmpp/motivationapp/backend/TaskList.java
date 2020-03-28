package com.mmpp.motivationapp.backend;

import java.util.ArrayList;
import com.mmpp.motivationapp.controllers.TaskListManager;
import java.time.LocalDate;

public class TaskList implements BackendConstants {

	private ArrayList<Task> myTasks;
	private LocalDate date;

	public TaskList() {
		myTasks = new ArrayList<Task>();
		date = java.time.LocalDate.now();
	}

	public void addTask(Task newTask) {
		int insertAt = getInsertIndex(newTask);
		myTasks.add(insertAt, newTask);
	}

	private int getInsertIndex(Task myTask) {
		if (myTasks.size() == 0) {
			return 0;
		}
		int index = 0;
		while (myTask.getPriority() < myTasks.get(index).getPriority()) {
			index++;
			if (index >= myTasks.size()) {
				break;
			}
		}
		return index;
	}

	public ArrayList<Task> getMyTasks() {
		return myTasks;
	}

	public void setMyTasks(ArrayList<Task> myTasks) {
		this.myTasks = myTasks;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	private void printList() {
		for (int i = 0; i < myTasks.size(); i++) {
			System.out.println(myTasks.get(i).toString());
		}
	}

	public static void main(String[] args) {
		TaskListManager taskMan = new TaskListManager();
		Task myTask1 = new Task("Test Task 1", 10);
		Task myTask2 = new Task("Test Task 2", 10);
		Task myTask3 = new Task("Test Task 3", 0);
		Task myTask4 = new Task("Test Task 4", 5);
		Task myTask5 = new Task("Test Task 5", 3);
		Task myTask6 = new Task("Test Task 6", 2);
		Task myTask7 = new Task("Test Task 7", 7);
		taskMan.addTaskToToday(myTask1);
		taskMan.addTaskToToday(myTask2);
		taskMan.addTaskToToday(myTask3);
		taskMan.addTaskToToday(myTask4);
		taskMan.addTaskToToday(myTask5);
		taskMan.addTaskToToday(myTask6);
		taskMan.addTaskToToday(myTask7);
		taskMan.saveListsToFile();
	}
}
