package edu.kirkwood.view;

import edu.kirkwood.controller.ConeCalculator;
import edu.kirkwood.controller.EthansTemperatureCalculator;
import edu.kirkwood.controller.FractionCalculator;
import edu.kirkwood.controller.LawsonsCurrencyCalculator;
import edu.kirkwood.controller.InvestmentCalculator;
import edu.kirkwood.controller.SetCalculator;
import edu.kirkwood.controller.TimeCalculator;
import edu.kirkwood.controller.UnitCircleCalculator;
import edu.kirkwood.controller.edward.IngredientCalculator;
import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {

    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator", 
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
                default:
                    return;
            } // end switch
        } // end loop
    } // end show method
}
