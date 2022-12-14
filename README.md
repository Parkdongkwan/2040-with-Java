# COMP2042_CW_Park_Dong_Kwan

Park Dongkwan 20307405

To run this program, JRE System Library[jdk-17.0.2], JAVAFX, Junit 5 should be included in module path. 
Javadoc is in the file named “COMP2042-ParkDongKwan”. COMP2042-ParkDongKwan\JavaDoc
Features added:
Added a menu screen and choose difficulty screen by using FXML Scene builder. In menu screen, I added “Play Game” button which will switch from menu screen to choose game mode screen and “Quit” button which will turn off the whole system. Another thing is color picker which will change the menu screen, game screen and end game screen. In choose difficulty screen, I added three buttons that each lead to a different mode of the game.
Added a login screen which will pop up when the user chooses the game mode. Login screen contains text field where user can enter their name. If the user doesn’t write anything or leave a blank between words, it will alert the user with message. 
Added three different game types easy, hard, time-limit mode. Easy mode is same as original. Hard mode will generate 2,4,8 as random number instead of 2,4 (original). Time-limit mode added one more condition to lose which is whenever the timer reaches 0. 
Added score pop up window when user lose the game. 
Added account system so all the users will be stored in the txt file and only the highest score will be stored for same username. Using the account system, we added top 5 list by sorting the user information by their score. 
Added a function that changes the text in end game screen “Game Over” to “You Win!” when the user wins the game. And in the end game screen, I added return Menu button, Retry Game button where user can retry the game with same game type and color theme chosen previously. Also, View Top Players button where user can view top 5 highest player score and username. In the end game screen, the highest player and score and current username and score are displayed. 
I could implement a game mode that changes grid size (n x n), but even with small change of grid size, the difference in difficulty gets too big. Therefore, I chose not to change the original gird size. 
Classes that I newly made are ChooseGameModeSceneController.java, MenuSceneController.java, RecordHighestScore.java, EndGameSceneButtons.java, Top5Users.java, GameScore.java, Timer.java, GameUserName.java, LoginPage.java. 
All Classes in the original code was modified. And I extracted CellMovement.java and RandomFillNumber.java from GameScene.java.
Bug/Logic error fixed:
1.	Fixed the way scores being added. 
2.	Fixed error where the random cells are keep generating even though the cells cannot move. 
3.	Fixed error where the cell which is already merged can also be merged again in an action. 
4.	Fixed a bug where the game still goes on even though user win the game. 
5.	Fixed a quit button in endgame screen which do not close the window properly. 

