package edu.kirkwood.controller;

import edu.kirkwood.model.RyanTemperature;

import java.util.InputMismatchException;
import java.util.Scanner;

import static edu.kirkwood.view.Messages.ryanTemperatureGoodbye;
import static edu.kirkwood.view.Messages.ryanTemperatureGreet;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;

public class RyanTemperatureCalculator {
    public static void start() {
        ryanTemperatureGreet();

        Scanner scanner = new Scanner(System.in);
        int choice;
        scanner.nextLine();

        do {
            System.out.println("\n*** RyanTemperature Converter ***");
            System.out.println("1. Celsius to Fahrenheit");
            System.out.println("2. Fahrenheit to Celsius");
            System.out.println("3. Celsius to Kelvin");
            System.out.println("4. Fahrenheit to Kelvin");
            System.out.println("5. Kelvin to Fahrenheit");
            System.out.println("6. Kelvin to Celsius");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            choice = getValidInt(scanner, "Choose an option: ");

            switch (choice) {

                case 1:
                    double celsiusToFahrenheit = getValidDouble(scanner, "Enter temperature in Celsius: ");
                    double fahrenheitCelsiusResult = RyanTemperature.celsiusToFahrenheit(celsiusToFahrenheit);
                    System.out.println(celsiusToFahrenheit + " °C = "
                                + RyanTemperature.roundToTwoDecimals(fahrenheitCelsiusResult) + " °F");
                    break;
                case 2:
                    double fahrenheitToCelsius = getValidDouble(scanner, "Enter temperature in Fahrenheit: ");
                    double celsiusFahrenheitResult = RyanTemperature.fahrenheitToCelsius(fahrenheitToCelsius);
                    System.out.println(fahrenheitToCelsius + " °F = "
                            + RyanTemperature.roundToTwoDecimals(celsiusFahrenheitResult) + " °C");
                    break;
                case 3:
                    double celsiusToKelvin = getValidDouble(scanner, "Enter temperature in Celsius: ");
                    double kelvinCelsiusResult = RyanTemperature.celsiusToKelvin(celsiusToKelvin);
                    System.out.println(celsiusToKelvin + " °C = "
                            + RyanTemperature.roundToTwoDecimals(kelvinCelsiusResult) + " °K");
                    break;
                case 4:
                    double fahrenheitToKelvin = getValidDouble(scanner, "Enter temperature in Fahrenheit: ");
                    double kelvinFahrenheitResult = RyanTemperature.fahrenheitToKelvin(fahrenheitToKelvin);
                    System.out.println(fahrenheitToKelvin + " °F = "
                            + RyanTemperature.roundToTwoDecimals(kelvinFahrenheitResult) + " °K");
                    break;
                case 5:
                    double kelvinToFahrenheit = getValidDouble(scanner, "Enter temperature in Kelvin: ");
                    double fahrenheitKelvinResult = RyanTemperature.kelvinToFahrenheit(kelvinToFahrenheit);
                    System.out.println(kelvinToFahrenheit + " °K = "
                            + RyanTemperature.roundToTwoDecimals(fahrenheitKelvinResult) + " °F");
                    break;
                case 6:
                    double kelvinToCelsius = getValidDouble(scanner, "Enter temperature in Kelvin: ");
                    double celsiusKelvinResult = RyanTemperature.kelvinToCelsius(kelvinToCelsius);
                    System.out.println(kelvinToCelsius + " °K = "
                            + RyanTemperature.roundToTwoDecimals(celsiusKelvinResult) + " °C");
                    break;
                case 7:
                    ryanTemperatureGoodbye();
                    pressEnterToContinue();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);
    }

    private static int getValidInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // clear bad input. Credited to CoPilot for helping me find
                                    // an error checker that fit my program
            }
        }
    }

    private static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // clear bad input. Credited to CoPilot for helping me find
                                    // an error checker that fit my program
            }
        }
    }
}
