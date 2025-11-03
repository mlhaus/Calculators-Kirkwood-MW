package edu.kirkwood.view;

import edu.kirkwood.model.Set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.kirkwood.controller.SetCalculator.*;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getInt;

public class SetMenu {

    public static void showMenu() {
        String[] menuItems = {
                "Create a Set",
                "Perform Set Operations",
                "View Sets",
                "Destroy a Set",
                "Quit"};
        while(true) {
            printMenu("Set Calculator Menu", menuItems);
            int choice = getInt("Choose an option. Press 5 to quit.", false,1, menuItems.length);
            switch(choice) {
                case 1: addSet();
                    break;
                case 2: viewCalculation();
                    break;
                case 3: viewSetList();
                    break;
                case 4: removeSet();
                    break;
                case 5:
                    displayMessage("Thanks for trying the Set Calculator!");
                    pressEnterToContinue();
                    return;
            }
        }
    } // end show method

    public static void showOperations() {
        String[] menuItems = {
                "Union",
                "Intersection",
                "Difference",
                "Return"};
            printMenu("Operation Selection Menu", menuItems);
    }

    public static void showSets() {
        if (setList.isEmpty()) {
            displayError("There are no sets available.");
        }
        String[] nameArray = getSetNames(setList);
        List<String> menuList = new ArrayList<>(Arrays.asList(nameArray));
        menuList.add("Return");
        nameArray = menuList.toArray(new String[0]);
        printMenu("Set Selection Menu", nameArray);
    }

    public static void displayResult(Set set1, Set set2, Set result, String operation) {
        System.out.println("----------------------------------------");
        displayMessage("Calculating Result Set");
        System.out.println("Set " + set1.toString() + " " + operation + " with set " + set2.toString() +  " equals");
        System.out.println(result.toString());
        System.out.println("----------------------------------------");
    }
}