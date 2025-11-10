//ZeeKonCal
//9/27/2025-10/1/2025
//A central class for calculating values to do with unit circles
package edu.kirkwood.controller;

import edu.kirkwood.model.Angle;
import edu.kirkwood.view.Helpers;
import static edu.kirkwood.view.Messages.unitCircleGoodbye;
import static edu.kirkwood.view.Messages.unitCircleGreet;
import static edu.kirkwood.view.UIUtility.clearScreen;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getDouble;
import static edu.kirkwood.view.UserInput.getString;

public class UnitCircleCalculator {
    public static void start() {
        unitCircleGreet();
        boolean stop = false;
        while (!stop) {
            boolean isDegrees;
            while (true) {
                String isDegreesUser = getString("Is your angle in degrees or radians?", true).toLowerCase().strip();
                switch (isDegreesUser) {
                    case "degree", "degrees", "deg", "d" -> {
                        isDegrees = true;
                        break;
                    }
                    case "radian", "radians", "rad", "r" -> {
                        isDegrees = false;
                        break;
                    }
                    default -> {
                        displayError("Input must be either \"Degrees\" or \"Radians\"");
                        continue;
                    }
                }
                break;
            }
            clearScreen();
            double userAngle = getDouble(
                    "Please enter the amount of " + (isDegrees ? "degrees" : "radians") + " you would like to calculate?\n[1 Radian = ~57.3 Degrees] [Pi = 3.1415] [2Pi Radians = 360 Degrees]", true);
            Angle angle = new Angle(userAngle);
            angle.correctAngle(isDegrees);
            clearScreen();
            printAngleDetails(angle, isDegrees);
            while (true) {
                String input = getString("Would you like to calculate another angle?", true);
                switch (input.toLowerCase().strip()) {
                    case "y", "yes" -> {
                        break;
                    }
                    case "n", "no" -> {
                        stop = true;
                        break;
                    }
                    default -> {
                        displayError("Input must be either \"Yes\" or \"No\"");
                        continue;
                    }
                }
                clearScreen();
                break;
            }
        }
        unitCircleGoodbye();
        pressEnterToContinue();
    }

    public static String formatInput(double angle, boolean isDegrees) {
        return String.format("%s %s", Helpers.round(angle, 3), (isDegrees ? "degrees" : "radians"));
    }

    public static void printAngleDetails(Angle angle, boolean isDegrees) {
        String formattedAngle = formatInput(isDegrees? angle.getAngle() : Math.toRadians(angle.getAngle()), isDegrees);
        System.out.printf("The sin of %s is %s\nThe cos of %s is %s\nYour angle is in quadrant %s.\n\n", formattedAngle,
                    angle.calcSin(), formattedAngle, angle.calcCos(), angle.getQuadrant());
    }
}
