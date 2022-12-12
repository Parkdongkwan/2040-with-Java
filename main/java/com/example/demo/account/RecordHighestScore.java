package com.example.demo.account;

import java.io.*;
import java.util.Scanner;

import com.example.demo.gamescene.GameScore;
import com.example.demo.loginscene.GameUserName;
/**
 * 
 * This class write the user name and score in each HighestUserName.txt and HighestScore.txt if the files are empty.
 * If files are not empty, this class read the file to obtain the highest score and it's user name and they compare the highest score to current user score.
 * If current user score is higher, it will over write the file. If not they will not write the current user name and score.
 * @author ParkDongKwan
 */
public class RecordHighestScore {
	private String highestUserName;
	private long highestScore;
	GameScore gs = GameScore.getInstance();
	GameUserName  un = GameUserName.getInstance();

	/**
	 * Constructor for class RecordHighestScore
	 */
	public RecordHighestScore(){
	}

	/**
	 * This method check if the files are empty.
	 * If any of the file is empty, it will just write current user name and score in text files.
	 * If the file is not empty, it reads the score and user name from each of it's text files and store it in arraList.
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
			if(Account.accountHaveBeenExist(un.getUserName()) == null) {  		
				writeRecords();
				findHighestScore();	
			}

			//If there is same user name exist in array list
			else {

				//Compare score with same name user's score
				if(Account.accountHaveBeenExist(un.getUserName()).getScore() >= gs.getScore()) {  
					findHighestScore();	

				}
				else {
					if(Account.deleteSameUserName(un.getUserName())) {      
					reWrite();
					findHighestScore();
					}
				}
			}

		}
	}

	/**
	 * writeRecords method writes userName and score in text file.
	 * Also add the records to array list.
	 * If the method cannot find the file or face any exceptions, it will print exception message. 
	 */
	public void writeRecords() { 
		try {
			BufferedWriter WN = new BufferedWriter(new FileWriter("Records.txt", true));	
			Account.makeNewAccount(un.getUserName(),gs.getScore());

			WN.write(un.getUserName() +", " + String.valueOf(gs.getScore()) + "\n");
			WN.close();
		}
		catch(Exception e) {System.out.println(e);}	
	}	


	/**
	 * ReadName method reads userName and score in text file
	 * Also, store the value read from text file in arrayList.
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
	 * findHighestScore method find for the highest score and userName by looping the arrayList
	 * And store the highest score and userName in the variable. 
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
	 * Delete the original Records file. And rewrite the arrayList in the new textFile which has same name "Records".
	 * If the method cannot find the file or face any exceptions, it will print exception message.
	 */
	public void reWrite() {	
		Account.makeNewAccount(un.getUserName(),gs.getScore());

		File oldFile = new File("Records.txt");
		//Delete the file
		oldFile.delete();

		try {	
			File newFile = new File("Records.txt");
			//Overwriting the information in arrayList in new textFile.
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
