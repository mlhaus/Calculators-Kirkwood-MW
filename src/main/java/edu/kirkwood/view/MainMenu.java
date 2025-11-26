package edu.kirkwood.view;

import edu.kirkwood.controller.DistanceUnitCalculator;
import edu.kirkwood.controller.FractionCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {

    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator",
                "Lawson's Currency Calculator",
                "Cone Calculator",
                "Lizbeth's Math Calculator",
                "Blake's Set Calculator",
                "Calder's Unit Circle Calculator",
                "Jason's Time Calculator",
                "McKeown's Ingredient Calculator",
                "Ethan's Temperature Calculator",
                "Gabriel's Character Fighter Calculator",
                "Dine Depreciation Calculator",
                "Yousif's Money Calculator",
                "Quit"
        };
        while(true) {
            printMenu("Main Menu", menuItems);
            int choice = getInt("Choose an option", false,1, menuItems.length);
            switch(choice) {
                case 1:
                    FractionCalculator.start();
                    break;

                case 13:
                    DistanceUnitCalculator.start();
                    break;
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method
}