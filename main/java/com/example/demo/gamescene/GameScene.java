package com.example.demo.gamescene;
import com.example.demo.account.RecordHighestScore;
import com.example.demo.cell.Cell;
import com.example.demo.cell.CellMovement;
import com.example.demo.endgame.EndGame;

import javafx.animation.Timeline;
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

/**
 * 
 * This class build and design the gameScene and handle every actions happening in gameScene.
 * @author ParkDongKwan-modified
 */
public class GameScene {
	private static int HEIGHT = 700;
	private static int n = 4;
	private final static int distanceBetweenCells = 10;
	private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;  

	private Cell[][] cells = new Cell[n][n];

	GameScore gs = GameScore.getInstance();

	Text scoreText; 
	RecordHighestScore recordScore;
	RandomFillNumber randomFill = new RandomFillNumber(this.cells);
	CellMovement move = new CellMovement(this.cells);
	
	private Stage primaryStage;
	private Scene gameScene;
	private Group gameRoot;
	private Scene endGameScene;
	private Group endGameRoot;
	
	private Timeline time = new Timeline();
	
	/**
	 * Constructor
	 * @param primaryStage
	 * @param gameScene
	 * @param gameRoot
	 * @param endGameScene
	 * @param endGameRoot
	 */
	public GameScene(Stage primaryStage, Scene gameScene, Group gameRoot, Scene endGameScene, Group endGameRoot){
		this.primaryStage = primaryStage;
		this.gameScene = gameScene;
		this.gameRoot = gameRoot;
		this.endGameScene = endGameScene;
		this.endGameRoot = endGameRoot;
	}

	/**
	 * @return the length of gameScene
	 */
	public static double getLENGTH() {
		return LENGTH;
	}

	/**
	 * This method display and controls the game scene
	 */
	public void game() {
		primaryStage.setTitle("2048");
		//GameType
		int gameType = 1;                                    
		//GameScene UI
		gameSceneUI();                                   
		//Firstly generated 2 random cells
		randomFill.root = gameRoot;
		randomFill.randomFillNumberGeneral();                
		randomFill.randomFillNumberGeneral();
		gameEventHandler(gameType);
	}

	/**
	 * This method display and controls the game scene2
	 */
	public void game2() {
		primaryStage.setTitle("2048 Hard Mode");
		//GameType
		int gameType = 2;
		//GameScene UI
		gameSceneUI(); 
		//Firstly generated 2 random cells
		randomFill.root = gameRoot;
		randomFill.randomFillNumberHard();                
		randomFill.randomFillNumberHard();
		gameEventHandler(gameType);
	}

	/**
	 * This method display and controls the game scene3
	 */
	public void game3() {

		primaryStage.setTitle("2048 Time-Limit mode");
		//GameType
		int gameType = 3;
		//GameScene UI
		gameSceneUI(); 
		//Count down timer and displaying remaining time in game.
		Timer obj = new Timer();
		obj.countDown(primaryStage,gameRoot, endGameScene, endGameRoot, time);
		obj.remainingTimeUI(gameRoot);
		//Firstly generated 2 random cells
		randomFill.root = gameRoot;
		randomFill.randomFillNumberGeneral();                
		randomFill.randomFillNumberGeneral();

		gameEventHandler(gameType);
	}

	/**
	 * @return 0 if there is 2048 and return 1 if there is empty cell and return -1 if both conditions are not met. 
	 */
	private int  haveEmptyCell() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(cells[i][j].getNumber() == 2048)   //Return 0 when the cell has value of 2048
					return 0;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
		if (cells[i][j].getNumber() == 0)     //Return 1 when the cell has value of 0
			return 1;   
			}
		}
		return -1;   
	}

	/**
	 * //Display the score window
	 */
	private void popUpScoreWindow()      
	{
		Stage popupwindow=new Stage();     
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Your Score");     
		Text text= new Text("Your Score is "+ gs.getScore()); 
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 

		//Close button
		Button button1= new Button("Close this window");                                    
		button1.setOnAction(e -> popupwindow.close());       

		VBox layout= new VBox(10);    
		layout.getChildren().addAll(text, button1);                                        
		layout.setAlignment(Pos.CENTER);       

		Scene scene= new Scene(layout, 300, 250);
		popupwindow.setScene(scene);  

		//it allows to hold the current stage from going to next stage
		popupwindow.showAndWait();                                                          
	}

	/**
	 * UI design for the gameScene
	 */
	public void gameSceneUI() {
		//Display the 4 X 4 grid
		for (int i = 0; i < n; i++) {                                  
			for (int j = 0; j < n; j++) {
				cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
						(i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, gameRoot);
			}
		}
		//Display the score
		Text text = new Text();                  
		gameRoot.getChildren().add(text);
		text.setText("SCORE :");        
		text.setFont(Font.font(30));
		text.relocate(750, 100);
		scoreText = new Text();
		gameRoot.getChildren().add(scoreText);           
		scoreText.relocate(750, 150);
		scoreText.setFont(Font.font(20));
		scoreText.setText("0");
	}

	/**
	 * Control all the actions when the keys are pressed.
	 * @param gameType (Type 1 is easy mode Type 2 is hard mode Type 3 is time-limit mode)
	 */
	public void gameEventHandler(int gameType) {

		//cells move everyTime key is pressed
		gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
			Platform.runLater(() -> {
				int haveEmptyCell;
				if (key.getCode() == KeyCode.DOWN) {GameScene.this.move.moveDown();                      
				} else if (key.getCode() == KeyCode.UP) {GameScene.this.move.moveUp();
				} else if (key.getCode() == KeyCode.LEFT) {GameScene.this.move.moveLeft();
				} else if (key.getCode() == KeyCode.RIGHT) {GameScene.this.move.moveRight();
				} else { return;                                                                          //(Fixed) if any keys other than left,right,up,down is pressed, it will exit from the method
				}
				//Display score on the gameScene
				scoreText.setText(gs.getScore() + "");
				
				//Check if the game is with empty cells, 2048 cell or full. 
				haveEmptyCell = GameScene.this.haveEmptyCell();
				if (haveEmptyCell == -1) {
					if (GameScene.this.move.canNotMove()) { 	
						boolean winOrLose = false;
						time.stop();                             //In case user is playing gmae3(Time-limit mode) it will stop the time when user lose earlier than countDown
						popUpScoreWindow();

						recordScore = new RecordHighestScore();
						//Compute the highestScore
						recordScore.ComputeHighestScore();       
						
						primaryStage.setScene(endGameScene);
						EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, recordScore.getHighestScore(), recordScore.getHighestUserName(), gameType, winOrLose);
						gameRoot.getChildren().clear();
					}
				} else if(haveEmptyCell == 1) {     
					if(move.countChanges()) {          //(Fixed) if the cells are not moving(No changes) it will not generate random cells 
						if (gameType ==2)
							//Generate random number with the range of 2,4,8)
							GameScene.this.randomFill.randomFillNumberHard();
						else
							GameScene.this.randomFill.randomFillNumberGeneral();
					}
				}
				//if the user reach 2048 
				else if (haveEmptyCell == 0) {
					boolean winOrLose = true;
					popUpScoreWindow();
					recordScore = new RecordHighestScore();
					recordScore.ComputeHighestScore();  
					primaryStage.setScene(endGameScene);
					EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, recordScore.getHighestScore(), recordScore.getHighestUserName(), gameType, winOrLose);
					gameRoot.getChildren().clear();
				}
				
			});
		});
	}

}
