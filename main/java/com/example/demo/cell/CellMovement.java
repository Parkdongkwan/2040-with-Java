package com.example.demo.cell;

import com.example.demo.gamescene.GameScore;

/**
 * This class allow cells to move in every 4 directions(up,down,left,right) and check if the cells can move or not.
 * @author DongKwanPark-modified
 */
public class CellMovement {
	private static int n = 4;
	private Cell[][] cells = new Cell[n][n];
	private int count = 0;
	
	GameScore gs = GameScore.getInstance();
	
	/**
	 * Constructor for CellMovement class
	 * @param cells
	 */
	public CellMovement(Cell[][] cells){
		this.cells = cells;
	}

	/**
	 * This method counts the movement of cells and if there was no movement (count == 0)
	 * It will be used to check if the system has to generate random number or not. 
	 * @return  false if there was any modification in cells. But if there was any modification( count > 0) , then it will return true
	 */
	public boolean countChanges() {
		//if 0 movement of cells
		if(count == 0)
			return false;
		else
			count = 0;
		return true;
	}

	/**
	 * According to the direction, this method returns the coordinate where the cells should move to. 
	 * @param i (row of the cell)
	 * @param j (column of the cell)
	 * @param direct (direction left,right,up,down) 
	 * @return coordinate (destination of the cell) and if the direction is not (left or up or down or right) it will return -1.
	 */
	private int passDestination(int i, int j, char direct) {
		int coordinate = j;
		if (direct == 'l') {
			for (int k = j - 1; k >= 0; k--) {
				if (cells[i][k].getNumber() != 0) {     //if there is any cell with value on left side, it will return the coordinate which is right side of at most front cell on left side.
					coordinate = k + 1;
					break;
				} else if (k == 0) {                    //if there is only cell with 0 value, it will return the most left side which is 0. 
					coordinate = 0;
				}
			}
			return coordinate;
		}
		coordinate = j;
		if (direct == 'r') {
			for (int k = j + 1; k <= n - 1; k++) {
				if (cells[i][k].getNumber() != 0) {     //if there is any cell with value on right side, it will return the coordinate which is left side of at most front cell on right side.
					coordinate = k - 1;
					break;
				} else if (k == n - 1) {                //if there is only cell with 0 value, it will return the most right side which is n-1.
					coordinate = n - 1;                 
				}
			}
			return coordinate;
		}
		coordinate = i;
		if (direct == 'd') {
			for (int k = i + 1; k <= n - 1; k++) {
				if (cells[k][j].getNumber() != 0) {       //if there is any cell with value on below side, it will return the coordinate which is upper side of at most front cell on below side.
					coordinate = k - 1;
					break;

				} else if (k == n - 1) {                  //if there is only cell with 0 value, it will return the most below side which is n-1.
					coordinate = n - 1;
				}
			}
			return coordinate;
		}
		coordinate = i;
		if (direct == 'u') {
			for (int k = i - 1; k >= 0; k--) {
				if (cells[k][j].getNumber() != 0) {         //if there is any cell with value on upper side, it will return the coordinate which is below side of at most front cell on upper side.
					coordinate = k + 1;
					break;
				} else if (k == 0) {                        //if there is only cell with 0 value, it will return the most upper side which is 0.
					coordinate = 0;                       
				}
			}
			return coordinate;
		}
		return -1;
	}

	/**
	 * Move all the cells to Left
	 */
	public void moveLeft() {
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if(cells[i][j].getNumber() != 0)                            //Does not count the move of empty cells
					moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
			}
			resetCellModi(cells);
		}

	}

	/**
	 * Move all the cells to Right
	 */
	public void moveRight() {
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j >= 0; j--) {
				if(cells[i][j].getNumber() != 0)                           //Does not count the move of empty cells
					moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
			}
			resetCellModi(cells);  
		}
	}

	/**
	 * Move all the cells to Up
	 */
	public void moveUp() {
		for (int j = 0; j < n; j++) {
			for (int i = 1; i < n; i++) {
				if(cells[i][j].getNumber() != 0)                          //Does not count the move of empty cells
					moveVertically(i, j, passDestination(i, j, 'u'), -1);
			}
			resetCellModi(cells);

		}

	}

	/**
	 * Move all the cells to Down
	 */
	public void moveDown() {
		for (int j = 0; j < n; j++) {
			for (int i = n - 1; i >= 0; i--) {
				if(cells[i][j].getNumber() != 0)                         //Does not count the move of empty cells
					moveVertically(i, j, passDestination(i, j, 'd'), 1);
			}
			resetCellModi(cells);
		}

	}

	/**
	 * Validating if the cell can move horizontally
	 * @param i (row of the cell)
	 * @param j (column of the cell)
	 * @param des (destination of cell)
	 * @param sign (left, down = -1 / right,up = 1)
	 * @return true if the move is validated. If the move is not validated, it will return false.
	 */
	private boolean isValidDesH(int i, int j, int des, int sign) {
		if (des + sign < n && des + sign >= 0) {
			if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
					&& cells[i][des + sign].getNumber() != 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Move a cell Horizontally
	 * @param i (row of the cell)
	 * @param j (column of the cell)
	 * @param des (destination of cell)
	 * @param sign (left, down = -1 / right,up = 1)
	 */
	private void moveHorizontally(int i, int j, int des, int sign) {
		if (isValidDesH(i, j, des, sign)) { 
			cells[i][j].adder(cells[i][des + sign]);
			gs.setScore(cells[i][des + sign].getNumber());         //(Fixed) Fixed the scoring system by updating the score variable instantly after merging.
			//(Fixed) Fixed the error where wrong cell was setModify(true). Instead, i set cells[i][des+sign] 
			//which is the cell they merge so the merged cell wont be able to change.
			cells[i][des+sign].setModify(true);                			                                                   
			count++;                                           //Count if there was any modification in cells
		} else if (des != j) {
			cells[i][j].changeCell(cells[i][des]);
			count++;                                           //Count if there was any modification in cells

		} 

	}

	/**
	 * Validating if the cell can move Vertically
	 * @param i (row of the cell)
	 * @param j (column of the cell)
	 * @param des (destination of cell)
	 * @param sign (left, down = -1 / right,up = 1)
	 * @return true if the move is validated. If the move is not validated, it will return false.
	 */
	private boolean isValidDesV(int i, int j, int des, int sign) {
		if (des + sign < n && des + sign >= 0)
			if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
			&& cells[des + sign][j].getNumber() != 0) {
				return true;
			}
		return false;
	}

	/**
	 * Move a cell vertically
	 * @param i (row of the cell)
	 * @param j (column of the cell)
	 * @param des (destination of cell)
	 * @param sign (left, down = -1 / right,up = 1)
	 */
	private void moveVertically(int i, int j, int des, int sign) {
		if (isValidDesV(i, j, des, sign)) {   
			cells[i][j].adder(cells[des + sign][j]);
			gs.setScore(cells[des + sign][j].getNumber());        //(Fixed) Fixed the scoring system by adding the value of merged cell after merging
			//(Fixed) Fixed the error where wrong cell was setModify(true). Instead, i set cells[i][des+sign] 
			//which is the cell they merge so the merged cell wont be able to change.
			cells[des+sign][j].setModify(true);               
			count++;                                          //Count if there was any modification in cells           
		} else if (des != i) {
			cells[i][j].changeCell(cells[des][j]);
			count++;                                          //Count if there was any modification in cells
		}
	}

	/**
	 * Check if a cell have same number value of cell near by.
	 * @param i (row of cell)
	 * @param j (column of cell)
	 * @return true if a cell have same number near by. If not, it returns false.
	 */
	private boolean haveSameNumberNearly(int i, int j) {
		if (i < n - 1 && j < n - 1) {
			if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
				return true;
			if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
				return true;
		}
		return false;
	}

	/**
	 * This method is to check if the game is over ( nothing to move anymore)
	 * @return true if cannot move. And returns false if the cells can move.
	 */
	public boolean canNotMove() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (haveSameNumberNearly(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * this method set all cells false for modification
	 * @param cells
	 */
	public void resetCellModi(Cell[][] cells) {            
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cells[i][j].setModify(false);
			}
		}
	}

}
