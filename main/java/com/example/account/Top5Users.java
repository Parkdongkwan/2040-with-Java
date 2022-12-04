package com.example.account;

import java.util.Collections;
import java.util.Comparator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author ParkDongKwan
 *This class stores the list and sort it with the score.
 */
public class Top5Users {
	
    static final int WIDTH = 700;
    static final int HEIGHT = 700; 
	
	/**
	 * It sorts the array list
	 */
	public void sortList() {
		Collections.sort(Account.accounts, Comparator.comparing(Account :: getScore));
		Collections.reverse(Account.accounts);
	}
	

	/**
	 * It creates a scene where user can view the list of top 5 highest score.
	 */
	public void top5ListScene() {
		sortList();
		Group rankRoot = new Group();		
		Stage newStage = new Stage();
		ListSceneUI(rankRoot);
		Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT);
		newStage.setTitle("TOP 5 LIST");
		newStage.setScene(rankScene);
		newStage.show();
}
	
	/**
	 * This method design the scene
	 * @param root
	 */
	public void ListSceneUI(Group root) {
		Text Header = new Text(); 
		Header.setText("TOP 5 USER LIST :");
		Header.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		Header.relocate(70, 0);
		root.getChildren().add(Header);

		
		int height = 200;
		for(int i = 0; i < Account.accounts.size() ; i++) {
			//Only shows top 5 from sorted list
			if(i == 5) {                              
				break;
			}
			Text UserName = new Text();	
			UserName.setText(Account.accounts.get(i).getUserName()+"\n");
			UserName.relocate(100, height);
			UserName.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
			
			//Every time it writes a userName, the next userName will be written below with gap
			height = height +100;
			root.getChildren().add(UserName);
		}
		
		int height2 = 200;
		for(int i = 0; i < Account.accounts.size() ; i++) {
			//Only shows top 5 from sorted list
			if(i == 5) {
				break;
			}
			Text score = new Text();	
			score.setText(Account.accounts.get(i).getScore()+"\n");
			score.relocate(400, height2);
			score.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
			
			//Every time it writes a score, the next score will be written below with gap
			height2 = height2 +100;        
			root.getChildren().add(score);
		}
		
	
	}
	
}
