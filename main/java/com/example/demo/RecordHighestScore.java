package com.example.demo;

import java.io.*;
import java.io.BufferedReader;
/**
 * 
 * @author DongKwanPark
 * This class write the user name and score in each HighestUserName.txt and HighestScore.txt if the files are empty.
 * If files are not empty, this class read the file to obtain the highest score and it's user name and they compare the highest score to current user score.
 * If current user score is higher, it will over write the file. If not they will not write the current user name and score.
 */
public class RecordHighestScore {
	private String userName;
	private String highestUserName;
	private long score;
	private long highestScore;
	
	/**
	 * Constructor for class RecordHighestScore
	 * @param userName
	 * @param score
	 */
	RecordHighestScore(String userName, long score){
		this.userName = userName;
		this.score = score;
	}
	
	/**
	 * This method check if the files are empty.
	 * If any of the file is empty, it will just write current user name and score in text files.
	 * If the file is not empty, it reads the score and user name from each of it's text files.
	 * If the score obtained from text file is less than current score, it will over write the user name and score in text files. 
	 * If the score obtained from text file is higher than current score, it wont write anything and the text file will remain as it is.
	 */
	public void ComputeHighestScore() {  //Write the score and user name in the txt files.
		File userNameFile = new File("HighestUserName.txt");     
		File userScoreFile = new File("HighestScore.txt");    
		if(userNameFile.length() ==0 && userScoreFile.length()==0 ) {                //if the file is empty, write name and score for both text files.
			WriteName();
			WriteScore();
		}
		else {   //file is not empty
			ReadScore();          //update highestScore
			ReadName();           //update highestScore user name
			if(getHighestScore() < score) {
				WriteName();              //over write the Name
				WriteScore();            //over write the Score
			}
		}
	}	
	
	
	/**
	 * WriteName method writes user name in HighestUserName.txt. 
	 * If the method cannot find the file or face any exceptions, it will print exception message. 
	 */
	public void WriteName() { 
		try {
		BufferedWriter WN = new BufferedWriter(new FileWriter("HighestUserName.txt"));
		WN.write(userName);
		WN.close();
		}
		catch(Exception e) {System.out.println(e);}	
	}	
	
	/**
	 * WriteScore method writes score in HighestScore.txt. 
	 * If the method cannot find the file or face any exceptions, it will print exception message. 
	 */
	public void WriteScore() {
		try {
		FileWriter WS = new FileWriter("HighestScore.txt");
		String s = String.valueOf(score);
		WS.write(s);
		WS.close();
		}
		catch(Exception e) {System.out.println(e);}
	}
		
	/**
	 * ReadName method reads user name in HighestUserName.txt. 
	 * If the method cannot find the file or face any exceptions, it will print exception message. 
	 */
	public void ReadName() {	
		try {
		BufferedReader RN = new BufferedReader(new FileReader("HighestUserName.txt"));
		highestUserName = RN.readLine();                                         
		RN.close();
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	/**
	 * ReadScore method reads score in HighestUserName.txt. 
	 * If the method cannot find the file or face any exceptions, it will print exception message. 
	 */
	public void ReadScore() {	
		try {
		BufferedReader RS = new BufferedReader(new FileReader("HighestScore.txt"));  
		highestScore = Long.parseLong(RS.readLine());                           
		RS.close();
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
