package com.example.demo;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * This class handles everything happening in endgame scene
 * @author DongKwanPark-modified
 *
 */
public class EndGame {
	
    private static EndGame singleInstance = null;        
    private EndGame(){}                          //This constructor is private so that this class cannot be instantiated.
   
    /**
     * This method returns object
     * @return
     */
    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }   

    /**
     * This method displays the end game scene
     * @param endGameScene
     * @param root
     * @param primaryStage
     * @param score
     * @param username
     * @param highestScore
     * @param highestUserName
     */
    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score, String username, long highestScore, String highestUserName){
    	
        Text text = new Text("GAME OVER");                      //Display "GAME OVER"
        text.relocate(250,250);
        text.setFont(Font.font(80));
        root.getChildren().add(text);
         
        Text userName = new Text("UserName:  "+username);        //Display userName
        userName.setFill(Color.BLACK);
        userName.relocate(0,700);
        userName.setFont(Font.font(50));
        root.getChildren().add(userName);
       

        Text scoreText = new Text("Score:  "+score);             //Display the Score
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(0,600);
        scoreText.setFont(Font.font(50));
        root.getChildren().add(scoreText);
        
        Text highestScoreUserNameText = new Text("Highest Score: "+ highestScore +" / " + highestUserName);             //Display the Score
        highestScoreUserNameText.setFill(Color.BLACK);
        highestScoreUserNameText.relocate(0,500);
        highestScoreUserNameText.setFont(Font.font(50));
        root.getChildren().add(highestScoreUserNameText);
        
        
        Button quitButton = new Button("QUIT");                //QUIT button
        quitButton.setPrefSize(100,30);
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

}
