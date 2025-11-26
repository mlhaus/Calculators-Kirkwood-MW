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


    public static void distanceUnitGreet() {
        displayMessage("Welcome to Robert's Distance Conversion Calculator");
        System.out.println("Enter calculations in the format: [value] [unit] to [unit]\n");
        System.out.println("Example Input: 1 mile to kilometers");
        System.out.println("Example Output: 1 mile = 1.60934 kilometers \n");
    }

    public static void distanceUnitGoodbye() {
        displayMessage("Thank you for using the Distance Conversion Calculator!");
    }

    public static void fractionGoodbye() {
        displayMessage("Thank you for using Marc's Fraction Calculator");
    }
    
    
}
