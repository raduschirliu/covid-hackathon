package com.mmpp.motivationapp.controllers;

import com.mmpp.motivationapp.backend.Motivator;

public class MotivationController {
	private Motivator motivator;
	
	public MotivationController() {
		motivator = new Motivator();
		motivator.importMessagesFromFile("MessageBank.txt");
	}
	
	public String getRandomMessage() {
		return motivator.getRandomMessage().getContent();
	}
}
