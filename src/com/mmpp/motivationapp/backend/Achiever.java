package com.mmpp.motivationapp.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Achiever {

	/**
	 * This array list stores all the achievements in the achievement bank
	 */
	private ArrayList<Achievement> allAchievements;
	
	/**
	 * This list stores the achievements the user has earned
	 */
	private ArrayList<Achievement> myAchievements;
	
	/**
	 * Tracks the number of tasks completed over the user's lifetime of using the app
	 */
	private int lifetimeCompleted;

	/**
	 * Constructor for the class Achiever
	 */
	public Achiever() {
		allAchievements = new ArrayList<Achievement>();
		myAchievements = new ArrayList<Achievement>();
	}
	
	/**
	 * Checks to see if user has earned any achievements
	 */
	public void update() {
		int i = 0;
		while(i < allAchievements.size() && allAchievements.get(i).isHasEarned()) {
			i++;
		}
		if(lifetimeCompleted >= allAchievements.get(i).getCost()) {
			earnAchievement(allAchievements.get(i));
		}
	}
	
	/**
	 * Adds an achievement from the AllAchievements list to myAchievements
	 */
	public void earnAchievement(Achievement a) {
		a.setHasEarned(true);
		myAchievements.add(a);
	}
	
	/**
	 * Imports achievements into the allAchievements from a file
	 * @param filename the name of the file
	 */
	public void importAchievementsFromFile(String filename) {
		File file = new File(filename);
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while(in.ready()) {
				String s = in.readLine();
				addAchievementToAll(s);
			}
		}
		catch(FileNotFoundException e) {
			System.err.println("Could not find the file with the specified filename");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.err.println("I/O error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an achievement to the AllAchievements list
	 * @param s
	 */
	public void addAchievementToAll(String s) {
		String [] split = s.split(";");
		Achievement a = new Achievement(split[0], Integer.parseInt(split[1]), split[2]);
		allAchievements.add(a);
	}
	
	public ArrayList<Achievement> getAllAchievements() {
		return allAchievements;
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
