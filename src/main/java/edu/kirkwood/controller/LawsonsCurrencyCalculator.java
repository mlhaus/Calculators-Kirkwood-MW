package edu.kirkwood.controller;

import edu.kirkwood.model.Currency;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

/**
 * A simple command-line based currency calculator that
 * parses and executes currency expressions like:
 *  "10 USD + 5 EUR", "15 EUR * 2 EUR", "20 GBP / 4 GBP"
 */
public class LawsonsCurrencyCalculator {
    public static void start() {
        currencyGreet();
        while (true) {
            String input = getString("Enter your currency expression (or 'q' to quit):");
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }

            String[] parts;
            try {
                parts = splitCalculation(input);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue;
            }

            String leftAmountStr = parts[0];
            String leftCurrencyStr = parts[1];
            String operator = parts[2];
            String rightAmountStr = parts[3];
            String rightCurrencyStr = parts[4];

            double leftAmount;
            double rightAmount = 0;

            try {
                leftAmount = Double.parseDouble(leftAmountStr);
                if (!operator.equalsIgnoreCase("to")) {
                    rightAmount = Double.parseDouble(rightAmountStr);
                }
            } catch (NumberFormatException e) {
                displayError("Invalid numeric values.");
                continue;
            }

            Currency currencyModel = new Currency();

            Double result;
            try {
                if (operator.equalsIgnoreCase("to")) {
                    currencyModel.setAmount(leftAmount);
                    currencyModel.setCurrency(leftCurrencyStr);
                    result = currencyModel.convert(rightCurrencyStr);
                    System.out.printf("Result: %.2f %s = %.2f %s%n%n",
                            leftAmount, leftCurrencyStr, result, rightCurrencyStr);
                    continue;
                }

                switch (operator) {
                    case "+":
                        result = currencyModel.addDifferentCurrencies(leftAmount, leftCurrencyStr, rightAmount, rightCurrencyStr);
                        break;
                    case "-":
                        result = currencyModel.subDifferentCurrencies(leftAmount, leftCurrencyStr, rightAmount, rightCurrencyStr);
                        break;
                    case "*":
                        if (!leftCurrencyStr.equalsIgnoreCase(rightCurrencyStr)) {
                            displayError("For multiplication, both operands must use the same currency.");
                            continue;
                        }
                        result = leftAmount * rightAmount;
                        result = currencyModel.simplify(result);
                        break;
                    case "/":
                        if (rightAmount == 0) {
                            displayError("Division by zero is not allowed.");
                            continue;
                        }
                        if (!leftCurrencyStr.equalsIgnoreCase(rightCurrencyStr)) {
                            displayError("For division, both operands must use the same currency.");
                            continue;
                        }
                        result = leftAmount / rightAmount;
                        result = currencyModel.simplify(result);
                        break;
                    default:
                        displayError("Invalid operator. Use +, -, *, /, or 'to'.");
                        continue;
                }
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue;
            }

            System.out.printf("Result: %.2f %s %s %.2f %s = %.2f %s%n%n",
                    leftAmount, leftCurrencyStr, operator, rightAmount, rightCurrencyStr, result, leftCurrencyStr);
        }

        currencyGoodbye();
        pressEnterToContinue();
    }

    /**
     * Splits the user input string into five parts:
     * amount1, currency1, operator, amount2, currency2
     *
     * @param input the raw input string from the user
     * @return a String array with 5 elements
     * @throws IllegalArgumentException if the format is invalid
     */
    public static String[] splitCalculation(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        String[] tokens = input.split(" ");
        if (tokens.length != 5 && tokens.length != 4) {
            throw new IllegalArgumentException("Invalid input. Expected format: '<amount1> <currency1> <operator> <amount2> <currency2>' or '<amount1> <currency1> to <currency2>'");
        }
        if (tokens.length == 4) {
            String amount1 = tokens[0];
            String currency1 = tokens[1].toUpperCase();
            String operator = tokens[2];
            String currency2 = tokens[3].toUpperCase();
            if (!operator.equalsIgnoreCase("to")) {
                throw new IllegalArgumentException("Invalid operator: '" + operator + "'. Use +, -, *, /, or 'to'.");
            }
            return new String[]{amount1, currency1, "to", "0", currency2};
        }

        String amount1 = tokens[0];
        String currency1 = tokens[1].toUpperCase();
        String operator = tokens[2];
        String amount2 = tokens[3];
        String currency2 = tokens[4].toUpperCase();
        if (!operator.matches("[+\\-*/]") && !operator.equalsIgnoreCase("to")) {
            throw new IllegalArgumentException("Invalid operator: '" + operator + "'. Use +, -, *, /, or 'to'.");
        }
        return new String[]{amount1, currency1, operator, amount2, currency2};
    }
}


