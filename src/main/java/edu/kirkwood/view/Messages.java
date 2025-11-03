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

    public static void investmentGreet() {
        displayMessage("Welcome to Lizbeth's Investment Calculator");
        // You'll need to update the instructions for how the user interacts with the investment calculator
        System.out.println("Enter the values in format of [Amount invested (in stocks and bonds)] " +
                ",[Stock Rate], [Bond Rate], [Annual income]).");
        System.out.println("Example: 50000, 6, 2 ,1892 "); // This should return 22,300 in bonds and 27,700 in stocks
    }

    public static void investmentGoodbye() {
        displayMessage("Thank you for using Lizbeth's Investment Calculator");
    }
  
    /**
     * Displays a greeting message for the Depreciation Calculator.
     * Gives clear instructions to the user.
     */
    public static void depreciationGreet() {
        displayMessage("Welcome to the Straight-Line Depreciation Calculator!!");
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
  
    public static void setGreet() {
        displayMessage("Welcome to Blake's Set Calculator!");
    }

    public static void setGoodbye() {
        displayMessage("Thank you for using Blake's Set Calculator!");
    }
  
    public static void unitCircleGreet() {
        displayMessage("Welcome to ZeeKonCal's Unit Circle Calculator");
    }
    
    public static void unitCircleGoodbye() {
        displayMessage("Thank you for using ZeeKonCal's Unit Circle Calculator");
    }
  
    public static void timeGreet() {
        displayMessage("Welcome to Jason's Time Calculator");
        System.out.println("Enter calculations in the format: [[number] [unit] or 'answer'] [operator] [number] [unit (optional)]");
        System.out.println("Example: 1 day + 1 hour\n");
    }

    public static void timeGoodbye() {
        displayMessage("Thank you for using Jason's Time Calculator");
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
  
    public static void ethanGreet() {
        displayMessage("Welcome to Ethan's EthansTemperature Calculator!");
        System.out.println("Enter a temperature value and scale (C, F, K).");
        System.out.println("You can then convert it to another scale.");
    }

    public static void ethanGoodbye(){
        displayMessage("Thank you for using Ethan's EthansTemperature Calculator. Goodbye!");
    }
}
