package com.example.gamescene;
import com.example.account.RecordHighestScore;
import com.example.cell.Cell;
import com.example.cell.CellMovement;
import endgame.EndGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author ParkDongKwan-modified
 * This class build and design the gameScene and handle every actions happening in gameScene.
 */
public class GameScene {
	private static int HEIGHT = 700;
	private static int n = 4;
	private final static int distanceBetweenCells = 10;
	private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;  

	private Cell[][] cells = new Cell[n][n];

	public static long score = 0;

	private final Integer startTime = 10;
	private Integer seconds = startTime;
	Label remainingTime;
	Text scoreText;

	RecordHighestScore recordScore;

	RandomFillNumber randomFill = new RandomFillNumber(this.cells);

	CellMovement move = new CellMovement(this.cells);
	
	private Stage primaryStage;
	private Scene gameScene;
	private Group gameRoot;
	private Scene endGameScene;
	private Group endGameRoot;
	
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
	 * @param gameScene
	 * @param root
	 * @param primaryStage
	 * @param endGameScene
	 * @param endGameRoot
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
	 * @param gameScene
	 * @param root
	 * @param primaryStage
	 * @param endGameScene
	 * @param endGameRoot
	 */
	public void game2() {

		primaryStage.setTitle("2048 Hard Mode");

		int gameType = 2;

		gameSceneUI(); 

		randomFill.root = gameRoot;
		randomFill.randomFillNumberHard();                
		randomFill.randomFillNumberHard();

		gameEventHandler(gameType);
	}

	/**
	 * This method display and controls the game scene3
	 * @param gameScene
	 * @param root
	 * @param primaryStage
	 * @param endGameScene
	 * @param endGameRoot
	 */
	public void game3() {

		primaryStage.setTitle("2048 Time-Limit mode");

		int gameType = 3;

		gameSceneUI(); 

		remainingTimeUI();
		countDown();

		randomFill.root = gameRoot;
		randomFill.randomFillNumberGeneral();                
		randomFill.randomFillNumberGeneral();

		gameEventHandler(gameType);
	}

	/**
	 * @return 1 when the cell has value of 0 
	 * and return 0 when the cell has value of 2048. 
	 * If neither the case, it returns -1
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
		return -1;       
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
	private void popUpScoreWindow()      
	{
		Stage popupwindow=new Stage();     
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Your Score");     
		Text text= new Text("Your Score is "+ score); 
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
	 * @param root
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
	 * @param gameScene
	 * @param root
	 * @param primaryStage
	 * @param endGameScene
	 * @param endGameRoot
	 * @param gameType
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

				//It will keep updating the score.
				GameScene.this.sumCellNumbersToScore();
				scoreText.setText(score + "");

				haveEmptyCell = GameScene.this.haveEmptyCell();
				if (haveEmptyCell == -1) {
					if (GameScene.this.move.canNotMove()) { 	
						popUpScoreWindow();

						recordScore = new RecordHighestScore();
						recordScore.ComputeHighestScore();  

						primaryStage.setScene(endGameScene);
						EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, recordScore.getHighestScore(), recordScore.getHighestUserName(), gameType);
						gameRoot.getChildren().clear();
						score = 0;
					}
				} else if(move.countChanges()) {     //(Fixed) if the cells are not moving(No changes) it will not generate random cells 
					if (gameType ==2)
						GameScene.this.randomFill.randomFillNumberHard();
					else
						GameScene.this.randomFill.randomFillNumberGeneral();
				}
			});
		});
	}

	/**
	 * This method works as count down timer. It will change the scene to end game scene when times up.
	 * @param gameScene
	 * @param root
	 * @param primaryStage
	 * @param endGameScene
	 * @param endGameRoot
	 */
	private void countDown() {

		//gameType
		int gameType = 3;

		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);

		if(time!= null) {
			time.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				seconds--;
				remainingTime.setText("You Only Have: "+seconds.toString());

				if(seconds<=0) {
					time.stop();
					
					popUpScoreWindow();

					recordScore = new RecordHighestScore();
					recordScore.ComputeHighestScore();  
					//Set the scene to end game scene.
					primaryStage.setScene(endGameScene);
					EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, recordScore.getHighestScore(), recordScore.getHighestUserName(), gameType);

					gameRoot.getChildren().clear();
				}
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}

	/**
	 * UI design for remaining time
	 * @param root
	 */
	public void remainingTimeUI() {
		remainingTime = new Label();
		remainingTime.setTextFill(Color.RED);
		remainingTime.setFont(Font.font(20));
		remainingTime.relocate(700, 200);
		gameRoot.getChildren().add(remainingTime);
	}

}
