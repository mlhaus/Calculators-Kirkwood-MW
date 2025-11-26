/**
 * @file        DistanceUnitCalculator.java
 * @author      Robert Biggart
 * @date        2025/09/26
 * @description This file contains the control logic for the distance unit calculator. It follows a similar layout to
 * what you showed in class as you requested. And so hopefully it implements into the bigger app well!
 * MODIFICATION HISTORY:
 *  2025/09/2
 */
package edu.kirkwood.controller;


import static edu.kirkwood.view.Messages.*;

import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

import edu.kirkwood.model.DistanceUnit;


/**
 * Starts the Distance Unit Calculator
 */
public class DistanceUnitCalculator {
    public static void start() {
        distanceUnitGreet();
        while (true) {
            String input = getString("Enter a value and unit type, followed by the unit type to convert to.");

            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }
            // Parse and calculate
            double value = 0;

            // Trys to parse the units and calculate the new value
            try {
                // Splits up the string into and array
                String[] parts = splitConversion(input);

                // Parses each section individually
                value = Double.parseDouble(parts[0]);
                DistanceUnit fromUnit =  DistanceUnit.parseUnit(parts[1]);
                DistanceUnit toUnit =  DistanceUnit.parseUnit(parts[2]);


                // REFACTOR LATER

                // Print result with proper singular/plural
                double result = DistanceUnit.convert(value, fromUnit, toUnit);

                System.out.println(fromUnit.formatValue(value) + " = " + toUnit.formatValue(result));

            } catch (NumberFormatException e1) {
                throw new IllegalArgumentException("Invalid distance value of '" + value + "'.\n " +
                        "Please enter a numeric value followed by the unit type.");
            } catch (Exception e2) {
                displayError("Unexpected error: " + e2.getMessage());
            }
        }

        // Displays the goodbye message and waits for the user to press enter
        distanceUnitGoodbye();
        pressEnterToContinue();
    }

    /**
     * Splits the input string into a String array containing separated as such [value, currentUnit, targetUnit]
     * @param input The string input from the user
     * @return A anonymous string array
     * @throws IllegalArgumentException If the string is missing, empty, or is invalid
     */
    public static String[] splitConversion(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Input: Input cannot be empty.");
        }

        // Throws an error if the input string does not contain the substring " to "
        if (!input.toLowerCase().contains(" to ")) {
            throw new IllegalArgumentException("Invalid Input: Must contain  'to ' (with spaces) to separate the current unit and the target unit.");
        }

        // Splits the input string into its main parts on the substring " to "
        String[] mainParts = input.split(" to ");

        // Throws an error if the split fails to produce two elements
        if (mainParts.length != 2) {
            throw new IllegalArgumentException("Invalid Input: You must include a value, the current unit, and also the target unit.");
        }

        String currentUnitAndValue = mainParts[0].trim();  // "5 miles" "1 inch"
        String targetUnit = mainParts[1].trim();           // "kilometer", "foot"

        // Splits the string containing the unit
        String[]  currentUnitAndValueParts = currentUnitAndValue.split(" ");

        // Throws an error if the split fails to produce two elements
        if ( currentUnitAndValueParts.length != 2) {
            throw new IllegalArgumentException("Invalid Input: You must include both the value and the current unit.");
        }

        String value =  currentUnitAndValueParts[0];
        String currentUnit =  currentUnitAndValueParts[1];

        // Returns a string array containing the parts of the conversion
        return new String[] {value, currentUnit, targetUnit};
    }

}