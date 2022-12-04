package com.example.gamescene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author DongKwanPark
 * This class pop up the window where user can type in their user name in text field.
 * And give access of the user name obtained from text field to other class using getUsrName method.
 */
public class LoginPage {
	private static String userName;       
	
	/**
	 * Pop up the window to obtain user name from the text field.
	 */
	public void popUpLoginWindow() {
		Stage stage = new Stage();
		//		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Enter your username");  
		Text text = new Text("Enter your username");
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField textField = new TextField();
		Button button = new Button("ENTER");
		button.setMaxSize(100, 200);
		Alert alert = new Alert(AlertType.ERROR);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(textField.getText().trim().equals("")) {     //if the user did not input user name, it will alert the user to enter user name
					alert.setTitle("An error has been encountered");
					alert.setHeaderText("Please Enter UserName without blank");
					alert.showAndWait();
				}
				else if(textField.getText().contains(" ")) {     //if the user input the user name with space or blank, it will alert the user to reenter the name
					alert.setTitle("An error has been encountered");
					alert.setHeaderText("Username cannot have Blank");
					alert.showAndWait();
				}
				else if(textField.getText().contains(",")) {     //if the user input the user name with space or blank, it will alert the user to reenter the name
					alert.setTitle("An error has been encountered");
					alert.setHeaderText("Please Enter UserName without \",\"");
					alert.showAndWait();
				}
				else
				{
					setUserName(textField.getText());	
					stage.close();	
				}
			}
		}); 
		VBox loginRoot = new VBox(10);
		loginRoot.getChildren().addAll(text,textField,button);
		Scene scene= new Scene(loginRoot, 450, 450);
		stage.setScene(scene);  

		//This pop up window will not disappear before the user close the window.
		stage.showAndWait();                                              
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		LoginPage.userName = userName;
	}
}