package com.example.demo.account;
import java.util.ArrayList;

/**
 * 
 * This class stores the object of this class in the arrayList, delete object from arrayList and compare the information of object. 
 * @author ParkDongKwan-modified
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
     * Set the score
     * @param score
     */
	public void setScore(long score) {
		this.score = score;
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
    public static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

    /**
     * Delete the object which has same userName in arayList
     * @param userName
     * @return account if there is same UserName. If not it returns false
     */
    public static boolean deleteSameUserName(String userName) {
    	for(Account account : accounts) {
    		if(account.getUserName().equals(userName)) {
    			accounts.remove(account);
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Make new account with userName and score. And store it in account arrayList and return that account object.
     * @param userName
     * @param score
     * @return account which was made
     */
    public static Account makeNewAccount(String userName,long score){
        Account account = new Account(userName,score);
        accounts.add(account);
        return account;
    }
    
}