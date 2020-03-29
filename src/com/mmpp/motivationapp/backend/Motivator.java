package com.mmpp.motivationapp.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles the user Motivator that is displayed to the right of the task window
 * @author Tyler Yip
 *
 */
public class Motivator {

	/**
	 * This array list stores all the messages generated from the text file
	 */
	private ArrayList<Message> messageBox;
	
	/**
	 * This array list stores all the history messages
	 */
	private ArrayList<HistoryMessage> historyMessageBox;
	
	/**
	 * This array list stores all of the tasks from yesterday, both completed or not
	 */
	private TaskList yesterdaysTasks;

	/**
	 * The number of completed tasks in the yesterdaysTasks TaskList
	 */
	private int completedYesterday;

	/**
	 * Constructor for class Motivator
	 */
	public Motivator() {
		messageBox = new ArrayList<Message>();
		historyMessageBox = new ArrayList<HistoryMessage>();
		completedYesterday = 0;
		yesterdaysTasks = null;
	}
	
	/**
	 * The main motivator method, calls the sub methods to generate motivational message
	 * @return
	 */
	public String motivate() {
		if(completedYesterday == 0) {
			return getRandomMessage();
		}
		else {
			Random r = new Random();
			if(r.nextInt(2) == 1) {
				return getRandomHistoryMessage();
			}
			else {
				return getRandomMessage();
			}
		}
	}
	
	
	/**
	 * Creates a new message and adds it to the message box
	 * @param s the contents of the new message
	 */
	public void addMessage(String s) {
		Message newMessage = new Message(s);
		messageBox.add(newMessage);
	}
	
	/**
	 * Resets the yesterdaysTasks TaskList given the new list of tasks that were completed yesterday
	 */
	public void resetYesterday(TaskList newList) {
		setCompletedYesterday(0);
		yesterdaysTasks = new TaskList();
		for(Task t: newList.getMyTasks()) {
			if(t.getIsComplete()) {
				yesterdaysTasks.addTask(t);
			}
		}
		setCompletedYesterday(yesterdaysTasks.getMyTasks().size());
	}

	/**
	 * Generates a string that contains a random history message based on the history message template bank and yesterday's completed tasks
	 * @return the generated history message
	 */
	public String getRandomHistoryMessage() {
		if(completedYesterday == 0) {
			System.err.println("No tasks were completed yesterday");
			return null;
		}
		//We need to get a random completed task and history message template
		Random r = new Random();
		int max = historyMessageBox.size();
		int index = r.nextInt(max);
		
		max = yesterdaysTasks.getMyTasks().size();
		int taskIndex = r.nextInt(max);
		
		//We take the first part of the template, add the task name, then add the last part of the template
		String result = historyMessageBox.get(index).getBefore();
		result += yesterdaysTasks.getMyTasks().get(taskIndex).getName();
		result += historyMessageBox.get(index).getAfter();
		
		return result;
	}
	
	/**
	 * Picks a random message from the message box
	 * @return a randomly selected message as a String
	 */
	public String getRandomMessage() {
		if(messageBox.size() == 0) {
			System.out.println("No messages to display");
			return null;
		}
		//Get a random index within the total messages
		Random r = new Random();
		int max = messageBox.size();
		int index = r.nextInt(max);

		return messageBox.get(index).getContent();
	}
	
	/**
	 * Adds the templates for history messages
	 * @param filename the file containing the templates
	 */
	public void importHistoryMessagesFromFile(String filename) {
		File file = new File(filename);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while(in.ready()) {
				String s = in.readLine();
				addHistoryMessage(s);
			}
			in.close();
		} catch(FileNotFoundException e) {
			System.err.println("Could not find the file with the specified filename");
			e.printStackTrace();
		} catch(IOException e) {
			System.err.println("I/O error");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Add a history message template to the history message box
	 * @param s the string containing both parts of the template
	 */
	public void addHistoryMessage(String s) {
		String before = "";
		String after = "";
		int i;
		for(i = 0; s.charAt(i) != '*'; i++){
			before += s.charAt(i);
		}
		i++;
		for(; i<s.length(); i++) {
			after += s.charAt(i);
		}
		HistoryMessage m = new HistoryMessage(before, after);
		historyMessageBox.add(m);
	}
	
	/**
	 * Loads preset messages from a text file to the message box
	 * @param filename the name of the text file
	 */
	public void importMessagesFromFile(String filename) {
		File file = new File(filename);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while(in.ready()) {
				String s = in.readLine();
				addMessage(s);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find the file with the specified filename");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("I/O error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the message box ArrayList
	 * @return the message box in ArrayList
	 */
	public ArrayList<Message> getMessageBox() {
		return messageBox;
	}

	/**
	 * Sets the message box ArrayList
	 * @param messageBox the new message box ArrayList
	 */
	public void setMessageBox(ArrayList<Message> messageBox) {
		this.messageBox = messageBox;
	}
	
	/**
	 * Gets YesterdaysTasks
	 * @return YesterdaysTasks
	 */
	public TaskList getYesterdaysTasks() {
		return yesterdaysTasks;
	}

	/**
	 * Sets yesterdaysTasks 
	 * @param yesterdaysTasks the new TaskList
	 */
	public void setYesterdaysTasks(TaskList yesterdaysTasks) {
		this.yesterdaysTasks = yesterdaysTasks;
	}
	
	/**
	 * This was used to test the random message display for the motivator 
	 * @param args command line arguments, not used
	 */
	public static void main(String [] args) {
		Motivator m = new Motivator();
		m.importMessagesFromFile("MessageBank.txt");
		System.out.println(m.getRandomMessage());
		m.importHistoryMessagesFromFile("HistoryMessageTemplates.txt");
		Task t1 = new Task("learn Jordan's birthday", 10, true);
		Task t2 = new Task("kill John Smith", 7, true);
		Task t3 = new Task("get cake", 9, true);
		TaskList list = new TaskList();
		list.addTask(t1);
		list.addTask(t2);
		list.addTask(t3);
		m.resetYesterday(list);
		System.out.println(m.getRandomHistoryMessage());
		
	}

	public ArrayList<HistoryMessage> getHistoryMessageBox() {
		return historyMessageBox;
	}

	public void setHistoryMessageBox(ArrayList<HistoryMessage> historyMessageBox) {
		this.historyMessageBox = historyMessageBox;
	}
	
	public int getCompletedYesterday() {
		return completedYesterday;
	}

	public void setCompletedYesterday(int completedYesterday) {
		this.completedYesterday = completedYesterday;
	}
	
}
