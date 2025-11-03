package edu.kirkwood.view;

import edu.kirkwood.controller.FractionCalculator;
import edu.kirkwood.controller.UnitCircleCalculator;
import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {

    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator", 
                "Calder's Unit Circle Calculator", 
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
                    UnitCircleCalculator.start();
                    break;
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method
}
