package com.mmpp.motivationapp.controllers;

import com.mmpp.motivationapp.backend.TaskList;

//make a save function
public class TaskListManager {

	//All TaskLists that front end should have access to:
	TaskList yesterdaysList;
	TaskList todaysList;
	
	public TaskListManager() {
		yesterdaysList = null;
		todaysList = new TaskList();
	}
	
	public void saveListsToFile() {
		
	}
	
	public boolean isNewDay() {
		//if todaysList contains the current date, return false
		if(todaysList.getDate().equals(java.time.LocalDate.now())) {
			return false;
		}
		return true;
	}
}
