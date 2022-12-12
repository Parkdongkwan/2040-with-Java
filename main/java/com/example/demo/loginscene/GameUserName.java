package com.example.demo.loginscene;
/**
 * 
 * This class is to store the userName
 * @author ParkDongKwan
 */
public class GameUserName {
	
	private static GameUserName instance = new GameUserName();
	private String userName;
	
	/**
	 * Constructor
	 */
	private GameUserName(){}
	
	/**
	 * @return the object of the class
	 */
	public static GameUserName getInstance() {
		return instance;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the userName
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
