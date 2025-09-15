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
}
