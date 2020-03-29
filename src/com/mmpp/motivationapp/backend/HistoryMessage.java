package com.mmpp.motivationapp.backend;
/**
 * This class provides the user with messages that are customized based on their 
 * history of completed tasks
 * @author Tyler Yip 
 *
 */
public class HistoryMessage{

	/**
	 * This is the first part of the motivational message
	 */
	private String before;
	
	/**
	 * The last part of the motivational message
	 */
	private String after;

	
	public HistoryMessage(String before, String after) {
		this.before = before;
		this.after = after;
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
	
	public String generateMessage(Task t) {
		String s = this.before;
		s += t.getName();
		s += this.after;
		return s;
	}

}
