package com.example.account;

import java.util.ArrayList;
/**
 * 
 * @author ParkDongKwan-modified
 * This class stores the object in the arrayList, delete object from arrayList. 
 */

public class Account {
    private long score = 0;
    private String userName ;
    public static ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Constructor
     * @param userName
     * @param score
     */
    public Account(String userName, long score){   
        this.userName=userName;
        this.setScore(score);
    }

    /**
     * @return score
     */
    public long getScore() {
        return score;
    }

    /**
     * 
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     * @return the object if there is same userName. If not it returns null
     */
    static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

    /**
     * Set the score
     * @param score
     */
	public void setScore(long score) {
		this.score = score;
	}

    /**
     * Delete the object which has same userName in arayList
     * @param userName
     * @return account if there is same UserName. If not it returns null
     */
    static Account deleteSameUserName(String userName) {
    	for(Account account : accounts) {
    		if(account.getUserName().equals(userName)) {
    			return account;
    		}
    	}
		return null;
    }
    
}
//Hello