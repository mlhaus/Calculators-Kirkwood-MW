package edu.kirkwood.controller.edward;

// file: /src/main/java/edu/kirkwood/controller/ValidationController.java v1 - Input validation controller constructors

import static edu.kirkwood.model.edward.IngredientCalculatorParameters.*;

/**
 * Controller for input validation operations
 */
public class ValidationController {
    private double minQuantity;
    private double maxQuantity;
    private int maxNameLength;

    /**
     * Default constructor with standard validation rules
     */
    public ValidationController() {
        this.minQuantity = MIN_QUANTITY;
        this.maxQuantity = MAX_QUANTITY;
        this.maxNameLength = MAX_INGREDIENT_NAME_LENGTH;
    }

    /**
     * Constructor with custom validation parameters
     * @param minQuantity Minimum allowed quantity
     * @param maxQuantity Maximum allowed quantity
     * @param maxNameLength Maximum ingredient name length
     */
    public ValidationController(double minQuantity, double maxQuantity, int maxNameLength) {
        if (minQuantity <= 0) {
            throw new IllegalArgumentException("Minimum quantity must be positive");
        }
        if (maxQuantity <= minQuantity) {
            throw new IllegalArgumentException("Maximum quantity must be greater than minimum");
        }
        if (maxNameLength <= 0) {
            throw new IllegalArgumentException("Maximum name length must be positive");
        }

        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.maxNameLength = maxNameLength;
    }
}