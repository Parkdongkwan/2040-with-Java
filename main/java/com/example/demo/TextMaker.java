package com.example.demo;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class TextMaker {
    private static TextMaker singleInstance = new TextMaker();   //instantiated
    private static double length = GameScene.getLENGTH();        
    private static double fontSize = (3 * length) / 7.0;

    private TextMaker() {         //This constructor is private so that this class cannot be instantiated.

    }

    static TextMaker getSingleInstance() {      //This method returns the object of this class
        return singleInstance;                  // return the object
    }

    Text madeText(String input, double xCell, double yCell, Group root) {      //This method write text( 2 or 4)  in the specified cell (Name changed)
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    static void changeTwoText(Text first, Text second) { //violates rule of single responsibility
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

    }

}
