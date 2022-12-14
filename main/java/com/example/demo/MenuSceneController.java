package com.example.demo;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * This class controls everything happening in Menu Scene.
 * @author ParkDongKwan
 *
 */
public class MenuSceneController{
	private Stage primaryStage;
	static final int WIDTH = 900;
	static final int HEIGHT = 900;
	public static Color myColor = Color.rgb(189, 177, 92);     //default color of background 
	@FXML
	private ColorPicker myColorPicker; 
	@FXML
	private Pane myPane;

	/**
	 * This method switches from menu scene to ChooseGameMode Scene.
	 * @param event (Button event)
	 * @throws IOException
	 */
	public void goToChooseGame(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/pane/ChooseGameMode.fxml"));	
		Scene scene = new Scene(root);
		primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Choose Difficulty");

		primaryStage.show();
	}

	/**
	 * If any action event happens, this method will alert user with message if they want to quit the page.
	 * If they press yes, it will exit the system.
	 * @param event (Button event)
	 * @throws IOException
	 */
	public void quitButton(ActionEvent event) throws IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quit Dialog");
		alert.setHeaderText("Quit from this page");
		alert.setContentText("Are you sure?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(0);                             
		}
	}

	/**
	 * This method changes the menu background color.
	 * @param event (Color Picker)
	 * @throws IOException 
	 */
	public void changeBackgroundColor(ActionEvent event) throws IOException {            
		myColor = myColorPicker.getValue();
		myPane.setBackground(new Background(new BackgroundFill(myColor,null, null)));

	}


}
