package org.example;
/* graphics library and event handling */
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

    /* Define custom colors for the calculator UI*/
    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    /*Create a main application window (JFrame)*/
    JFrame frame = new JFrame("Calculator");
    /*Create a label to display numbers and results*/
    JLabel displayLabel = new JLabel();
    /*Create a panel to hold the display label*/
    JPanel displayPanel = new JPanel();

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

        displayLabel.setBackground(Color.BLACK);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);
    }

}
