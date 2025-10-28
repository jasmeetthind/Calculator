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

    /* All the button labels that will appear on the calculator*/
    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };

    /* These are the operator buttons that go on the right side*/
    String[] rightSymbols = {"÷", "x", "-", "+", "="};

    /*These are the buttons that go on the top row*/
    String[] topSymbols = {"AC", "+/-", "%"};

    /*Create a main application window (JFrame)*/
    JFrame frame = new JFrame("Calculator");
    /*Create a label to display numbers and results*/
    JLabel displayLabel = new JLabel();
    /*Create a panel to hold the display label*/
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    /* Store the first number entered by the user */
    String A = "0";
    /* Store the selected math operation (+, -, x, ÷) */
    String operator = null;
    /* Store the second number entered by the user */
    String B = null;

    /*constructor*/
    Calculator() {
        /*so we can see the window*/
        frame.setVisible(true);
        /* Set the window size*/
        frame.setSize(boardWidth, boardHeight);
        /* this will center the window so it will appear in the center of the screen*/
        frame.setLocationRelativeTo(null);
        /*so now can change the size of the window*/
        frame.setResizable(false);
        /*when users presses 'x', program will terminate*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*so we can place components north, south, east and west*/
        frame.setLayout(new BorderLayout());

        /* ---------- DISPLAY SETUP (WHERE NUMBERS SHOW UP) ---------- */
        displayLabel.setBackground(Color.BLACK); /* Background color of the display */
        displayLabel.setForeground(Color.white); /* Text color (white text) */
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80)); /* Font style and size */
        displayLabel.setHorizontalAlignment(JLabel.RIGHT); /* Align text to the right side */
        displayLabel.setText("0"); /* Start with 0 on the display */
        displayLabel.setOpaque(true); /* Make sure the background color shows up */

        /* Set up the panel that holds the display label */
        displayPanel.setLayout(new BorderLayout()); /* Use BorderLayout for consistent alignment */
        displayPanel.add(displayLabel); /* Add the display label to the panel */
        /* Add the display panel to the top (NORTH) of the main frame */
        frame.add(displayPanel, BorderLayout.NORTH);

        /* ---------- BUTTONS SETUP ---------- */
        buttonsPanel.setLayout(new GridLayout(5,4)); // Create a 5x4 grid of buttons
        buttonsPanel.setBackground(customBlack); // Background behind buttons
        frame.add(buttonsPanel); // Add button panel to the frame

        // Loop through all button labels and create buttons
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30)); // Button text style
            button.setText(buttonValue); // Set the text of the button
            button.setBorder(new LineBorder(customBlack)); // Add a border around each button

            /* Make button backgrounds visible on macOS */
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setFocusPainted(false);
            button.setContentAreaFilled(true);

            if(Arrays.asList(topSymbols).contains(buttonValue)) {
                // Top row buttons: AC, +/-, %
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)) {
                // Right-side operation buttons: ÷, x, -, +, =
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);
            }
            else {
                // Number and decimal buttons
                button.setBackground(customDarkGray);
                button.setForeground(Color.WHITE);
            }
            // Add button to the grid
            buttonsPanel.add(button);

            /* ---------- BUTTON CLICK ACTION ---------- */
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Get which button was clicked
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    /* ---------- IF USER PRESSES AN OPERATOR ---------- */
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        // When user presses "="
                        if (buttonValue == "="){
                            if (A != null) {
                                // Store the second number
                                B = displayLabel.getText();

                                // Convert stored strings to numbers
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                // Perform the correct calculation based on the operator
                                if (operator == "+") {
                                    displayLabel.setText(removeZeroDecimal(numA + numB));
                                }
                                else if (operator == "-") {
                                    displayLabel.setText(removeZeroDecimal(numA - numB));
                                }
                                else if (operator == "x") {
                                    displayLabel.setText(removeZeroDecimal(numA * numB));
                                }
                                else if (operator == "÷") {
                                    displayLabel.setText(removeZeroDecimal(numA / numB));
                                }
                                // Reset variables for the next operation
                                clearAll();
                            }
                        }
                        // When user presses one of "+ - x ÷"
                        else if ("+-x÷".contains(buttonValue)) {
                            // Only set operator if it hasn’t been chosen yet
                            if (operator == null) {
                                A = displayLabel.getText(); // Store the first number
                                displayLabel.setText("0"); // Reset display for the next number
                                B = "0"; // Reset second number
                            }
                            operator = buttonValue; // Store the operator
                        }
                    }
                    /* ---------- IF USER PRESSES TOP BUTTONS ---------- */
                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        // Clear button
                        if (buttonValue == "AC"){
                            clearAll();
                            displayLabel.setText("0");
                        }
                        // Change sign (+/-)
                        else if (buttonValue == "+/-"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1; // Flip sign
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                        // Percentage button (%)
                        else if (buttonValue == "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100; // Convert to percent
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }
                    /* ---------- IF USER PRESSES A NUMBER OR DECIMAL ---------- */
                    else { // digit or .
                        // If user presses ".", only add it once
                        if (buttonValue == ".") {
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                        // If user presses a number
                        else if ("0123456789".contains(buttonValue)) {
                            // If display currently shows 0, replace it
                            if (displayLabel.getText() == "0"){
                                displayLabel.setText(buttonValue);
                            }
                            // Otherwise, add the number to the end
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
            }
        }

        /* ---------- HELPER METHODS ---------- */

        // Reset all calculator values
        void clearAll() {
        A = "0";
        operator = null;
        B = null;
        }

        // Remove .0 from whole numbers (e.g., 5.0 → 5)
        String removeZeroDecimal(double numDisplay) {
            if (numDisplay % 1 == 0) {
                return Integer.toString((int) numDisplay);
            }
            return Double.toString(numDisplay);
        }
    }
