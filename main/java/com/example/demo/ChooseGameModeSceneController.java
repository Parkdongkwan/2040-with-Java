package com.example.demo;

import java.io.IOException;

import com.example.demo.gamescene.GameScene;
import com.example.demo.loginscene.LoginPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * This class controls every action happening in ChooseGameModeScene
 * @author ParkDongKwan
 */
public class ChooseGameModeSceneController {
	static final int WIDTH = 900;
	static final int HEIGHT = 900;

	@FXML
	private Pane myPane2;

	private Stage primaryStage;
	private Group endGameRoot;
	private Scene endGameScene;
	private Group gameRoot;
	private Scene gameScene;
	private LoginPage login = new LoginPage();

	/**
	 * This method switches from ChooseGameMode Scene to Game1 Scene
	 * @param event (Button event)
	 * @throws IOException
	 */
	public void goToGame(ActionEvent event) throws IOException {    
		//Display the login page
		login.popUpLoginWindow();

		int gameType = 1;
		createSceneForGame(event,gameType);

	}

	/**
	 * This method switches from ChooseGameMode Scene to Game2 Scene
	 * @param event (Button event)
	 * @throws IOException
	 */
	public void goToGame2(ActionEvent event) throws IOException {   
		//Display the login page  
		login.popUpLoginWindow();

		int gameType = 2;
		createSceneForGame(event,gameType);

	}

	/**
	 * This method switches from ChooseGameMode Scene to Game3 scene.
	 * @param event (Button event)
	 * @throws IOException
	 */
	public void goToGame3(ActionEvent event) throws IOException {    
		//Display the login page  
		login.popUpLoginWindow();

		int gameType = 3;
		createSceneForGame(event,gameType);

	}

	/**
	 * Create different gameScene for different gameType
	 * @param event (Button event)
	 * @param gameType (Type 1 is easy mode Type 2 is hard mode Type 3 is time-limit mode)
	 */
	private void createSceneForGame(ActionEvent event, int gameType) {
		endGameRoot = new Group();
		endGameScene = new Scene(endGameRoot, WIDTH, HEIGHT, MenuSceneController.myColor);  //Change the color of endGame to the color obtained from colorPicker in MenuSceneController
		primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		gameRoot = new Group();
		gameScene = new Scene(gameRoot, WIDTH, HEIGHT, MenuSceneController.myColor);    //Change the color of gameScene to the color obtained from colorPicker in MenuSceneController
		primaryStage.setScene(gameScene);

		GameScene game = new GameScene(primaryStage, gameScene, gameRoot, endGameScene, endGameRoot);

		switch(gameType) {
		//if gameType is 1
		case 1:
			game.game();
			break;
			//if gameType is 2
		case 2:
			game.game2();
			break;
			//if gameType is 3
		case 3 :
			game.game3();
		}
	}
}
