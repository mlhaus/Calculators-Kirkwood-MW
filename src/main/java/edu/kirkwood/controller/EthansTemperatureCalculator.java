package edu.kirkwood.controller;

import edu.kirkwood.model.EthansTemperature;
import edu.kirkwood.view.Messages;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

import static edu.kirkwood.view.UIUtility.pressEnterToContinue;

/**
 * A temperature calculator that converts between Celsius, Fahrenheit, and Kelvin.
 */
public class EthansTemperatureCalculator {

    /**
     * Starts the EthansTemperature Calculator. Presents a menu to the user to pick a conversion,
     * reads the value and source scale, performs the conversion using,
     * and prints a single result. Loops until the user chooses to quit.
     */
    public static void start() {
        Messages.ethanGreet();
        EthansTemperature temp = new EthansTemperature(10,"K");
        while (true) {
            System.out.println("\n--- EthansTemperature Calculator Menu ---");
            System.out.println("1. Convert to Celsius");
            System.out.println("2. Convert to Fahrenheit");
            System.out.println("3. Convert to Kelvin");
            System.out.println("q. Quit");

            String choice = UserInput.getString("Choose an option (1-3 or q to quit): ").trim();

            if (choice.equalsIgnoreCase("q") || choice.equalsIgnoreCase("quit")) {
                break;
            }

            /* Read numeric value as string and parse to double */
            String valueStr = "";
            try {
                valueStr = UserInput.getString("Enter temperature value: ").trim();
                temp.setValue(valueStr);
            } catch (NumberFormatException nfe) {
                UIUtility.displayError("Invalid number. Please enter a numeric temperature value (e.g. 98.6).");
                pressEnterToContinue(); // back to menu
                continue;
            } catch (ArithmeticException ae) {
                UIUtility.displayError("Invalid EthansTemperature. Please enter a temperature higher than absolute zero.");
                pressEnterToContinue();
                continue;
            }


            String scale = "";
            try {
                scale = UserInput.getString("Enter scale of the input value (C, F, K): ").trim().toUpperCase();
                temp.setScale(scale);
            } catch (IllegalArgumentException iae) {
                UIUtility.displayError(iae.getMessage());
                pressEnterToContinue(); // back to menu
                continue;
            }

            switch (choice) {
                case "1":
                    System.out.printf("%s %s = %.1f C%n%n", valueStr, scale, temp.toCelsius());
                    pressEnterToContinue();
                    break;
                case "2":
                    System.out.printf("%s %s = %.1f F%n%n", valueStr, scale, temp.toFahrenheit());
                    pressEnterToContinue();
                    break;
                case "3":
                    System.out.printf("%s %s = %.1f K%n%n", valueStr, scale, temp.toKelvin());
                    pressEnterToContinue();
                    break;
                default:
                    UIUtility.displayError("Invalid choice. Enter 1, 2, 3, or q.");
                    pressEnterToContinue();
                    break;
            }
        }

        Messages.ethanGoodbye();
        pressEnterToContinue();
    }
}
