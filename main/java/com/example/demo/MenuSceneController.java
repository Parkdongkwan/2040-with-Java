package com.example.demo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * This class controls everything happening in Menu Scene.
 * @author DongKwanPark-modified
 *
 */
public class MenuSceneController {
	private Stage primaryStage;
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    public static Color myColor = Color.rgb(189, 177, 92);     //default color of background 
    public static String username;
    @FXML
    private ColorPicker myColorPicker; 
    @FXML
    private Pane myPane;

    
 /**
  * This method switches from menu scene to ChooseGameMode Scene.
  * @param event
  * @throws IOException
  */
    public void goToChooseGame(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("ChooseGameMode.fxml"));
    	primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
	
	/**
	 * This method switches from ChooseGameMode Scene to Easy Mode Game Scene
	 * @param event
	 * @throws IOException
	 */
	public void goToGame(ActionEvent event) throws IOException {      
		
		
    	LoginPage login = new LoginPage();                      //Pop up login Page
    	login.popUpLoginWindow();
    	username = login.getUserName();
		
		Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, myColor);
        
		primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		Group gameRoot = new Group();
		Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, myColor);
		
		primaryStage.setScene(gameScene);
		
        GameScene game = new GameScene();
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
	}
	
	/**
	 * This method switches from ChooseGameMode Scene to Hard Mode Game Scene
	 * @param event
	 * @throws IOException
	 */
	public void goToGame2(ActionEvent event) throws IOException {      
		
		
    	LoginPage login = new LoginPage();                      
    	login.popUpLoginWindow();
    	username = login.getUserName();
		
		Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, myColor);
        
		primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		Group gameRoot = new Group();
		Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, myColor);
		
		primaryStage.setScene(gameScene);
		
        GameScene game = new GameScene();
        game.game2(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
	}
	
	/**
	 * This method switches from ChooseGameMode Scene to time-limit mode game scene.
	 * @param event
	 * @throws IOException
	 */
	public void goToGame3(ActionEvent event) throws IOException {      
		
		
    	LoginPage login = new LoginPage();                      
    	login.popUpLoginWindow();
    	username = login.getUserName();
		
		Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, myColor);
        
		primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		Group gameRoot = new Group();
		Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, myColor);
		
		primaryStage.setScene(gameScene);
		
        GameScene game = new GameScene();
        game.game3(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
	}
	
	
	
	
	/**
	 * This method changes the menu background color and game scene background.
	 * @param event
	 */
	public void changeBackgroundColor(ActionEvent event) {            
		myColor = myColorPicker.getValue();
		myPane.setBackground(new Background(new BackgroundFill(myColor,null, null)));
	}
	
	
		
}
