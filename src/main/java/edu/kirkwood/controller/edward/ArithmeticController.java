package edu.kirkwood.controller.edward;

// file: /src/main/java/edu/kirkwood/controller/ArithmeticController.java v1 - Arithmetic operations controller constructors

import edu.kirkwood.model.edward.UnitConverter;

/**
 * Controller for arithmetic operations on ingredients
 */
public class ArithmeticController {
    private UnitConverter converter;

    /**
     * Default constructor
     */
    public ArithmeticController() {
        this.converter = new UnitConverter();
    }

    /**
     * Constructor with custom converter
     * @param converter The unit converter to use for operations
     */
    public ArithmeticController(UnitConverter converter) {
        if (converter == null) {
            throw new IllegalArgumentException("Converter cannot be null");
        }
        this.converter = converter;
    }
}