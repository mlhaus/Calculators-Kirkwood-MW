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
        System.out.println("Perform calculations with cooking ingredients");
        System.out.println("Supports volume (tsp, tbsp, cups, etc.) and weight (oz, lb, g, kg) measurements\n");
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

    public static void ryanTemperatureGreet() {
        UIUtility.displayMessage("Welcome to Ryan's Temperature Converter");
        System.out.println("Enter a whole number when prompted");
    }

    public static void ryanTemperatureGoodbye() {
        UIUtility.displayMessage("Thank you for using Ryan's Temperature Converter");
    }
  
    public static void characterCalcGreet(){
        displayMessage("Welcome to the character moveset Calculator!");
        System.out.println("Make sure that you type the option exactly as it is shown (just the word)");
        System.out.println("A move startup is how long it takes for the move to hit the opponent. Enter in a positive whole number.");
        System.out.println("A character status is one of the following (Stand, Crouch, downed).");
        System.out.println("A move type is the type of move the character will do which is one of the following (Command Grab, high, medium, low, throw.)");
        System.out.println("Enter calculations in the format: [Character1 MoveStartup], [Character1 ChacracterStatus]," +
                " [Character1 MoveType], [Character2 MoveStartup], [Character2 ChacracterStatus], [Character2 MoveType]");
    }

    public static void characterCalcGoodbye(){
        displayMessage("Thank you for using the character moveset Calculator");
    }
    public static void myCalculatorGreet() {
        displayMessage("Welcome to Yousif Money Calculator");
        System.out.println("Enter calculations in the format: [Money] [operator] [Money]");
        System.out.println("Example: 100USD + 10USD\n");
        System.out.println("Allowed Currencies: USD, GBP, CAD");
    }
    public static void myCalculatorGoodbye() {
        displayMessage("Thanks for join me in to my money Calculator");
    }
}
