package com.example.endgame;

import java.util.Optional;

import com.example.account.Top5Users;
import com.example.demo.MenuSceneController;
import com.example.gamescene.GameScene;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author ParkDongKwan
 * Buttons in endGameScene
 */
public class EndGameSceneButtons {
	
	static final int WIDTH = 900;
	static final int HEIGHT = 900;
	
	/**
	 * This button alerts users by showing the message saying if they want to quit. 
	 * If user press yes then it will exit the system.
	 * @param root
	 */
	public void quitButton(Group root) {
	Button quitButton = new Button("QUIT");                //QUIT button
	quitButton.setPrefSize(150,30);
	quitButton.setTextFill(Color.PINK);
	root.getChildren().add(quitButton);
	quitButton.relocate(100,800);
	quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Quit Dialog");
			alert.setHeaderText("Quit from this page");
			alert.setContentText("Are you sure?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				root.getChildren().clear();
				System.exit(0);                             //(Fixed) Originally, without this code, the system stuck in the empty scene. So this code shut down the system when the button is clicked.
			}
		}
	});

	}
	
	/**
	 * This button allow users to return back to menu
	 * @param root
	 * @param primaryStage
	 */
	public void returnMenuButton(Group root, Stage primaryStage) {
		Button returnMenuButton = new Button("Return Menu");         
		returnMenuButton.setPrefSize(150, 30);
		root.getChildren().add(returnMenuButton);
		returnMenuButton.relocate(300,800);
		returnMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					Parent menuRoot = FXMLLoader.load(getClass().getResource("/pane/Menu.fxml"));  
					Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT);
					primaryStage.setScene(menuScene);
					primaryStage.setTitle("Menu");
					primaryStage.show();
				}
				catch(Exception e) {System.out.println(e);}

			}
		});
	}

	/**
	 * This button allows users to rePlay the game with the same game mode they chose previously.
	 * @param root
	 * @param primaryStage
	 * @param gameType
	 * @param endGameScene
	 */
	public void retryGameButton(Group root, Stage primaryStage, int gameType, Scene endGameScene) {
		Button retryGameButton = new Button("Retry Game");         //Return back to menu button
		retryGameButton.setPrefSize(150, 30);
		root.getChildren().add(retryGameButton);
		retryGameButton.relocate(500,800);
		retryGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Group gameRoot = new Group();
					Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, MenuSceneController.myColor);

					primaryStage.setScene(gameScene);
					GameScene game = new GameScene(primaryStage, gameScene, gameRoot, endGameScene, root);

					if(gameType == 1)
						game.game();

					else if(gameType == 2)
						game.game2();

					else
						game.game3();

					
					//clear the previous end game scene
					root.getChildren().clear();        	 	
				}
				catch(Exception e) {System.out.println(e);}

			}
		});
	}
	
	/**
	 * This button shows the Top5UsersList Scene.
	 * @param root
	 */
	public void viewButton(Group root) {
		Button viewButton = new Button("View TOP Players");
		viewButton.setPrefSize(150, 30);
		root.getChildren().add(viewButton);
		viewButton.relocate(700,800);
		
		viewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Top5Users obj = new Top5Users();
					obj.top5ListScene();

				}catch(Exception e) {System.out.println(e);}
			}
		});
	}
}
