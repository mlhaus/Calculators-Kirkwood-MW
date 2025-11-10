package edu.kirkwood.controller;

import edu.kirkwood.model.Depreciation;
import edu.kirkwood.model.Fraction;

import static edu.kirkwood.view.Messages.depreciationGoodbye;
import static edu.kirkwood.view.Messages.depreciationGreet;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getDouble;
import static edu.kirkwood.view.UserInput.getInt;

/**
 * Controller for the Straight-Line Depreciation Calculator.
 * Handles user interaction, input validation, calls the Depreciation model for calculations,
 * and displays results to the user.
 */
public class DepreciationCalculator {

    /**
     * Starts the straight-line depreciation calculator application.
     * Prompts the user for asset details, performs calculations, and allows
     * the user to explore different depreciation figures.
     */
    public static void start() {
        depreciationGreet(); // Display the greeting message from view.Messages

        Depreciation assetDepreciation = null;
        double assetCost = 0;
        double salvageValue = 0;
        int usefulLife = 0;

        // Loop to get valid initial asset inputs
        while (assetDepreciation == null) {
            try {
                assetCost = getDouble("Enter the Asset Cost (e.g., 100000)", true, 0, Integer.MAX_VALUE);
                // Prompt for salvage value, ensuring it's not greater than asset cost
                salvageValue = getDouble("Enter the Salvage Value (e.g., 10000)", true, 0, assetCost);
                // Prompt for useful life, ensuring it's a positive number
                usefulLife = getInt("Enter the Useful Life in years (e.g., 5)", true, 1, Integer.MAX_VALUE);

                assetDepreciation = new Depreciation(assetCost, salvageValue, usefulLife);
            } catch (IllegalArgumentException e) {
                displayError("Input Error: " + e.getMessage());
                System.out.println("Please re-enter the asset details.");
                pressEnterToContinue();
            }
        }
//        public static double getDouble(String prompt, boolean allowNegative,
//        double min, double max) {
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.print(prompt + ": ");
//                try {
//                    double value = scanner.nextDouble();
//                    if (value < min || value > max) {
//                        System.out.println("Value must be between " + min + " and " + max);
//                        continue;
//                    }
//                    if (!allowNegative && value < 0) {
//                        System.out.println("Value cannot be negative");
//                        continue;
//                    }
//                    return value;
//                } catch (InputMismatchException e) {
//                    System.out.println("ERROR: Please enter a valid numeric value");
//                    scanner.nextLine(); // Clear the invalid input
//                }
//            }
//        }

        System.out.println("\n--- Asset Details ---");
        System.out.println("Asset Cost: " + assetDepreciation.getAssetCost().toMixedNumber());
        System.out.println("Salvage Value: " + assetDepreciation.getSalvageValue().toMixedNumber());
        System.out.println("Useful Life: " + assetDepreciation.getUsefulLife() + " years");

        // Main menu for depreciation calculations
        String[] calculationMenuItems = {
                "Show Annual Depreciation",
                "Calculate Accumulated Depreciation for a specific year",
                "Calculate Item Value for a specific year",
                "Back to Main Menu"
        };

        while(true) {
            printMenu("Depreciation Calculations", calculationMenuItems);
            int choice = getInt("Choose an option", false, 1, calculationMenuItems.length);

            try {
                switch (choice) {
                    case 1:
                        Fraction annualDepreciation = assetDepreciation.calculateAnnualDepreciation();
                        System.out.println("\nAnnual Depreciation: " + annualDepreciation.toMixedNumber());
                        System.out.println("Decimal equivalent: " + String.format("%.2f", annualDepreciation.getNumerator() / (double) annualDepreciation.getDenominator()));
                        break;
                    case 2:
                        int yearsForAccumulated = getInt("Enter the number of years for accumulated depreciation (0 to " + usefulLife + ")", false, 0, usefulLife);
                        Fraction accumulatedDepreciation = assetDepreciation.calculateAccumulatedDepreciation(yearsForAccumulated);
                        System.out.println("\nAccumulated Depreciation after " + yearsForAccumulated + " years: " + accumulatedDepreciation.toMixedNumber());
                        System.out.println("Decimal equivalent: " + String.format("%.2f", accumulatedDepreciation.getNumerator() / (double) accumulatedDepreciation.getDenominator()));
                        break;
                    case 3:
                        int yearsForItemValue = getInt("Enter the number of years for item value (0 to " + usefulLife + ")", false, 0, usefulLife);
                        Fraction itemValue = assetDepreciation.calculateItemValue(yearsForItemValue);
                        System.out.println("\nitem Value after " + yearsForItemValue + " years: " + itemValue.toMixedNumber());
                        System.out.println("Decimal equivalent: " + String.format("%.2f", itemValue.getNumerator() / (double) itemValue.getDenominator()));
                        break;
                    case 4:
                        depreciationGoodbye(); // Display goodbye message
                        pressEnterToContinue();
                        return; // Exit depreciation calculator
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                displayError("Calculation Error: " + e.getMessage());
            }
            pressEnterToContinue(); // Pause before next menu display
        }
    }
}