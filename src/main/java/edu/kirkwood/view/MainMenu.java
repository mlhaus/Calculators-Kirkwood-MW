package edu.kirkwood.view;

import edu.kirkwood.controller.AslesMeasurementCalculator;
import edu.kirkwood.controller.FractionCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {

    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator", 
                "Asle's Measurement Calculator",
                "Quit"
        };
        while(true) {
            printMenu("Main Menu", menuItems);
            int choice = getInt("Choose an option", false,1, menuItems.length);
            switch(choice) {
                case 1:
                    FractionCalculator.start();
                    break;
                case 2:
                    AslesMeasurementCalculator.start();
                    break;
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method

    /**
     * shows AslesMeasurementCalculator menu
     */
    public static void showAsleMenu() {
        String[] menuItems = {
                "Create A Measurement",
                "View Measurement(s)",
                "Add Measurements",
                "Subtract Measurements",
                "Quit"
        };
        String menuTitle = "Asle's Measurement Menu";
        while (true) {
            printMenu(menuTitle, menuItems);
            int choice = getInt("Choose an option", false, 1, menuItems.length);
            switch (choice) {
                case 1:
                    AslesMeasurementCalculator.createAMeasurement();
                    break;
                case 2:
                    AslesMeasurementCalculator.viewMeasurements(false);
                    break;
                case 3:
                    AslesMeasurementCalculator.addMeasurement();
                    break;
                case 4:
                    AslesMeasurementCalculator.subtractMeasurements();
                    break;
                default:
                    return;

            }
        }
    }

    /**
     * shows a menu with units, has user enter a number to select a certain unit
     * @param isMath if used when doing math, removes the back option
     * @return the integer representing the unit that was selected
     */
    public static Number showUnitsMenu(boolean isMath) {
        Integer returnInt;
        if (!isMath) {
            String[] menuItems = {
                    "Inches",
                    "Feet",
                    "Yards",
                    "Miles",
                    "Centimeters",
                    "Meters",
                    "Kilometers",
                    "Back"
            };
            String menuTitle = "Select A Unit Of Measurement";
            printMenu(menuTitle, menuItems);
            returnInt = getInt("Choose an option", true, 1, menuItems.length);
        }else {
            String[] menuItems = {
                    "Inches",
                    "Feet",
                    "Yards",
                    "Miles",
                    "Centimeters",
                    "Meters",
                    "Kilometers",
            };
            String menuTitle = "Select A Unit Of Measurement";
            printMenu(menuTitle, menuItems);
            returnInt = getInt("Choose an option", true, 1, menuItems.length);
        }

        return returnInt;

    };
}
