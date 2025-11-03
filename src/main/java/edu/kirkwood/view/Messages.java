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

    public static void ingredientGreet() {
        displayMessage("Welcome to Edward's Ingredient Calculator");
        System.out.println("Perform arithmetic operations on cooking ingredients with automatic unit conversions");
        System.out.println("Supports volume units: tsp, tbsp, fl oz, cup, pint, quart");
        System.out.println("Supports weight units: oz, lb, g, kg");
    }

    public static void ingredientGoodbye() {
        displayMessage("Thank you for using Edward's Ingredient Calculator");
    }
}
