package com.example.demo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
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
    private Color myColor = Color.rgb(189, 177, 92);     //default color of background 
    @FXML
    private ColorPicker myColorPicker; 
    @FXML
    private Pane myPane;

    
    
	
	/**
	 * This method switches from menu scene to game scene
	 * @param event
	 * @throws IOException
	 */
	public void goToGame(ActionEvent event) throws IOException {       
		
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
	 * This method changes the menu background color and game scene background.
	 * @param event
	 */
	public void changeBackgroundColor(ActionEvent event) {            
		myColor = myColorPicker.getValue();
		myPane.setBackground(new Background(new BackgroundFill(myColor,null, null)));
	}
	
	
		
}
