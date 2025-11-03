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

    /**
     * Displays a greeting message for the Depreciation Calculator.
     * Gives clear instructions to the user.
     */

    public static void depreciationGreet() {
        displayMessage("Welcome to the Straight-Line Depreciation Calculator!");
        System.out.println("This tool helps you calculate asset depreciation over time.");
        System.out.println("Please enter positive integer values for cost, salvage,");
        System.out.println("and useful life when prompted.\n"); // Added newline for better spacing
    }

    /**
     * Displays a goodbye message for the Depreciation Calculator.
     */
    public static void depreciationGoodbye() {
        displayMessage("Thank you for using the Depreciation Calculator. Goodbye!");
    }
}
