package edu.kirkwood.controller.edward;

// file: /src/main/java/edu/kirkwood/controller/IngredientCalculatorController.java v1 - Controller class constructors

import edu.kirkwood.model.edward.Ingredient;
import edu.kirkwood.model.edward.UnitConverter;
import edu.kirkwood.view.IngredientCalculatorView;

import java.util.ArrayList;

/**
 * Controller class for managing application logic
 */
public class IngredientCalculatorController {
    private ArrayList<Ingredient> ingredients;
    private IngredientCalculatorView view;
    private UnitConverter converter;
    private boolean isRunning;

    /**
     * Default constructor
     * Initializes with default view and empty ingredient list
     */
    public IngredientCalculatorController() {
        this.ingredients = new ArrayList<>();
        this.view = new IngredientCalculatorView();
        this.converter = new UnitConverter();
        this.isRunning = false;
    }

    /**
     * Constructor with custom view
     * @param view The view component to use
     */
    public IngredientCalculatorController(IngredientCalculatorView view) {
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        this.ingredients = new ArrayList<>();
        this.view = view;
        this.converter = new UnitConverter();
        this.isRunning = false;
    }

    /**
     * Constructor with custom view and converter
     * @param view The view component to use
     * @param converter The unit converter to use
     */
    public IngredientCalculatorController(IngredientCalculatorView view, UnitConverter converter) {
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        if (converter == null) {
            throw new IllegalArgumentException("Converter cannot be null");
        }
        this.ingredients = new ArrayList<>();
        this.view = view;
        this.converter = converter;
        this.isRunning = false;
    }

    /**
     * Full constructor with all dependencies
     * @param view The view component to use
     * @param converter The unit converter to use
     * @param initialIngredients Initial list of ingredients
     */
    public IngredientCalculatorController(IngredientCalculatorView view, UnitConverter converter, ArrayList<Ingredient> initialIngredients) {
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        if (converter == null) {
            throw new IllegalArgumentException("Converter cannot be null");
        }
        this.view = view;
        this.converter = converter;
        this.isRunning = false;

        // Deep copy the initial ingredients list to prevent external modification
        this.ingredients = new ArrayList<>();
        if (initialIngredients != null) {
            for (Ingredient ingredient : initialIngredients) {
                if (ingredient != null) {
                    this.ingredients.add(new Ingredient(ingredient));
                }
            }
        }
    }
}