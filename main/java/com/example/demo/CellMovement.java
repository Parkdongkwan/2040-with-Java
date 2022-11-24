package com.example.demo;

/**
 * @author DongKwanPark-modified
 * This class allow cells to move right left up and down and check if the cells can move or not.
 */
public class CellMovement {
    private static int n = 4;
    private Cell[][] cells = new Cell[n][n];
    private long score = 0;
    private int count = 0;
    
    
    /**
     * This method return false if there was any modification in cells
     * if there was any modification( count >0) , then it will return true and reset the count to 0 again.
     * @return
     */
    public boolean countChanges() {
    	if(count == 0)
    		return false;
    	else
    		count = 0;
    		return true;
    }
    

    
    
    /**
     * Constructor for CellMovement class
     * @param cells
     */
    CellMovement(Cell[][] cells){
    	this.cells = cells;

    }
    /**
     * @return score
     */
    public long getScore() {
    	return score;
    }

    
	/**
	 * According to the direction, this method returns the coordinate where the cells should move to. 
	 * @param i
	 * @param j
	 * @param direct
	 * @return coordinate
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
            	if(cells[i][j].getNumber() != 0)
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }
    
    /**
     * Move all the cells to Right
     */
    public void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
            	if(cells[i][j].getNumber() != 0)
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * Move all the cells to Up
     */
    public void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
            	if(cells[i][j].getNumber() != 0)
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
                
            }
        }

    }
    
    /**
     * Move all the cells to Down
     */
    public void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
            	if(cells[i][j].getNumber() != 0)
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * Validating if the cell can move horizontally
     * @param i
     * @param j
     * @param des
     * @param sign
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
 * @param i
 * @param j
 * @param des
 * @param sign
 */
    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign) && !cells[i][des].getModify()) {  //(fIXED) Fixed the auto double merging.
            cells[i][j].adder(cells[i][des + sign]);
            score += cells[i][des + sign].getNumber();         //(Fixed) Fixed the scoring system by adding the value of merged cell after merging
            cells[i][des].setModify(true);
            count++;                                           //Count if there was any modification in cells
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
            count++;                                           //Count if there was any modification in cells

        } 

    }

    /**
     * Validating if the cell can move Vertically
     * @param i
     * @param j
     * @param des
     * @param sign
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
 * @param i
 * @param j
 * @param des
 * @param sign
 */
    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign) && !cells[des][j].getModify()) {       //(Fixed) Fixed the auto double merging.
            cells[i][j].adder(cells[des + sign][j]);
            score += cells[des + sign][j].getNumber();        //(Fixed) Fixed the scoring system by adding the value of merged cell after merging
            cells[des][j].setModify(true);
            count++;                                          //Count if there was any modification in cells           
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            count++;                                          //Count if there was any modification in cells
        }
    }

    /**
     * Check if a cell have same number value of cell near by.
     * @param i
     * @param j
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
 * 
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

}
