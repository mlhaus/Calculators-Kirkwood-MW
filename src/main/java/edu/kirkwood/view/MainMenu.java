package edu.kirkwood.view;

import edu.kirkwood.controller.*;
import edu.kirkwood.controller.edward.IngredientCalculator;
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
                    LawsonsCurrencyCalculator.start();
                    break;
                case 3:
                    ConeCalculator.show();
                    break;
                case 4:
                    InvestmentCalculator.start();
                    break;
                case 5:
                    SetCalculator.start();
                    break;
                case 6:
                    UnitCircleCalculator.start();
                    break;
                case 7:
                    TimeCalculator.start();
                    break;
                case 8:
                    IngredientCalculator.start();
                    break;
                case 9:
                    EthansTemperatureCalculator.start();
                    break;
                case 10:
                    CharacterMoveCalc.start();
                    break;
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method
}
