package com.mmpp.motivationapp.controllers;

import com.mmpp.motivationapp.backend.Achiever;

public class AchieverController {

	private Achiever achiever;

	public AchieverController() {
		achiever = new Achiever();
		achiever.importAchievementsFromFile("AchievementBank.txt");
	}
	
	public Achiever getAchiever() {
		return achiever;
	}

	public void setAchiever(Achiever achiever) {
		this.achiever = achiever;
	}
	
	public void update() {
		achiever.update();
	}
	
}
