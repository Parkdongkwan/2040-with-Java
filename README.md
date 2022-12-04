# COMP2042_CW_Park_Dong_Kwan
Park Dong Kwan 20307405

First push (update) :
1. Fixed adding up score algorithm 
2. Fixed the error of getting stuck in the scene screen when pressing the quit button.

Second push (update2) :
1. Separate out CellMovement class, RandomFillNumber class from GameScene class.
2. Added Menu(FXML) Screen and button where it direct to gamescene.
3. Added Color Picker in menu screen so that it can change the background color of gamescene.
4. Added a pop up login page before getting into gamescene.
5. Added a class where it records the highest score by comparing in txt file. 
6. Added a text about highest score and highest username in the endgamescene. 
7. Added pop up window showing the score after the game ends. 

Third push (update3) :
1.Fixed an error where the cells don't move, but random numbers keep generating. 
2.Fixed an error in which already merged cells are merged once more.  

Fourth push (update4) :
1. Added a functionality in login page which alert the user if user did not put any value in textfield. 
2. Added 2 more different games. First is that not only 2 and 4,  8 will also be generated randomly. Another game is with countdown timer. So if the timer become 0, the game will be ended.
3. Added ChooseGameMode(FXML) screen with three buttons which enable user to play three different difficulty of games. 

Fifth push (update5) :
1. Fixed double merge 
2. Reduce the use of textfile by saving both the username and score in one text file. 
3. divded classes into packages.
4. Made one more controller for ChooseGameModeScene. 
5. Added use of Account class to store all the users in text file (Not only highest) and not updating the score (if lower) of same user name.
6  Added leaderboard where they show top 5 users with their scores.
7. Added quit button in Menu scene.

Six push ( update 6)
1.change in package name
2.(Bug fixed) Bug fixed where in game three if use die earlier than countdown, the timer still goes on.
3. Create test class for Junit testing
