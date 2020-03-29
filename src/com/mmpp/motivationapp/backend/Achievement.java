package com.mmpp.motivationapp.backend;

/**
 * This class is used to make achievements that the user can get for completing
 * x number of tasks
 * 
 * @author Tyler Yip
 *
 */
public class Achievement {

	/**
	 * The name of the achievement
	 */
	private String name;

	/**
	 * A description of the achievement
	 */
	private String description;

	/**
	 * The number of tasks that the user must complete to earn this achievement
	 */
	private int cost;
	
	/**
	 * The status of whether or not a user has earned this achievement
	 */
	private boolean hasEarned;

	/**
	 * The constructor for class achievement
	 * 
	 * @param name the name for the achievement
	 * @param cost the number of tasks to earn this achievement
	 */
	public Achievement(String name, int cost, String description) {
		this.name = name;
		this.cost = cost;
		this.setDescription(description);
		this.setHasEarned(false);
	}

	/**
	 * Gets the name of the achievement
	 * 
	 * @return the name of the achievement
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the achievement
	 * 
	 * @param name the new name of the achievement
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the cost of the achievement
	 * 
	 * @return the cost of the achievement
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the cost of the achievement
	 * 
	 * @param cost the new cost of the achievement
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Gets the description of the achievement
	 * 
	 * @return the description of the achievement
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the achievement
	 * 
	 * @param description the new description of the achievement
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHasEarned() {
		return hasEarned;
	}

	public void setHasEarned(boolean hasEarned) {
		this.hasEarned = hasEarned;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += name;
		s += "\n" + cost;
		s += "\n" + description;
		return s;
	}

}
