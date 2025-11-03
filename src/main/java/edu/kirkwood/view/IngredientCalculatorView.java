package edu.kirkwood.view;

// file: /src/main/java/edu/kirkwood/view/IngredientCalculatorView.java v1 - View class constructors

import java.io.InputStream;
import java.util.Scanner;

/**
 * View class for displaying user interface
 */
public class IngredientCalculatorView {
    private Scanner scanner;

    /**
     * Default constructor
     * Initializes scanner with System.in
     */
    public IngredientCalculatorView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructor with custom input stream
     * @param inputStream The input stream to use for user input
     */
    public IngredientCalculatorView(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null");
        }
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Constructor with existing Scanner
     * @param scanner The scanner to use for input
     */
    public IngredientCalculatorView(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }
        this.scanner = scanner;
    }
}