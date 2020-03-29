package com.mmpp.motivationapp.backend;

/**
 * This class makes up the main body of the task objects used throughout the program 
 * @author Alexa Calkhoven
 *
 */
public class Task implements BackendConstants{

	/**
	 * The name for the task to complete, will be displayed to the user on the window
	 */
	String name;
	
	/**
	 * A ranking of how important this task is to complete, scale is included below:
	 * values from 1-10 which will decide where new task is inserted
	 * 10 = most important
	 * 1 = least important
	 */
	int priority;
	
	/**
	 * Used to determine whether or not a task is completed
	 */
	boolean complete;
	
	/**
	 * Constructor for class Task
	 * @param s the name of the task
	 * @param p the priority ranking of the task
	 */
	public Task(String s, int p) {
		name = s;
		priority = p;
		complete = false;
	}
	
	/**
	 * An alternate constructor for class Task, including the boolean complete parameter
	 * @param s the name of the task 
	 * @param p the priority ranking of the task
	 * @param c the status of the tasks' competition 
	 */
	public Task(String s, int p, boolean c) {
		name = s;
		priority = p;
		complete = c;
	}
	
	/**
	 * Sets the complete to true
	 */
	public void makeComplete() {
		setComplete(true);
	}

	/**
	 * Gets the name of the task
	 * @return the name of the task
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of a task
	 * @param name the new name of the task
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the priority ranking of this task
	 * @return the priority ranking of this task
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority ranking of this task
	 * @param priority the new priority rank of this task 
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Gets the completion status of this task
	 * @return the completion status of this task, either true or false
	 */
	public boolean getIsComplete() {
		return complete;
	}

	/**
	 * Sets this task's completion status
	 * @param complete the new completion status of this task
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		String s = "";
		s += getName();
		s += ";" + getPriority();
		s += ";" + getIsComplete();
		return s;
	}

}
