package edu.kirkwood.view;

import edu.kirkwood.controller.EthansTemperatureCalculator;
import edu.kirkwood.controller.FractionCalculator;
import edu.kirkwood.controller.InvestmentCalculator;
import edu.kirkwood.controller.MyCalculator;
import edu.kirkwood.controller.SetCalculator;
import edu.kirkwood.controller.UnitCircleCalculator;
import edu.kirkwood.controller.TimeCalculator;
import edu.kirkwood.controller.edward.IngredientCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {

    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator", 
                "Lizbeth's Math Calculator",
                "Blake's Set Calculator",
                "Calder's Unit Circle Calculator", 
                "Jason's Time Calculator",
                "McKeown's Ingredient Calculator",
                "Ethan's Temperature Calculator",
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
                case 3:
                    SetCalculator.start();
                    break;
                case 4:
                    UnitCircleCalculator.start();
                    break;
                case 5:
                    TimeCalculator.start();
                    break;
                case 6:
                    IngredientCalculator.start();
                    break;
                case 7:
                    EthansTemperatureCalculator.start();
                    break;
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method
}
