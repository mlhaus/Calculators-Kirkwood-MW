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

    public static void currencyGreet() {
        displayMessage("Welcome to Currency Calculator.");
        System.out.println("Enter calculations in the format: <amount1> <currency1> <operator> <amount2> <currency2>" + "\n or in <amount> <currency1> to <currency2>");
        System.out.println("Example: 5.00 USD");
        System.out.println("The system only accepts 3 digit currency codes, only supported currencies are USD, JPY, GBP, and EUR");
    }

    public static void currencyGoodbye() {
        displayMessage("Thank you for using Currency Calculator");
    }
}
