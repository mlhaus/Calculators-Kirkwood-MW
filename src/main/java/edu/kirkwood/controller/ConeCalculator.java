package edu.kirkwood.controller;

import edu.kirkwood.model.Cone;
import edu.kirkwood.view.*;

public class ConeCalculator {

    //MENU TO BE CALLED BY ANOTHER CLASS
    public static void show(){
        Messages.hello();

        String[] coneMenuItems = {
                "Calculate missing dimension (radius, height, slant height)",
                "Calculate volume",
                "Calculate curved surface area",
                "Calculate total surface area",
                "Back"
        };
        UIUtility.printMenu("Cone Calculator", coneMenuItems);
        int choice = UserInput.getInt("Please choose an option: ", true, 1, coneMenuItems.length);
        switch(choice){
            case 1:
                missingDimensionMenu();
                break;
            case 2:
                getVolume();
                break;
            case 3:
                getLateralSurfaceArea();
                break;
            case 4:
                getSurfaceArea();
                break;
            case 5:
                MainMenu.show();
                return; // exit the start
            default:
                break;
        }
    }

    // Switch-Case for missingDimension
    private static void missingDimensionMenu() {
        String missingDimension;
        while (true) {
            // Ask the user what Dimension is missing.
            missingDimension = UserInput.getString("Which dimension is missing " +
                    "(r=Radius, h=Height, l=SlantHeight)").toLowerCase();
            // this logic is for is the user puts incorrect input
            if (missingDimension.equals("r") || missingDimension.equals("h") || missingDimension.equals("l")) {
                break;
            }
        }

        // Set variables to 0 for switch-Case
        double radius = 0.0;
        double height = 0.0;
        double slantHeight = 0.0;


        //Case-Switch for missingDimension + User Input
        switch (missingDimension) {
            case "r":
                try {
                    // Input height, a number that's bigger than zero.
                    height = UserInput.getDouble("Enter height: ", true, 0.0001, 999999.0);

                    // Input slant height, a number that's bigger than height, because the Hypotenuse is always the biggest.
                    slantHeight = UserInput.getDouble("Enter slant height: ", true, height + 0.0001, 999999.0);

                    // Calculate the radius
                    radius = Cone.calculateRadius(height, slantHeight);

                    // Display result
                    UIUtility.displaySuccess("Calculated radius: " + Helpers.round(radius, 2));
                    break;

                } catch (IllegalArgumentException e) {
                    UIUtility.displayError(e.getMessage());
                    return;
                }
            case "h":
                try {
                    // Input Radius, a number that's bigger than 0.
                    radius = UserInput.getDouble("Enter radius", true, 0.0001, 999999.0);

                    // Input slantHeight, Added to the radius because hypotenuse is always bigger than b or a.
                    slantHeight = UserInput.getDouble("Enter slant height", true, radius + 0.0001, 999999.0);

                    // Calculate the height.
                    height = Cone.calculateHeight(radius, slantHeight);

                    // Display the results.
                    UIUtility.displaySuccess("Calculated height: " + Helpers.round(height, 2));
                    break;

                } catch (IllegalArgumentException e) {
                    UIUtility.displayError(e.getMessage());
                    return;
                }
            case "l":
                try {
                    // Input Radius, a number that's bigger than zero.
                    radius = UserInput.getDouble("Enter radius", true, 0.0001, 999999.0);

                    // Input Height, a number that's bigger than zero.
                    height = UserInput.getDouble("Enter height", true, 0.0001, 999999.0);

                    // Calculate the slantHeight (Hypotenuse)
                    slantHeight = Cone.calculateSlantHeight(radius, height);

                    // Display the results.
                    UIUtility.displaySuccess("Calculated slant height: " + Helpers.round(slantHeight, 2));
                    break;

                } catch (IllegalArgumentException e) {
                    UIUtility.displayError(e.getMessage());
                }
            default:
                UIUtility.displayError("Invalid input, Please enter R, H, or L.");
        }
    }

    /**
     * Prompting for radius, height, to calculate Slant height.
     * @return A new Cone object
     */
    private static Cone promptRadiusAndHeight() {
        //get the radius
        double radius = UserInput.getDouble("Enter radius", true, 0.0001, 999999.0);
        //get the height
        double height = UserInput.getDouble("Enter height", true, 0.0001, 999999.0);
        //calculate slantHeight
        double slantHeight = Cone.calculateSlantHeight(radius, height);
        return new Cone(radius, height, slantHeight);
    }

    /**
     * Prompting for radius and slant to get height.
     * @return A new Cone object
     */
    private static Cone promptRadiusAndSlant() {
        //get the radius
        double radius = UserInput.getDouble("Enter radius", true, 0.0001, 999999.0);
        //get the slantHeight
        double slantHeight = UserInput.getDouble("Enter slant height", true, radius + 0.0001, 999999.0);
        // calculate the height
        double height = Cone.calculateHeight(radius, slantHeight);
        return new Cone(radius, height, slantHeight);
    }

    /**
     * logic for getVolume
     */
    private static void getVolume() {
        //assigning cone to be a new cone object returned by the prompt function
        Cone cone = promptRadiusAndHeight();
        UIUtility.displaySuccess("Volume: " + Helpers.round(
                cone.getVolume(), 2));
        UIUtility.pressEnterToContinue();
    }

    /**
     *logic for get curved surface
     */
    private static void getLateralSurfaceArea() {
        //assigning cone to be a new cone object returned by the prompt function
        Cone cone = promptRadiusAndHeight();
        UIUtility.displaySuccess("Lateral Surface Area: " + Helpers.round(
                cone.getLateralSurfaceArea(), 2));
        UIUtility.pressEnterToContinue();
    }

    /**
     * logic for getting the full surface area
     */
    private static void getSurfaceArea() {
        //assigning cone to be a new cone object returned by the prompt function
        Cone cone = promptRadiusAndSlant();
        UIUtility.displaySuccess("Total Surface Area: " + Helpers.round(
                cone.getTotalSurfaceArea(), 2));
        UIUtility.pressEnterToContinue();
    }
}

