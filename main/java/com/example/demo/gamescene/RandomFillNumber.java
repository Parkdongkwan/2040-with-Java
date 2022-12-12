package com.example.demo.gamescene;

import java.util.Random;

import com.example.demo.cell.Cell;
import com.example.demo.textmaker.TextMaker;

import javafx.scene.Group;
import javafx.scene.text.Text;
/**
 * 
 * Fill the empty cells with random number
 * @author DongKwanPark-modified
 */
public class RandomFillNumber {
	private static int n = 4;
	private Cell[][] cells = new Cell[n][n];
	public Group root;
	private TextMaker textMaker = TextMaker.getSingleInstance();   

	/**
	 * Constructor for RandomFillNumber Class
	 * @param cells
	 */
	public RandomFillNumber(Cell[][] cells){
		this.cells = cells;
	}

	/**
	 * This class generates random number which is either 2 or 4. 
	 */
	public void randomFillNumberGeneral() {         //(FIXED): Delete useless parameter

		Cell[][] emptyCells = new Cell[n][n];
		int a = 0;
		int b = 0;
		int aForBound=0,bForBound=0;

		int[] bound = calculateBoundary(emptyCells, a, b, aForBound, bForBound);

		Text text;
		Random random = new Random();
		boolean putTwo = true;

		if (random.nextInt() % 2 == 0)             //if the random number generated is even number
			putTwo = false;                        //Boolean variable putTwo will be false.
		int xCell, yCell;
		xCell = random.nextInt(bound[0]+1);       //Randomly choosing the location for next Random 2,4 value according to aBound and bBound
		yCell = random.nextInt(bound[1]+1);   
		if (putTwo) {
			text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root); //if putTwo is True, it will write 2 in the location chosen above
			emptyCells[xCell][yCell].setTextClass(text);
			root.getChildren().add(text);
			emptyCells[xCell][yCell].setColorByNumber(2);
		} else {
			text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root); //if putTwo is False, it will write 4 in the location chosen above
			emptyCells[xCell][yCell].setTextClass(text);
			root.getChildren().add(text);
			emptyCells[xCell][yCell].setColorByNumber(4);
		}
	}

	/**
	 * This class generates random number which is either 2 or 4 or 8. 
	 */
	public void randomFillNumberHard() {        

		Cell[][] emptyCells = new Cell[n][n];
		int a = 0;
		int b = 0;
		int aForBound=0,bForBound=0;

		int[] bound = calculateBoundary(emptyCells, a, b, aForBound, bForBound);

		Text text;
		Random random = new Random();

		int randomNumber = random.nextInt(3);       

		int xCell, yCell;
		xCell = random.nextInt(bound[0]+1);      
		yCell = random.nextInt(bound[1]+1); 

		if (randomNumber == 0) {
			text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root); 
			emptyCells[xCell][yCell].setTextClass(text);
			root.getChildren().add(text);
			emptyCells[xCell][yCell].setColorByNumber(2);
		} 
		else if(randomNumber == 1) {
			text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root); 
			emptyCells[xCell][yCell].setTextClass(text);
			root.getChildren().add(text);
			emptyCells[xCell][yCell].setColorByNumber(4);
		}

		else {
			text = textMaker.madeText("8", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
			emptyCells[xCell][yCell].setTextClass(text);
			root.getChildren().add(text);
			emptyCells[xCell][yCell].setColorByNumber(8);
		}
	}
	
	

	/**
	 * Calculate the boundary where random cell can be generated
	 * @param emptyCells
	 * @param a
	 * @param b
	 * @param aForBound
	 * @param bForBound
	 * @return
	 */
	public int[] calculateBoundary(Cell[][] emptyCells, int a, int b, int aForBound, int bForBound) {
		outer:
			for (int i = 0; i < n; i++) {           
				for (int j = 0; j < n; j++) {             //Searching all the cells one by one using for loop
					if (cells[i][j].getNumber() == 0) { 
						emptyCells[a][b] = cells[i][j];   //If 0 value cells are found, they are assigned to emptyCells
						if (b < n-1) {
							bForBound=b;
							b++;

						} else {
							aForBound=a;
							a++;
							b = 0;
							if(a==n)
								break outer;
						}
					}
				}
			}
		int[] bound = new int[2];          //To return 2 integer values (bForBound, aForBound).
		bound[0] = aForBound;
		bound[1] = bForBound;
		return bound;
	}
}
