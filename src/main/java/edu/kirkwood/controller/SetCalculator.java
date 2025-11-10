package edu.kirkwood.controller;

import edu.kirkwood.model.Set;

import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.view.Messages.setGoodbye;
import static edu.kirkwood.view.Messages.setGreet;
import static edu.kirkwood.view.SetMenu.*;
import static edu.kirkwood.view.UIUtility.displayMessage;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class SetCalculator {
    public static List<Set> setList =  new ArrayList<>();
    public static void start() {
        System.out.println();
        setGreet();
        showMenu();
        setGoodbye();
    }

    /**
     * Allows the user to add a set to the set list. Asks for a set name and list of elements, validates the input,
     * and adds the set to the list.
     */
    public static void addSet() {
        while (true) {
            System.out.println();
            System.out.println("----------------------------------------");
            String inputName = getString("Enter a name for the set: ").trim();
            if (inputName.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a name.");
                continue;
            }
            boolean nameExists = false;
            if (!setList.isEmpty()) {
                for (Set set : setList) {
                    if (set.getName().equalsIgnoreCase(inputName)) {
                        System.out.println("That name is already used. Please enter a different name.");
                        nameExists = true;
                        break;
                    }
                }
            }
            if (nameExists) {
                continue;
            }
            String inputValues = getString(
                    """
                            ----------------------------------------
                            Enter the elements of the set separated by commas. 
                            Supplying zero elements will create an empty set.
                            Example: 1, 2, 3, dog, car
                            Press Enter when finished
                            ----------------------------------------
                            \s""",
                    false
            );
            ArrayList<String> parsedValues = parseSetValues(inputValues);
            Set newSet = new Set(inputName, parsedValues);
            setList.add(newSet);
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("Set '" + inputName + "' created successfully.");
            pressEnterToContinue();
            break;
        }
    }

    /**
     * Shows the list of sets to a user and allows them to select a set to delete. Prompts for confirmation and removes
     * the set from the set list.
     */
    public static void removeSet() {
        while (true) {
            showSets();
            int setChoice = getInt(
                    "Choose the set you would like to destroy. Press " + (setList.size() + 1) + " to return.",
                    true, 1, setList.size() + 1);
            if (setChoice == setList.size() + 1) {
                return;
            }
            Set chosenSet = setList.get(setChoice - 1);
            int confirmation = getInt(
                    "You have chosen to destroy set '" + chosenSet.getName() + "'. Are you sure?\n" + "Press 1 to confirm or 2 to cancel.\n"
                    + "----------------------------------------\n",
                    true, 1, 2);
            if (confirmation == 1) {
                setList.remove(setChoice - 1);
                System.out.println("Set '" + chosenSet.getName() + "' has been destroyed.");
                pressEnterToContinue();
                return;
            } else if (confirmation == 2) {
                System.out.println("Set removal cancelled.");
                pressEnterToContinue();
                return;
            }  else {
                displayMessage("Invalid choice. Please enter a valid choice.");
                pressEnterToContinue();
            }
        }
    }

    /**
     * Displays available sets to the user. Has the user select two sets. Has the user
     * select an operation and displays the result.
     */
    public static void viewCalculation() {
        while (true) {
            showSets();
            int choice1 = getInt("Choose the first set for the operation. Press " + (setList.size() + 1) + " to return.", true, 1, setList.size() + 1);
            if  (choice1 == setList.size() + 1) {
                return;
            }
            Set set1 = setList.get(choice1 - 1);

            int choice2 = getInt("Choose the second set: ", true, 1, setList.size() + 1);
            if  (choice2 == setList.size() + 1) {
                return;
            }
            Set set2 = setList.get(choice2 - 1);


            showOperations();
            int operationChoice = getInt("Choose the type of operation to perform. Press 4 to return. " + 1, true, 1, 4);

            Set result = new Set();
            String operation = "";

            if (operationChoice == 1) {
                result = set1.union(set2);
                operation = "Union";
            } else if (operationChoice == 2) {
                result = set1.intersection(set2);
                operation = "Intersection";
            } else if (operationChoice == 3) {
                result = set1.difference(set2);
                operation = "Difference";
            } else if (operationChoice == 4) {
                return;
            } else {
                displayMessage("Invalid choice. Please enter a valid choice.");
            }
            displayResult(set1, set2, result, operation);
            pressEnterToContinue();
        }
    }

    /**
     * Calls the showSets() function to display the sets to the user. Prompts the user for an integer to view details of a
     * set, or returns with last menu option.
     */
    public static void viewSetList() {

        while (true) {
            showSets();
            if(setList.isEmpty()) {
                displayMessage("No sets have been added.");
                pressEnterToContinue();
                return;
            }
            int setChoice = getInt("Enter the number of a set to view more details. Press " + (setList.size() + 1) + " to return.", true, 1, setList.size() + 1);
            if (setChoice == setList.size() + 1) {
                return;
            }
            displayMessage(setList.get(setChoice - 1).toString());
            pressEnterToContinue();
        }
    }

    /**
     * Takes in the list of all sets and returns the name properties of the sorted sets.
     * @param setList List of set objects
     * @return String[] of Set name properties
     */
    public static String[] getSetNames(List<Set> setList) {
        String[] names = new String[setList.size()];
        for (int i = 0; i < setList.size(); i++) {
            names[i] = setList.get(i).getName();
        }
        return names;
    }

    /**
     * Takes in a string of input from the user. Splits the string on commas to parse the set elements.
     * No input is valid input and creates a legitimate empty set.
     * No comma is also valid input, the user is allowed to have a set with one element.
     * @param inputValues String of elements separated by commas
     * @return {@literal ArrayList<String>} elements of the set
     */
    public static ArrayList<String> parseSetValues(String inputValues) {
        ArrayList<String> result = new ArrayList<>();
        if (inputValues.isEmpty()) {
            return result;
        }
        for (String part : inputValues.split(",")) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty() && !result.contains(trimmed)){
                result.add(trimmed);
            }
        }
        return result;
    }
}
