package edu.kirkwood.view;

import static edu.kirkwood.view.UIUtility.displayMessage;

public class Messages {

    public static void hello() {
        displayMessage("Welcome to the Kirkwood Calculators Application");
    }

    public static void goodbye() {
        displayMessage("Goodbye");
    }

    public static void fractionGreet() {
        displayMessage("Welcome to Marc's Fraction Calculator");
        System.out.println("Enter calculations in the format: [fraction] [operator] [fraction]");
        System.out.println("Example: 1 1/2 + 3/4\n");
    }

    public static void fractionGoodbye() {
        displayMessage("Thank you for using Marc's Fraction Calculator");
    }

    public static void ethanGreet() {
        displayMessage("Welcome to Ethan's EthansTemperature Calculator!");
        System.out.println("Enter a temperature value and scale (C, F, K).");
        System.out.println("You can then convert it to another scale.");
    }

    public static void ethanGoodbye(){
        displayMessage("Thank you for using Ethan's EthansTemperature Calculator. Goodbye!");
    }
}
