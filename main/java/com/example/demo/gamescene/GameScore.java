package com.example.demo.gamescene;
/**
 * 
 * This class is to store the gameScore and access the score from other classes.
 * @author ParkDongKwan
 */
public class GameScore {
	
	private static GameScore instance = new GameScore();
	private long score;

	/**
	 * Constructor
	 */
	private GameScore() {};
	
	/**
	 * @return the object of this class
	 */
	public static GameScore getInstance() {
		return instance;
	}
	/**
	 * @return the score
	 */
	public long getScore() {
		return score;
	}

	/**
	 * Set the score
	 * @param score
	 */
	public void setScore(long score) {
		this.score += score;
	}
	
	/**
	 * set the score to 0
	 */
	public void resetScore() {
		this.score = 0;
	}
}
