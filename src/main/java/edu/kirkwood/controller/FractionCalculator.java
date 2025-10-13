package edu.kirkwood.controller;

import edu.kirkwood.model.Fraction;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class FractionCalculator {
    public static void start() {
        fractionGreet();
        while(true) {
            String input = getString("Enter your equation (or 'q' to quit)");
            if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }
            // Validate the input
            String[] parts = null;
            try {
                parts = splitCalculation(input);
            } catch(IllegalArgumentException e) {
                displayError(e.getMessage());
                continue; // restart the loop
            }

            String fraction1Str = parts[0];
            String operator = parts[1];
            String fraction2Str = parts[2];

            Fraction fraction1 = null;
            Fraction fraction2 = null;
            try {
                fraction1 = parseFraction(fraction1Str);
                fraction2 = parseFraction(fraction2Str);
            } catch(IllegalArgumentException e) {
                displayError(e.getMessage());
                continue; // restart the loop
            }

            // Perform mathematical operations
            Fraction result = null;
            switch (operator) {
                case "+":
                    result = fraction1.add(fraction2);
                    break;
                case "-":
                    result = fraction1.subtract(fraction2);
                    break;
                case "*":
                    result = fraction1.multiply(fraction2);
                    break;
                case "/":
                    try {
                        result = fraction1.divide(fraction2);
                    } catch(ArithmeticException e) {
                        displayError(e.getMessage());
                        continue; // restart the loop
                    }
                    break;
            }

            // Display output
            System.out.printf("%s %s %s = %s%n%n",
                    fraction1.toMixedNumber(), operator,  fraction2.toMixedNumber(), result.toMixedNumber());
        }
        fractionGoodbye();
        pressEnterToContinue();
    }

    /**
     * Splits the user input string into three parts: fraction 1, operator, fraction 2
     * @param input the raw input string from the user
     * @return a String[] array of size 3
     * @throws IllegalArgumentException if the input format or operator is invalid
     */
    public static String[] splitCalculation(String input) {
        String operator = "";
        int operatorIndex = -1;

        if(input.contains(" + ")) {
            operator = "+";
            operatorIndex = input.indexOf(" + ");
        } else if(input.contains(" - ")) {
            operator = "-";
            operatorIndex = input.indexOf(" - ");
        } else if(input.contains(" * ")) {
            operator = "*";
            operatorIndex = input.indexOf(" * ");
        } else if(input.contains(" / ")) {
            operator = "/";
            operatorIndex = input.indexOf(" / ");
        }

        if(operatorIndex == -1) {
            throw new IllegalArgumentException("Invalid format. Ensure operator (+, -, *, /) has a space on both sides.");
        }

        String fraction1 = input.substring(0, operatorIndex).trim();
        String fraction2 = input.substring(operatorIndex + 3).trim();

        if(fraction1.isEmpty()) {
            throw new IllegalArgumentException("Missing fraction 1.");
        }

        if(fraction2.isEmpty()) {
            throw new IllegalArgumentException("Missing fraction 2.");
        }

        return new String[]{fraction1, operator, fraction2};
    }

    /**
     * Convert a String into a Fraction object. Handles whole numbers, proper and improper fractions, and mixed number fractions
     * @param str is the String to parse
     * @return a Fraction object representing the parsed String
     * @throws IllegalArgumentException if the String is not a valid fraction
     */
    public static Fraction parseFraction(String str) {
        Fraction result = null;
        if(str.contains(" ")) { // Mixed number fraction like "1 1/2"
            String[] parts = str.split(" ");
            int whole = 0;
            try {
                whole = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid whole number. '" + parts[0] + "' (the correct format is '1 2/3')");
            }

            String[] fractionParts = parts[1].split("/");

            int num = 0;
            try {
                num = Integer.parseInt(fractionParts[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid numerator. '" + fractionParts[0] + "' (the correct format is '1 2/3')");
            }

            int den = 1;
            if(fractionParts.length > 1) {
                try {
                    den = Integer.parseInt(fractionParts[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid denominator. '" + fractionParts[1] + "' (the correct format is '1 2/3')");
                }
            } else {
                throw new IllegalArgumentException("Invalid denominator (the correct format is '1 2/3')");
            }

            if(den == 0) {
                throw new IllegalArgumentException("Invalid denominator. '" + fractionParts[1] + "' (the correct format is '1 2/3')");
            }

            // If the fraction part is negative, flip negativity as needed
            if(num < 0 && den < 0) {
                num *= -1;
                den *= -1;
            } else if (num < 0 && den > 0) {
                num *= -1;
                whole *= -1;
            } else if (num > 0 && den < 0) {
                den *= -1;
                whole *= -1;
            }

            int newNumerator = 0;
            if(whole > 0) {
                newNumerator = whole * den + num;
            } else {
                newNumerator = whole * den - num;
            }
            result = new Fraction(newNumerator, den);

        } else if(str.contains("/")) { // Proper or improper fraction like "1/2" or "3/2"
            String[] parts = str.split("/");

            int num = 0;
            try {
                num = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid numerator. '" + parts[0] + "'");
            }

            int den = 0;
            try {
                den = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid denominator. '" + parts[1] + "'");
            }

            if(den == 0) {
                throw new IllegalArgumentException("Invalid denominator. '" + parts[1] + "'");
            }

            result = new Fraction(num, den);

        } else { // Whole numbers
            int whole = 0;
            try {
                whole = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid whole number. '" + str + "'");
            }
            result = new Fraction(whole, 1);
        }
        return result;
    }







}