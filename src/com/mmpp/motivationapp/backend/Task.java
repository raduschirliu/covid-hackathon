package com.mmpp.motivationapp.backend;

public class Task implements BackendConstants{

	String name;
	// values from 1-10 which will decide where new task is inserted
	// 10 = most important
	// 1 = least important
	int priority;
	boolean complete;
	
	public Task(String s, int p) {
		name = s;
		priority = p;
		complete = false;
	}
	
	public void makeComplete() {
		setComplete(true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean getIsComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String toString() {
		String s = "";
		s += "Name: " + getName();
		s += " Complete? " + getIsComplete();
		return s;
	}

}
