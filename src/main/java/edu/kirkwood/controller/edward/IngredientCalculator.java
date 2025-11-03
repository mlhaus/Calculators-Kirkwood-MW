// file: /src/main/java/edu/kirkwood/controller/IngredientCalculator.java v3 - Improved UX based on user feedback

package edu.kirkwood.controller.edward;

import edu.kirkwood.model.edward.Ingredient;
import edu.kirkwood.model.edward.Unit;
import edu.kirkwood.model.edward.UnitConverter;
import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.*;

public class IngredientCalculator {

    private static List<Ingredient> ingredients = new ArrayList<>();
    private static UnitConverter converter = new UnitConverter();

    public static void start() {
        ingredientGreet();

        String[] menuItems = {
                "Create New Ingredient",
                "Perform Arithmetic Operations",
                "Convert Units",
                "Display All Ingredients",
                "Return to Main Menu"
        };

        while(true) {
            printMenu("Ingredient Calculator", menuItems);
            int choice = getInt("Choose an option", false, 1, menuItems.length);

            switch(choice) {
                case 1:
                    createIngredient();
                    break;
                case 2:
                    performArithmetic();
                    break;
                case 3:
                    convertUnits();
                    break;
                case 4:
                    displayIngredients();
                    break;
                default:
                    ingredientGoodbye();
                    return;
            }
        }
    }

    private static void createIngredient() {
        try {
            if (ingredients.size() >= 100) {
                displayError("Maximum number of ingredients reached (100)");
                return;
            }

            displayMessage("Create New Ingredient");
            String name = getString("Enter ingredient name");
            double quantity = getDouble("Enter quantity", true, 0.001, Double.MAX_VALUE);

            displayMessage("Available Units:");
            displayMessage("Volume: tsp, tbsp, fl oz, cup, pint, quart");
            displayMessage("Weight: oz, lb, g, kg");

            String unitStr = getString("Enter unit").toLowerCase().trim();
            Unit unit = parseUnit(unitStr);

            if (unit == null) {
                displayError("Invalid unit. Please use: tsp, tbsp, fl oz, cup, pint, quart, oz, lb, g, kg");
                return;
            }

            Ingredient ingredient = new Ingredient(name, quantity, unit);
            ingredients.add(ingredient);

            displaySuccess("Ingredient created: " + ingredient.toString());

        } catch (Exception e) {
            displayError("Error creating ingredient: " + e.getMessage());
        }

        pressEnterToContinue();
    }

    private static void performArithmetic() {
        if (ingredients.isEmpty()) {
            displayError("Need at least 1 ingredient for arithmetic operations");
            pressEnterToContinue();
            return;
        }

        try {
            displayMessage("Arithmetic Operations");

            // Ask for operation type first
            String[] operations = {"Add (+)", "Subtract (-)", "Multiply (*)", "Divide (/)"};
            printMenu("Select Operation", operations);
            int operation = getInt("Choose operation", true, 1, operations.length);

            Ingredient result = null;

            switch(operation) {
                case 1: // Add
                case 2: // Subtract
                    // Need two ingredients
                    if (ingredients.size() < 2) {
                        displayError("Need at least 2 ingredients for addition/subtraction operations");
                        pressEnterToContinue();
                        return;
                    }

                    displayIngredientsList();
                    printLine();
                    displayWarning("This operation only works with compatible unit types");
                    displayMessage("Volume units (tsp, tbsp, fl oz, cup, pint, quart) can only be combined with other volume units");
                    displayMessage("Weight units (oz, lb, g, kg) can only be combined with other weight units");
                    printLine();

                    int index1 = getInt("Select first ingredient (number)", true, 1, ingredients.size()) - 1;
                    int index2 = getInt("Select second ingredient (number)", true, 1, ingredients.size()) - 1;

                    Ingredient ingredient1 = ingredients.get(index1);
                    Ingredient ingredient2 = ingredients.get(index2);

                    if (operation == 1) {
                        result = ingredient1.add(ingredient2);
                    } else {
                        result = ingredient1.subtract(ingredient2);
                    }
                    break;

                case 3: // Multiply
                    displayIngredientsList();
                    int multiplyIndex = getInt("Select ingredient to multiply (number)", true, 1, ingredients.size()) - 1;
                    Ingredient ingredientToMultiply = ingredients.get(multiplyIndex);
                    double multiplier = getDouble("Enter multiplier", true, 0.001, Double.MAX_VALUE);

                    // Create result with clean name (no operation symbols)
                    Ingredient tempResult = ingredientToMultiply.multiply(multiplier);
                    result = new Ingredient(ingredientToMultiply.getName(), tempResult.getQuantity(), tempResult.getUnit());
                    break;

                case 4: // Divide
                    displayIngredientsList();
                    int divideIndex = getInt("Select ingredient to divide (number)", true, 1, ingredients.size()) - 1;
                    Ingredient ingredientToDivide = ingredients.get(divideIndex);
                    double divisor = getDouble("Enter divisor", true, 0.001, Double.MAX_VALUE);

                    // Create result with clean name (no operation symbols)
                    Ingredient tempDivideResult = ingredientToDivide.divide(divisor);
                    result = new Ingredient(ingredientToDivide.getName(), tempDivideResult.getQuantity(), tempDivideResult.getUnit());
                    break;
            }

            if (result != null) {
                displaySuccess("Result: " + result.toString());

                boolean saveResult = getBoolean("Save result as new ingredient?", false);
                if (saveResult) {
                    ingredients.add(result);
                    displaySuccess("Result saved!");
                }
            }

        } catch (Exception e) {
            displayError("Error performing operation: " + e.getMessage());
        }

        pressEnterToContinue();
    }

    private static void convertUnits() {
        if (ingredients.isEmpty()) {
            displayError("No ingredients available for conversion");
            pressEnterToContinue();
            return;
        }

        try {
            displayMessage("Unit Conversion");
            displayIngredientsList();

            int index = getInt("Select ingredient to convert (number)", true, 1, ingredients.size()) - 1;
            Ingredient ingredient = ingredients.get(index);

            displayMessage("Current: " + ingredient.toString());
            displayMessage("Available compatible units:");

            if (ingredient.getUnit().isVolumeUnit()) {
                displayMessage("Volume units: tsp, tbsp, fl oz, cup, pint, quart");
            } else {
                displayMessage("Weight units: oz, lb, g, kg");
            }

            String unitStr = getString("Enter target unit").toLowerCase().trim();
            Unit targetUnit = parseUnit(unitStr);

            if (targetUnit == null) {
                displayError("Invalid unit. Please use valid units listed above.");
                pressEnterToContinue(); // Added this line
                return;
            }

            if (!ingredient.getUnit().isCompatible(targetUnit)) {
                displayError("Incompatible units - cannot convert between volume and weight");
                pressEnterToContinue(); // Added this line
                return;
            }

            Ingredient converted = ingredient.convertTo(targetUnit);
            displaySuccess("Converted: " + converted.toString());

            boolean saveConverted = getBoolean("Save converted ingredient?", false);
            if (saveConverted) {
                ingredients.add(converted);
                displaySuccess("Converted ingredient saved!");
            }

        } catch (Exception e) {
            displayError("Error converting units: " + e.getMessage());
            pressEnterToContinue(); // Added this line for any other errors
        }

        pressEnterToContinue();
    }

    private static void displayIngredients() {
        if (ingredients.isEmpty()) {
            displayMessage("No ingredients created yet");
        } else {
            displayMessage("All Ingredients:");
            printLine();
            displayIngredientsList();
        }
        pressEnterToContinue();
    }

    private static void displayIngredientsList() {
        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println((i + 1) + ". " + ingredients.get(i).toString());
        }
    }

    private static Unit parseUnit(String unitStr) {
        // Handle common abbreviations and variations
        switch(unitStr) {
            case "tsp":
            case "teaspoon":
            case "teaspoons":
                return Unit.TSP;
            case "tbsp":
            case "tablespoon":
            case "tablespoons":
                return Unit.TBSP;
            case "fl oz":
            case "floz":
            case "fluid ounce":
            case "fluid ounces":
                return Unit.FL_OZ;
            case "cup":
            case "cups":
                return Unit.CUP;
            case "pint":
            case "pints":
                return Unit.PINT;
            case "quart":
            case "quarts":
                return Unit.QUART;
            case "oz":
            case "ounce":
            case "ounces":
                return Unit.OZ;
            case "lb":
            case "lbs":
            case "pound":
            case "pounds":
                return Unit.LB;
            case "g":
            case "gram":
            case "grams":
                return Unit.GRAM;
            case "kg":
            case "kilogram":
            case "kilograms":
                return Unit.KILOGRAM;
            default:
                return null;
        }
    }
}