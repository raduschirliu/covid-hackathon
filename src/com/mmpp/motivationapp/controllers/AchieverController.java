package com.mmpp.motivationapp.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.mmpp.motivationapp.backend.Achievement;
import com.mmpp.motivationapp.backend.Achiever;
import com.mmpp.motivationapp.backend.BackendConstants;

public class AchieverController implements BackendConstants{

	private Achiever achiever;

	public AchieverController() {
		achiever = new Achiever();
		achiever.importAchievementsFromFile("AchievementBank.txt", achiever.getAllAchievements());
		
		try {
			File achievementsFile = new File(MY_ACHIEVEMENTS);
			if (!achievementsFile.exists()) {
				achievementsFile.createNewFile();
			} 
			achiever.loadMyAchievements(achievementsFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public static void main(String [] args) {
		AchieverController c = new AchieverController();
		for(Achievement a: c.achiever.getAllAchievements()) {
			System.out.println("\n" + a);
		}
	}

	public void saveAchievementsToFile() {
		if(achiever.getMyAchievements() == null) {
			return;
		}
		try {
			FileWriter writer = new FileWriter(MY_ACHIEVEMENTS, false);
			//TODO somehow save the total completed lifetime achievements
			
			
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
