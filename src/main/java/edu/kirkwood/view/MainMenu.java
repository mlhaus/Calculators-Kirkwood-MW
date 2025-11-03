package edu.kirkwood.view;

import edu.kirkwood.controller.FractionCalculator;
import edu.kirkwood.controller.InvestmentCalculator;
import edu.kirkwood.controller.MyCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {

    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator", 
                "Lizbeth's Math Calculator",
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
                    InvestmentCalculator.start();
                    break;
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method
}
