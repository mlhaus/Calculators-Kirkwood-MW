package edu.kirkwood.view;

import edu.kirkwood.controller.ConeCalculator;
import edu.kirkwood.controller.FractionCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {

    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator", 
                "Cone Calculator",
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
                    ConeCalculator.show();
                    break;
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method
}
