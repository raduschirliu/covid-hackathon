package com.mmpp.motivationapp.backend;
/**
 * This class is used by the motivator to deliver messages to the user
 * @author Tyler Yip
 *
 */
public class Message {

	private String content;
	
	public Message(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return getContent();
	}
	
}
