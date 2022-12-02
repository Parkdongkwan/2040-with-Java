package com.example.textmaker;

import com.example.gamescene.GameScene;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * 
 * @author DongKwanPark-modified
 * This class make and modify the text and return it. 
 */
public class TextMaker {
	private static TextMaker singleInstance = null;
	private static double length = GameScene.getLENGTH();      
	private static double fontSize = (3 * length) / 7.0;

	/**
	 * Private Constructor
	 */
	private TextMaker() {}      

	/**
	 * This method return the object this class
	 * @return object
	 */
	public static TextMaker getSingleInstance() {
		if (singleInstance == null)
			singleInstance = new TextMaker();
		return singleInstance;
	}

	/**
	 * This method returns the text to specified location
	 * @param input
	 * @param xCell
	 * @param yCell
	 * @param root
	 * @return text
	 */
	public Text madeText(String input, double xCell, double yCell, Group root) {      
		Text text = new Text(input);                                           
		text.setFont(Font.font(fontSize));                             
		text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0)); 
		text.setFill(Color.WHITE);                                           

		return text;                                                              
	}

	/**
	 * This method change the two text
	 * @param first
	 * @param second
	 */
	public static void changeTwoText(Text first, Text second) { 
		String temp;
		temp = first.getText();
		first.setText(second.getText());
		second.setText(temp);

	}

}
