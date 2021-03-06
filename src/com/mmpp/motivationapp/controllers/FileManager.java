package com.mmpp.motivationapp.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.mmpp.motivationapp.backend.BackendConstants;
import com.mmpp.motivationapp.backend.Task;
import com.mmpp.motivationapp.backend.TaskList;

public class FileManager {

	public FileManager() {
		try {
			File dataDir = new File(BackendConstants.DATA_DIR);
			if (!dataDir.exists() || !dataDir.isDirectory()) {
				dataDir.mkdir();
			}
			
			File yesterdayFile = new File(BackendConstants.YESTERDAY_FILE_NAME);
			if (!yesterdayFile.exists()) {
				yesterdayFile.createNewFile();
			}
			
			File todayFile = new File(BackendConstants.TODAY_FILE_NAME);
			if (!todayFile.exists()) {
				todayFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveList(TaskList list, String filename) {
		try {
			FileWriter writer = new FileWriter(filename, false);
			if (list == null) {
				writer.close();
				return;
			}
			writer.write(list.getDate().toString() + "\n");
			for (int i = 0; i < list.getMyTasks().size(); i++) {
				writer.write(list.getMyTasks().get(i).toString() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TaskList importListFromFile(String fileName) {
		TaskList newList = new TaskList();
		File file = new File(fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String date = br.readLine();
			if (date == null) {
				br.close();
				return null;
			}
			newList.setDate(LocalDate.parse(date));
			String task = br.readLine();
			while (task != null) {
				String[] vars = task.split(";");
				String name = vars[0];
				int pri = Integer.parseInt(vars[1]);
				boolean boo = Boolean.parseBoolean(vars[2]);
				Task newTask = new Task(name, pri, boo);
				newList.addTask(newTask);
				task = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (DateTimeParseException e3) {
			//if there isn't a date at the top of the file, return a null list
			return null;
		}
		return newList;
	}
	

}
