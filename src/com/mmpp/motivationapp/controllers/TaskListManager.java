package com.mmpp.motivationapp.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mmpp.motivationapp.backend.BackendConstants;
import com.mmpp.motivationapp.backend.Task;
import com.mmpp.motivationapp.backend.TaskList;

//make sure you have the files of the correct names in the correct folder before running
//TODAY_FILE_NAME and YESTERDAY_FILE_NAME
public class TaskListManager implements BackendConstants {

	// All TaskLists that front end should have access to:
	FileManager fileMan;
	TaskList yesterdaysList;
	TaskList todaysList;

	// decide where to get the lists from upon start up
	public TaskListManager() {
		fileMan = new FileManager();
		if (isNewDay()) {
			setupNewDay();
		} else {
			continueDay();
		}
	}

	private void continueDay() {
		yesterdaysList = fileMan.importListFromFile(YESTERDAY_FILE_NAME);
		todaysList = fileMan.importListFromFile(TODAY_FILE_NAME);
	}

	private void setupNewDay() {
		yesterdaysList = fileMan.importListFromFile(TODAY_FILE_NAME);
		todaysList = new TaskList();
	}
	
	public ArrayList<Task> getTodaysTasks() {
		return todaysList.getMyTasks();
	}

	public void addTaskToToday(Task myTask) {
		todaysList.addTask(myTask);
	}

	public void saveListsToFile() {
		fileMan.saveList(yesterdaysList, YESTERDAY_FILE_NAME);
		fileMan.saveList(todaysList, TODAY_FILE_NAME);
	}

	public boolean isNewDay() {
		// if todaysList contains the current date, return false
		LocalDate now = java.time.LocalDate.now();
		LocalDate recorded = getDate();
		if (recorded == null) {
			return true;
		} else if (recorded.equals(now)) {
			return false;
		}
		return true;
	}

	private LocalDate getDate() {
		File file = new File(TODAY_FILE_NAME);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String myDate = null;
		try {
			myDate = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (myDate == null) {
			return null;
		}
		return LocalDate.parse(myDate);
	}
}
