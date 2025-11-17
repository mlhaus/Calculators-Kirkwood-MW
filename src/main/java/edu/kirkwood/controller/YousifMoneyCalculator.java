package edu.kirkwood.controller;

import edu.kirkwood.model.Fraction;
import edu.kirkwood.model.Money;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

/**
 * this class is for Yousif Omer money calculator app
 */
public class YousifMoneyCalculator {
    /**
     * this message is to start the calculator
     * @throws Exception
     */
    public static void start() {
        myCalculatorGreet();
        while (true) {
            String input = getString("Enter your equation (or 'q' to quit)");
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }
            // Validate the input
            String[] parts = null;
            try {
                parts = splitCalculation(input);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue; // restart the loop
            }

            String money1Str = parts[0];
            String operator = parts[1];
            String money2Str = parts[2];

            Money money1 = null;
            Money money2 = null;
            try {
                money1 = parseMoney(money1Str);
                money2 = parseMoney(money2Str);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue; // restart the loop
            }

            // Perform mathematical operations

            switch (operator) {
                case "+":
                    money1.add(money2);
                    break;
                case "-":
                    money1.subtract(money2);
                    break;
                case "*":
                    money1.multiply(money2);
                    break;
                case "/":
                    try {
                        money1.divide(money2);
                    } catch (ArithmeticException e) {
                        displayError(e.getMessage());
                        continue; // restart the loop
                    }
                    break;
            }
            System.out.println(money1.toString());
            myCalculatorGoodbye();
            pressEnterToContinue();
        }
    }

    /**
     * this message is to parse money string
     *
     * @param moneyStr the input string containing amount and currency
     * @return money object with parsed amount and currency
     * @throws Exception
     */
    public static Money parseMoney(String moneyStr) {
        try {
            String currency = moneyStr.substring(moneyStr.length() - 3);
            String amountStr = moneyStr.substring(0, moneyStr.length() - 3);
            Money money = new Money(Double.parseDouble(amountStr), currency);
            return money;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid format. could not recognize money. try again");
        }


    }

    /**
     * this message is to split string input
     *
     * @param input
     * @return string array
     */
    public static String[] splitCalculation(String input) {
        String operator = "";
        int operatorIndex = -1;

        if (input.contains(" + ")) {
            operator = "+";
            operatorIndex = input.indexOf(" + ");
        } else if (input.contains(" - ")) {
            operator = "-";
            operatorIndex = input.indexOf(" - ");
        } else if (input.contains(" * ")) {
            operator = "*";
            operatorIndex = input.indexOf(" * ");
        } else if (input.contains(" / ")) {
            operator = "/";
            operatorIndex = input.indexOf(" / ");
        }

        if (operatorIndex == -1) {
            throw new IllegalArgumentException("Invalid format. Ensure operator (+, -, *, /) has a space on both sides.");
        }

        String money1 = input.substring(0, operatorIndex).trim();
        String money2 = input.substring(operatorIndex + 3).trim();

        if (money1.isEmpty()) {
            throw new IllegalArgumentException("Missing money 1.");
        }

        if (money2.isEmpty()) {
            throw new IllegalArgumentException("Missing money 2.");
        }

        return new String[]{money1, operator, money2};
    }

}