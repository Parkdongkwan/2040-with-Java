package com.example.account;

import java.io.*;
import java.util.Scanner;

import com.example.gamescene.GameScene;
import com.example.gamescene.LoginPage;
/**
 * 
 * @author DongKwanPark
 * This class write the user name and score in each HighestUserName.txt and HighestScore.txt if the files are empty.
 * If files are not empty, this class read the file to obtain the highest score and it's user name and they compare the highest score to current user score.
 * If current user score is higher, it will over write the file. If not they will not write the current user name and score.
 */
public class RecordHighestScore {
	String highestUserName;
	long highestScore;

	/**
	 * Constructor for class RecordHighestScore
	 */
	public RecordHighestScore(){
	}

	/**
	 * This method check if the files are empty.
	 * If any of the file is empty, it will just write current user name and score in text files.
	 * If the file is not empty, it reads the score and user name from each of it's text files.
	 * If the score obtained from text file is less than current score, it will over write the user name and score in text files. 
	 * If the score obtained from text file is higher than current score, it wont write anything and the text file will remain as it is.
	 */
	public void ComputeHighestScore() {  //Write the score and user name in the text files.

		File record = new File("Records.txt");    
		//Check if the file is empty
		if(record.length() ==0 ) {        
			writeRecords();                        
			findHighestScore();
		}

		else {   //if file is not empty
			Account.accounts.clear();          //Reset the arrayList
			readRecords();                     //Read text file and store it in arrayList

			//If there is no same user name exist in array list
			if(Account.accountHaveBeenExist(LoginPage.getUserName()) == null) {  		
				writeRecords();
				findHighestScore();	
			}

			//If there is same user name exist in array list
			else {

				//Compare score with same name user's score
				if(Account.accountHaveBeenExist(LoginPage.getUserName()).getScore() >= GameScene.score) {  
					findHighestScore();	

				}
				else {
					if(Account.deleteSameUserName(LoginPage.getUserName())) {      
					reWrite();
					findHighestScore();
					}
				}
			}

		}
	}

	/**
	 * WriteName method writes user name in text file.
	 * Also add the records to array list.
	 * If the method cannot find the file or face any exceptions, it will print exception message. 
	 */
	public void writeRecords() { 
		try {
			BufferedWriter WN = new BufferedWriter(new FileWriter("Records.txt", true));	
			Account.makeNewAccount(LoginPage.getUserName(),GameScene.score);

			WN.write(LoginPage.getUserName() +", " + String.valueOf(GameScene.score) + "\n");
			WN.close();
		}
		catch(Exception e) {System.out.println(e);}	
	}	


	/**
	 * ReadName method reads user name in text file
	 * Also, store the value read from text file in array list.
	 * If the method cannot find the file or face any exceptions, it will print exception message. 
	 */
	public void readRecords() {	
		try {
			Scanner sc  = new Scanner(new File("Records.txt")).useDelimiter(", |\n"); //Read data file

			while(sc.hasNext()) {
				String userName = sc.next();
				String score = sc.next();

				Account.makeNewAccount(userName,Long.parseLong(score));		
			}
			sc.close();

		}
		catch(Exception e) {System.out.println(e);}
	}

	/**
	 * By exploring array list, it compares all the elements to get highest score and user name.
	 */
	public void findHighestScore() {
		long maxValue = 0;
		int index = 0;

		for(int i = 0; i <Account.accounts.size(); i++) {
			if(Account.accounts.get(i).getScore() > maxValue) {
				maxValue = Account.accounts.get(i).getScore();
				index = i;
			}
		}
		highestUserName = Account.accounts.get(index).getUserName();
		highestScore = Account.accounts.get(index).getScore();
	}



	/**
	 * Remove the original text file to reWrite the updated (deletion) array list.
	 * If the method cannot find the file or face any exceptions, it will print exception message.
	 */
	public void reWrite() {	
		Account.makeNewAccount(LoginPage.getUserName(),GameScene.score);

		File oldFile = new File("Records.txt");
		oldFile.delete();

		try {	
			File newFile = new File("Records.txt");

			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
			for(int i = 0; i < Account.accounts.size(); i++) {
				writer.write(Account.accounts.get(i).getUserName() + ", " + Account.accounts.get(i).getScore()+"\n");
			}
			writer.close();
		}
		catch(Exception e) {System.out.println(e);}

	}



	/**
	 * @return highestUserName
	 */
	public String getHighestUserName() {
		return highestUserName;
	}

	/**
	 * @return highestScore
	 */
	public long getHighestScore() {
		return highestScore;
	}
}
