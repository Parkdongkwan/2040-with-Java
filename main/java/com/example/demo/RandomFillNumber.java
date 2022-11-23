package com.example.demo;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.text.Text;
/**
 * 
 * @author DongKwanPark-modified
 *
 */
public class RandomFillNumber {
    private static int n = 4;
    private Cell[][] cells = new Cell[n][n];
    @SuppressWarnings("exports")
	public Group root;
    private TextMaker textMaker = TextMaker.getSingleInstance();   
    
    /**
     * Constructor for RandomFillNumber Class
     * @param cells
     */
    RandomFillNumber(Cell[][] cells){
    	this.cells = cells;
    }
    
/**
 * This class generates random number which is either 2 or 4. 
 */
    public void randomFillNumber() {         //(FIXED): Delete useless parameter

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
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



        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)             //if the random number generated is even number
            putTwo = false;                        //Boolean variable putTwo will be false.
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);       //Randomly choosing the location for next Random 2,4 value according to aBound and bBound
            yCell = random.nextInt(bForBound+1);   
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

}
