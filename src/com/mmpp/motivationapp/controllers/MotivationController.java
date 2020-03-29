package com.mmpp.motivationapp.controllers;

import com.mmpp.motivationapp.backend.Motivator;

public class MotivationController {
	private Motivator motivator;
	
	public Motivator getMotivator() {
		return motivator;
	}

	public void setMotivator(Motivator motivator) {
		this.motivator = motivator;
	}

	public MotivationController() {
		motivator = new Motivator();
		motivator.importMessagesFromFile("MessageBank.txt");
	}
	
	public String getRandomMessage() {
		return motivator.motivate();
	}
}
