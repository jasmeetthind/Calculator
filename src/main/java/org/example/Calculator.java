package org.example;
/* grahics library and event handling */
import java.awt.*;
import java.awt.event.*;
/* working with arrays to list out the buttons*/
import java.util.Arrays;
/* modifying the borders of the button on the calculator*/
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    JFrame frame = new JFrame("Calculator");

    /*constructor*/
    Calculator() {
        /*so we can see the window*/
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        /* this will center the window so it will appear in the center of the screen*/
        frame.setLocationRelativeTo(null);
        /*so now can change the size of the window*/
        frame.setResizable(false);
        /*when users presses 'x', program will terminate*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*so we can place components north, south, east and west*/
        frame.setLayout(new BorderLayout());
    }

}
