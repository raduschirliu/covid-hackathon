package com.mmpp.motivationapp.backend;

import java.util.ArrayList;

public class Achiever {

	private ArrayList<Achievement> allAchievements;
	private ArrayList<Achievement> myAchievements;
	
	private int lifetimeCompleted;
	
	public ArrayList<Achievement> getAllAchievements() {
		return allAchievements;
	}


	public void importAchievementsFromFile(String filename) {
		//TODO complete this method
	}
	
	
	public void setAllAchievements(ArrayList<Achievement> allAchievements) {
		this.allAchievements = allAchievements;
	}

	public ArrayList<Achievement> getMyAchievements() {
		return myAchievements;
	}

	public void setMyAchievements(ArrayList<Achievement> myAchievements) {
		this.myAchievements = myAchievements;
	}

	public int getLifetimeCompleted() {
		return lifetimeCompleted;
	}

	public void setLifetimeCompleted(int lifetimeCompleted) {
		this.lifetimeCompleted = lifetimeCompleted;
	}

}
