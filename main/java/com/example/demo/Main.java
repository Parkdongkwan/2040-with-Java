package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	static final int WIDTH = 900;
	static final int HEIGHT = 900;

	/**
	 * This method displays the Menu scene
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {  
		Parent menuRoot = FXMLLoader.load(getClass().getResource("/pane/Menu.fxml"));  
		Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT);
		primaryStage.setScene(menuScene);
		primaryStage.setTitle("Menu");
		primaryStage.show();
	}

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

