package com.mmpp.motivationapp.backend;

import java.util.ArrayList;
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
}
