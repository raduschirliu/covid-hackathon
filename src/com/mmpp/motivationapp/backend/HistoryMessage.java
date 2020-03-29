package com.mmpp.motivationapp.backend;
/**
 * This class provides the user with messages that are customized based on their 
 * history of completed tasks
 * @author Tyler Yip 
 *
 */
public class HistoryMessage{

	private String before;
	
	/**
	 * The last part of the motivational saying
	 */
	private String after;
	
	/**
	 * The task that was completed
	 */
	private Task task;
	
	public HistoryMessage(String before, String after, Task t) {
		this.before = before;
		this.after = after;
		this.setTask(t);
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += before;
		s += task.getName();
		s += after;
		return s;
	}

}
