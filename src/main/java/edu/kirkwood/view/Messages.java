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
    public static void timeGreet() {
        displayMessage("Welcome to Jason's Time Calculator");
        System.out.println("Enter calculations in the format: [[number] [unit] or 'answer'] [operator] [number] [unit (optional)]");
        System.out.println("Example: 1 day + 1 hour\n");
    }

    public static void timeGoodbye() {
        displayMessage("Thank you for using Jason's Time Calculator");
    }
    
}
