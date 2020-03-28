package com.mmpp.motivationapp.backend;

import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList implements BackendConstants{

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
		int insertionFactor = MAX_PRIORTIY - myTask.getPriority();
		double fraction = (double) insertionFactor/(MAX_PRIORTIY);
		int index = (int)(fraction*(getMyTasks().size()));	
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
		for(int i = 0; i < myTasks.size(); i++) {
			System.out.println(myTasks.get(i).toString());
		}
	}

	public static void main(String[] args) {
		TaskList test = new TaskList();
		Task myTask1 = new Task("Test Task 1", 10);
		Task myTask2 = new Task("Test Task 2", 10);
		Task myTask3 = new Task("Test Task 3", 0);
		Task myTask4 = new Task("Test Task 4", 5);
		test.addTask(myTask1);
		test.addTask(myTask2);
		test.addTask(myTask3);
		test.addTask(myTask4);
		test.printList();
	}
}
