package com.mmpp.motivationapp.backend;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * This class manages multiple tasks and keeps track of a collection of them that are related to a date
 * @author Alexa Calkhoven
 *
 */
public class TaskList implements BackendConstants {

	/**
	 * An ArrayList that stores all the Tasks in this TaskList
	 */
	private ArrayList<Task> myTasks;
	
	/**
	 * The date of this TaskList
	 */
	private LocalDate date;

	/**
	 * Constructor for the class TaskList
	 */
	public TaskList() {
		myTasks = new ArrayList<Task>();
		date = java.time.LocalDate.now();
	}

	/**
	 * Adds a Task to the TaskList
	 * @param newTask the Task to add
	 */
	public void addTask(Task newTask) {
		int insertAt = getInsertIndex(newTask);
		myTasks.add(insertAt, newTask);
	}

	/**
	 * Gets the insertion index of a new Task to be added to the TaskList based on its priority 
	 * @param myTask the Task to be added to the list
	 * @return the index where this Task should be added
	 */
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

	/**
	 * Gets the ArrayList that stores Tasks
	 * @return the ArrayList that stores Tasks
	 */
	public ArrayList<Task> getMyTasks() {
		return myTasks;
	}

	/**
	 * Sets the TaskList ArrayList
	 * @param myTasks the new TaskList ArrayList
	 */
	public void setMyTasks(ArrayList<Task> myTasks) {
		this.myTasks = myTasks;
	}

	/**
	 * Gets the date
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
