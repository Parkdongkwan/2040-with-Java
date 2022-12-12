package com.example.demo.endgame;
import com.example.demo.gamescene.GameScore;
import com.example.demo.loginscene.GameUserName;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class handles everything happening in endGame scene
 * @author ParkDongKwan-modified
 *
 */
public class EndGame {
	static final int WIDTH = 900;
	static final int HEIGHT = 900;
	
	private static EndGame singleInstance = null;    
	
	EndGameSceneButtons button = new EndGameSceneButtons();
	
	GameScore gs = GameScore.getInstance();
	GameUserName un = GameUserName.getInstance();
	
	/**
	 * private constructor
	 */
	private EndGame(){}                          
	

	/**
	 * If the singleInstance is null 
	 * @return the object of EndGame.
	 */
	public static EndGame getInstance(){
		if(singleInstance == null)
			singleInstance= new EndGame();
		return singleInstance;
	}   

	/**
	 * This method displays the end game scene
	 * @param endGameScene
	 * @param root
	 * @param primaryStage
	 * @param highestScore
	 * @param highestUserName
	 * @param gameType
	 * @param winOrLose
	 */
	public void endGameShow(Scene endGameScene, Group root, Stage primaryStage, long highestScore, String highestUserName, int gameType, boolean winOrLose){		
		
		primaryStage.setTitle("GameOver");
		
		endGameSceneText(root, highestScore,highestUserName, winOrLose);

		gs.resetScore();  //Reset the score to 0
		
		button.quitButton(root);

		button.returnMenuButton(root, primaryStage);

		button.retryGameButton(root, primaryStage, gameType, endGameScene);
		
		button.viewRankButton(root);
	}

	/**
	 * design the UI of endGameScene
	 * @param root
	 * @param highestScore
	 * @param highestUserName
	 * @param winOrLose
	 */
	public void endGameSceneText(Group root, long highestScore, String highestUserName, boolean winOrLose) {
		if(winOrLose) {
		Text text = new Text("You Win!");                                  //Display "GAME OVER"
		text.relocate(250,250);
		text.setFont(Font.font(80));
		root.getChildren().add(text);
		}
		else {
			Text text = new Text("GAME OVER");                                  //Display "GAME OVER"
			text.relocate(250,250);
			text.setFont(Font.font(80));
			root.getChildren().add(text);
		}

		Text userName = new Text("UserName:  "+ un.getUserName());        //Display userName
		userName.setFill(Color.BLACK);
		userName.relocate(0,700);
		userName.setFont(Font.font(50));
		root.getChildren().add(userName);


		Text scoreText = new Text("Score:  "+ gs.getScore());             //Display the Score
		scoreText.setFill(Color.BLACK);
		scoreText.relocate(0,600);
		scoreText.setFont(Font.font(50));
		root.getChildren().add(scoreText);

		Text highestScoreUserNameText = new Text("Highest Score: "+ highestScore +" / " + highestUserName);             //Display the  Highest Score
		highestScoreUserNameText.setFill(Color.BLACK);
		highestScoreUserNameText.relocate(0,500);
		highestScoreUserNameText.setFont(Font.font(50));
		root.getChildren().add(highestScoreUserNameText);

	}

}
