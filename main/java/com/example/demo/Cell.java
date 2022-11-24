package com.example.demo;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;


    /**
     * Set the boolean value modify
     * @param modify
     */
    void setModify(boolean modify) {       
        this.modify = modify;
    }


    /**
     * return the boolean value modify
     * @return
     */
    boolean getModify() {
        return modify;
    }
    
    /**
     * Constructor for Cell class
     * @param x
     * @param y
     * @param scale
     * @param root
     */
    Cell(double x, double y, double scale, Group root) {  
        rectangle = new Rectangle();
        cellLocation(x, y);                               
        cellFrame(scale);                                 
        cellColor();
        cellText(x, y, root);
        
        this.root = root;
        root.getChildren().add(rectangle);
    }

    /**
     * Change the cell
     * @param cell
     */
    void changeCell(Cell cell) {
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
     * Add the two cell with number inside in it
     * @param cell
     */
    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * Set the color of cell by number
     * @param number
     */
    void setColorByNumber(int number) {
        switch (number) {
            case 0:
                rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
                break;
            case 2:
                rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
                break;
            case 4:
                rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
                break;
            case 8:
                rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
                break;
            case 16:
                rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
                break;
            case 32:
                rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
                break;
            case 64:
                rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
                break;
            case 128:
                rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
                break;
            case 256:
                rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
                break;
            case 512:
                rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
                break;
            case 1024:
                rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
                break;
            case 2048:
                rectangle.setFill(Color.rgb(250,0,0,1));


        }

    }
    
	private void cellLocation(double x, double y) {         //Set the cell location
		rectangle.setX(x);
        rectangle.setY(y);
	}

	private void cellFrame(double scale) {                   //Set the size of cell
		rectangle.setHeight(scale);
        rectangle.setWidth(scale);
	}

	private void cellText(double x, double y, Group root) {   //set the text in cell
		this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
	}

	private void cellColor() {                               //Set the color of cell
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
	}

    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    double getX() {
        return rectangle.getX();
    }

    double getY() {
        return rectangle.getY();
    }

    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    private Text getTextClass() {
        return textClass;
    }





}
