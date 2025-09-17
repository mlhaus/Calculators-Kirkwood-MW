package edu.kirkwood.controller;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class FractionCalculator {
    public static void start() {
        fractionGreet();
        while(true) {
            String value = getString("Enter your equation (or 'q' to quit)");
            if(value.equalsIgnoreCase("q") || value.equalsIgnoreCase("quit")) {
                break;
            }
            // Todo: Validate the input
            // Todo: Perform mathematical operations
            // Todo: Display output
        }
        fractionGoodbye();
        pressEnterToContinue();
    }

    /**
     * Splits the user input string into three parts: fraction 1, operator, fraction 2
     * @param input the raw input string from the user
     * @return a String[] array of size 3
     * @throws IllegalArgumentException if the input format or operator is invalid
     */
    public static String[] splitCalculation(String input) {
        String operator = "";
        int operatorIndex = -1;

        if(input.contains(" + ")) {
            operator = "+";
            operatorIndex = input.indexOf(" + ");
        } else if(input.contains(" - ")) {
            operator = "-";
            operatorIndex = input.indexOf(" - ");
        } else if(input.contains(" * ")) {
            operator = "*";
            operatorIndex = input.indexOf(" * ");
        } else if(input.contains(" / ")) {
            operator = "/";
            operatorIndex = input.indexOf(" / ");
        }

        if(operatorIndex == -1) {
            throw new IllegalArgumentException("Invalid format. Ensure operator (+, -, *, /) has a space on both sides.");
        }

        String fraction1 = input.substring(0, operatorIndex).trim();
        String fraction2 = input.substring(operatorIndex + 3).trim();
        
        if(fraction1.isEmpty()) {
            throw new IllegalArgumentException("Missing fraction 1.");
        }

        if(fraction2.isEmpty()) {
            throw new IllegalArgumentException("Missing fraction 2.");
        }
        
        return new String[]{fraction1, operator, fraction2};
    }









}
