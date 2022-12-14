package com.example.demo.cell;

import com.example.demo.textmaker.TextMaker;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * 
 * Create a cell and manage the cell's size, color, position, and text within it.
 * And also add the cells. 
 * This class is used in every activities including cell (movement, modification, random generate)
 * @author ParkDongKwan-modified
 */
public class Cell {
	private Rectangle rectangle;
	private Group root;
	private Text textClass;
	private boolean modify = false;

	/**
	 * Set the boolean value "modify"
	 * @param modify 
	 */
	public void setModify(boolean modify) {       
		this.modify = modify;
	}

	/**
	 * @return boolean value "modify"
	 */
	public boolean getModify() {
		return modify;
	}

	/**
	 * Constructor for Cell class
	 * @param x (location of cell in x axis)
	 * @param y (location of cell in y axis)
	 * @param scale (size of the cell)
	 * @param root
	 */
	public Cell(double x, double y, double scale, Group root) {  
		rectangle = new Rectangle();
		cellLocation(x, y);                               
		cellFrame(scale);                                 
		cellColor();
		cellText(x, y, root);

		this.root = root;
		root.getChildren().add(rectangle);
	}

	/**
	 * Set the cell(rectangle) location
	 * @param x (location of cell in x axis)
	 * @param y (location of cell in y axis)
	 */
	private void cellLocation(double x, double y) {         
		rectangle.setX(x);
		rectangle.setY(y);
	}

	/**
	 * Set the size of cell(rectangle)
	 * @param scale (size of cell)
	 */
	private void cellFrame(double scale) {                   
		rectangle.setHeight(scale);
		rectangle.setWidth(scale);
	}

	/**
	 * Set the text inside cell(rectangle)
	 * @param x (location of cell in x axis)
	 * @param y (location of cell in y axis)
	 * @param root
	 */
	private void cellText(double x, double y, Group root) {   
		this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
	}

	/**
	 * Set the color of cell(rectangle)
	 */
	private void cellColor() {                              
		rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
	}


	/**
	 * Change the text inside the cell
	 * @param cell
	 */
	public void changeCell(Cell cell) {
		TextMaker.changeTwoText(textClass, cell.getTextClass());
		root.getChildren().remove(cell.getTextClass());
		root.getChildren().remove(textClass);

		if (!cell.getTextClass().getText().equals("0")) {
			root.getChildren().add(cell.getTextClass());
		}
		if (!textClass.getText().equals("0")) {
			root.getChildren().add(textClass);
		}
		setColorByNumber(getNumber());
		cell.setColorByNumber(cell.getNumber());
	}

	/**
	 * Add the texts(number) inside the cells
	 * @param cell
	 */
	public void adder(Cell cell) {
		cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
		textClass.setText("0");
		root.getChildren().remove(textClass);
		cell.setColorByNumber(cell.getNumber());
		setColorByNumber(getNumber());
	}

	/**
	 * Set different color of cell by different number
	 * @param number (indicates number written in the cell)
	 */
	public void setColorByNumber(int number) {
		switch (number) {
		case 0: fillColor(224, 226, 226, 0.5); break;
		case 2: fillColor(232, 255, 100, 0.5); break;
		case 4: fillColor(232, 220, 50, 0.5); break;
		case 8: fillColor(232, 200, 44, 0.8); break;
		case 16: fillColor(232, 170, 44, 0.8); break;
		case 32: fillColor(180, 120, 44, 0.7); break;
		case 64: fillColor(180, 100, 44, 0.7); break;
		case 128: fillColor(180, 80, 44, 0.7); break;
		case 256: fillColor(180, 60, 44, 0.8); break;
		case 512: fillColor(180, 30, 44, 0.8); break;
		case 1024: fillColor(250, 0, 44, 0.8); break;
		case 2048: fillColor(250,0,0,1);
		}
	}

	/**
	 * Fill the color of cell(rectangle)
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public void fillColor(int a, int b, int c, double d) {
		rectangle.setFill(Color.rgb(a, b, c, d));
	}

	/**
	 * Set the textClass
	 * @param textClass
	 */
	public void setTextClass(Text textClass) {
		this.textClass = textClass;
	}

	/**
	 * 
	 * @return X value of rectangle
	 */
	public double getX() {
		return rectangle.getX();
	}

	/**
	 * 
	 * @return Y value of rectangle
	 */
	public double getY() {
		return rectangle.getY();
	}

	/**
	 * @return Number 
	 */
	public int getNumber() {
		return Integer.parseInt(textClass.getText());
	}

	/**
	 * @return TextClass
	 */
	private Text getTextClass() {
		return textClass;
	}
}
