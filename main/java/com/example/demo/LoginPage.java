package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author DongKwanPark
 * This class pop up the window where user can type in their user name in textfield.
 * And give access of the username obtained from textfield to other class using getUsrName method.
 */
public class LoginPage {
	private String userName;
	/**
	 * Pop up the window to obtain username from textfield.
	 */
    public void popUpLoginWindow() {
    	Stage popupwindow2 = new Stage();
        popupwindow2.initModality(Modality.APPLICATION_MODAL);
        popupwindow2.setTitle("Enter your username");  
        Text text = new Text("Enter your username");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        TextField textField = new TextField();
        Button button2 = new Button("ENTER");
        button2.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		userName = textField.getText();	
        		popupwindow2.close();	
        	}
        }); 
        VBox root = new VBox(10);
        root.getChildren().addAll(text,textField,button2);
        Scene scene2= new Scene(root, 300, 250);
        popupwindow2.setScene(scene2);  
        popupwindow2.showAndWait();   
    }

	public String getUserName() {
		return userName;
	}

}
