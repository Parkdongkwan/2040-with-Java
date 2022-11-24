package com.example.demo;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


class GameScene {
    private static int HEIGHT = 700;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;  //Length of a game scene                         
    private Cell[][] cells = new Cell[n][n];
    private long score = 0;
    private String username;
    
    
	RandomFillNumber randomFill = new RandomFillNumber(this.cells);
	CellMovement move = new CellMovement(this.cells);
	

//    static void setLENGTH(int number) {          //Change method Name      //(Fixed) Delete useless method
//        n = number;
//        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
//    }
	

	
	
    
	/**
	 * @return the length of GameScene
	 */
    static double getLENGTH() {
        return LENGTH;
    }
 
/**
 * @return 1 when the cell has value of 0 and return 0 when the cell has value of 2048. If neither the case, it returns -1
 */
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)     //Return 1 when the cell has value of 0
                    return 1;
                if(cells[i][j].getNumber() == 2048)   //Return 0 when the cell has value of 2048
                    return 0;
            }
        }
        return -1;        //Return -1 if there is no 2048 or 0 in the cells
    }

/**
 * This method set the score 
 */
    private void sumCellNumbersToScore() {
    	score = move.getScore();
    }
    
    /**
     * //Display the score and able to quit the window with button
     */
    public void popUpScoreWindow()      
    {
    Stage popupwindow=new Stage();     
    popupwindow.initModality(Modality.APPLICATION_MODAL);
    popupwindow.setTitle("Your Score");     
    Text text= new Text("Your Score is "+ score); 
    text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));      
    Button button1= new Button("Close this window");                                    //Close button
    button1.setOnAction(e -> popupwindow.close());                                      
    VBox layout= new VBox(10);    
    layout.getChildren().addAll(text, button1);                                        
    layout.setAlignment(Pos.CENTER);                                                    
    Scene scene1= new Scene(layout, 300, 250);
    popupwindow.setScene(scene1);  
    popupwindow.showAndWait();                                                          //it allows to hold the current stage from going to next stage
           
    }
    
    /**
     * This method display and controls the game scene
     * @param gameScene
     * @param root
     * @param primaryStage
     * @param endGameScene
     * @param endGameRoot
     */
    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {
    	
    	LoginPage login = new LoginPage();                      //Pop up login Page
    	login.popUpLoginWindow();
    	this.username = login.getUserName();

        randomFill.root = root;
        
        for (int i = 0; i < n; i++) {                                  //Making 4 X 4 grid
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }
        }

        Text text = new Text();                  
        root.getChildren().add(text);
        text.setText("SCORE :");        
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        
        
        Text scoreText = new Text();
        root.getChildren().add(scoreText);           //Display score
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        randomFill.randomFillNumber();                //Generate random numbers in the grid
        randomFill.randomFillNumber();

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
                Platform.runLater(() -> {
                    int haveEmptyCell;
                    if (key.getCode() == KeyCode.DOWN) {GameScene.this.move.moveDown();                      
                    } else if (key.getCode() == KeyCode.UP) {GameScene.this.move.moveUp();
                    } else if (key.getCode() == KeyCode.LEFT) {GameScene.this.move.moveLeft();
                    } else if (key.getCode() == KeyCode.RIGHT) {GameScene.this.move.moveRight();
                    } else { return;                                                                          //(Fixed) if any keys other than left,right,up,down is pressed, it will exit from the method
                    }
                   
                GameScene.this.sumCellNumbersToScore();
                
                RecordHighestScore obj = new RecordHighestScore(username,score);
                obj.ComputeHighestScore();       
                
                    scoreText.setText(score + "");
                    haveEmptyCell = GameScene.this.haveEmptyCell();
                    if (haveEmptyCell == -1) {
                        if (GameScene.this.move.canNotMove()) { 
                        	popUpScoreWindow();
                            primaryStage.setScene(endGameScene);
                            EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score, username, obj.getHighestScore(), obj.getHighestUserName());
                            root.getChildren().clear();
                            score = 0;
                        }
                    } else if(move.countChanges())      //(Fixed) if the cells are not moving(No changes) it will not generate random cells
                        GameScene.this.randomFill.randomFillNumber();
                });
            });
    }
}
