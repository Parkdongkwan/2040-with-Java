package com.example.demo.gamescene;

import com.example.demo.account.RecordHighestScore;
import com.example.demo.endgame.EndGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class set the timer and display it in gameScene.
 * @author ParkDongKwan
 *
 */
public class Timer {
	
	private final Integer startTime = 200;
	private Integer seconds = startTime;
	
	Label remainingTime;
	
	RecordHighestScore recordScore;
	
	
	/**
	 * This method works as count down timer. It will change the scene to end game scene when times up.
	 * @param primaryStage
	 * @param gameRoot
	 * @param endGameScene
	 * @param endGameRoot
	 * @param time
	 */
	public void countDown(Stage primaryStage, Group gameRoot, Scene endGameScene, Group endGameRoot, Timeline time) {
		//gameType
		int gameType = 3;
		
		time.setCycleCount(Timeline.INDEFINITE);

		if(time!= null) {
			time.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				seconds--;
				remainingTime.setText("You Only Have: "+seconds.toString());

				if(seconds<=0) {       //if timer reach 0
					boolean winOrLose = false;
					time.stop();	   //Stop the timer
					recordScore = new RecordHighestScore();
					recordScore.ComputeHighestScore();  
					//Set the scene to end game scene.
					primaryStage.setScene(endGameScene);
					EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, recordScore.getHighestScore(), recordScore.getHighestUserName(), gameType, winOrLose);

					gameRoot.getChildren().clear();
				}
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
	
	/**
	 * UI design for remaining time
	 * @param gameRoot
	 */
	public void remainingTimeUI(Group gameRoot) {
		remainingTime = new Label();
		remainingTime.setTextFill(Color.RED);
		remainingTime.setFont(Font.font(20));
		remainingTime.relocate(700, 200);
		gameRoot.getChildren().add(remainingTime);
	}

}
