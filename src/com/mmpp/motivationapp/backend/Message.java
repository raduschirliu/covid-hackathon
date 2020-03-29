package com.mmpp.motivationapp.backend;
/**
 * This class is used by the motivator to deliver messages to the user
 * @author Tyler Yip
 *
 */
public class Message {

	/**
	 * Holds the message's contents
	 */
	private String content;
	
	/**
	 * Constructor for class message
	 * @param content the message's string contents
	 */
	public Message(String content) {
		this.content = content;
	}

	/**
	 * Gets the contents of the message
	 * @return the contents of the message
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the contents of the message
	 * @param content the new contents of the message
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return getContent();
	}
	
}
