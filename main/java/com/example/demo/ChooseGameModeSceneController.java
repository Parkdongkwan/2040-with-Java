package com.example.demo;

import java.io.IOException;
import com.example.gamescene.GameScene;
import com.example.gamescene.LoginPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author ParkDongKwan
 * This class controls every action happening in ChooseGameModeScene
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

	LoginPage login;

	/**
	 * This method switches from ChooseGameMode Scene to Game1 Scene
	 * @param event
	 * @throws IOException
	 */
	public void goToGame(ActionEvent event) throws IOException {      
		login = new LoginPage();
		login.popUpLoginWindow();

		int gameType = 1;
		createSceneForEachGame(event,gameType);

	}

	/**
	 * This method switches from ChooseGameMode Scene to Game1 Scene
	 * @param event
	 * @throws IOException
	 */
	public void goToGame2(ActionEvent event) throws IOException {   

		login = new LoginPage();     
		login.popUpLoginWindow();

		int gameType = 2;
		createSceneForEachGame(event,gameType);

	}

	/**
	 * This method switches from ChooseGameMode Scene to game3 scene.
	 * @param event
	 * @throws IOException
	 */
	public void goToGame3(ActionEvent event) throws IOException {      
		login = new LoginPage();     
		login.popUpLoginWindow();

		int gameType = 3;

		createSceneForEachGame(event,gameType);

	}

	/**
	 * Create different gameScene for different gameType
	 * @param event
	 * @param gameType
	 */
	private void createSceneForEachGame(ActionEvent event, int gameType) {
		endGameRoot = new Group();
		endGameScene = new Scene(endGameRoot, WIDTH, HEIGHT, MenuSceneController.myColor);  //Change the color of endGame to the color obtained from colorPicker in MenuSceneController
		primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		gameRoot = new Group();
		gameScene = new Scene(gameRoot, WIDTH, HEIGHT, MenuSceneController.myColor);    //Change the color of gameScene to the color obtained from colorPicker in MenuSceneController
		primaryStage.setScene(gameScene);

		GameScene game = new GameScene(primaryStage, gameScene, gameRoot, endGameScene, endGameRoot);

		switch(gameType) {
		case 1:
			game.game();
			break;
		case 2:
			game.game2();
			break;
		case 3 :
			game.game3();
		}
	}
}
