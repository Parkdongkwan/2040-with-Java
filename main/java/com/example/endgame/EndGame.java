package com.example.endgame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.gamescene.GameScene;
import com.example.gamescene.LoginPage;

/**
 * This class handles everything happening in endGame scene
 * @author DongKwanPark-modified
 *
 */
public class EndGame {
	static final int WIDTH = 900;
	static final int HEIGHT = 900;
	private static EndGame singleInstance = null;        
	private EndGame(){}                          //This constructor is private so that this class cannot be instantiated.
	EndGameSceneButtons button = new EndGameSceneButtons();

	/**
	 * This method returns object
	 * @return
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
	 * @param score
	 * @param username
	 * @param highestScore
	 * @param highestUserName
	 */
	public void endGameShow(Scene endGameScene, Group root, Stage primaryStage, long highestScore, String highestUserName, int gameType){
		primaryStage.setTitle("GameOver");
		
		endGameSceneUI(root, highestScore,highestUserName);

		button.quitButton(root);

		button.returnMenuButton(root, primaryStage);

		button.retryGameButton(root, primaryStage, gameType, endGameScene);
		
		button.viewButton(root);
	}

	/**
	 * UI of endGameScene
	 * @param root
	 * @param score
	 * @param username
	 * @param highestScore
	 * @param highestUserName
	 */
	public void endGameSceneUI(Group root, long highestScore, String highestUserName) {
		Text text = new Text("GAME OVER");                      //Display "GAME OVER"
		text.relocate(250,250);
		text.setFont(Font.font(80));
		root.getChildren().add(text);

		Text userName = new Text("UserName:  "+ LoginPage.userName);        //Display userName
		userName.setFill(Color.BLACK);
		userName.relocate(0,700);
		userName.setFont(Font.font(50));
		root.getChildren().add(userName);


		Text scoreText = new Text("Score:  "+GameScene.score);             //Display the Score
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
