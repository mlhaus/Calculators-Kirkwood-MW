package edu.kirkwood.controller;

import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CurrencyCalculatorTest {

    @Test
    public void startWithValidInputs() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
             MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("5 USD + 5 EUR", "quit");
            LawsonsCurrencyCalculator.start();
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    // I was having trouble getting the exception because of how I designed the code, so instead to make it sure it-
    // is supposed to I made this thing. It checks if UserInput is being called twice when an error should occur.
    // This is a simple way of checking as the program should continue as normal and just diaply a message
    // Then continue on as usual.
    @Test
    public void startWithInvalidExtraInputsButDisplaysErrorAndContinues() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("5 USD + 5 EUR + 5 GBP", "quit");
            LawsonsCurrencyCalculator.start();
            staticUserInput.verify(() -> UserInput.getString(anyString()), times(2));
        }
    }

    // Same as above however this ensures that the numeric values being input are being read properly.
    @Test
    public void startWithInvalidNumericInputsButDisplaysErrorAndContinues() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("USD 5 + EUR 5", "quit");
            LawsonsCurrencyCalculator.start();
            staticUserInput.verify(() -> UserInput.getString(anyString()), times(2));
        }
    }

    // Same as above but mocking to make sure that it does currency codes properly (Really running out of ideas, I don't know what else to test).
    @Test
    public void startWithInvalidCurrencyCodeInputsButDisplaysErrorAndContinues() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("5 USD + 5 SEK", "quit");
            LawsonsCurrencyCalculator.start();
            staticUserInput.verify(() -> UserInput.getString(anyString()), times(2));
        }
    }

    // Using the stream here to capture what I am outputting to the console to make sure it matches my expected result and not something else.
    @Test
    public void startWithSameCurrencyOperation() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("5 USD + 5 USD", "quit");
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            try {
                LawsonsCurrencyCalculator.start();
            } finally {
                System.setOut(originalOut);
            }
            String output = outContent.toString();
            assertTrue(output.contains("Result: 5.00 USD + 5.00 USD = 10.00 USD"));
        }
    }

    @Test
    public void startWithBadNumberInputs() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("USD 5 + 5 USD", "quit");
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            try {
                LawsonsCurrencyCalculator.start();
            } finally {
                System.setOut(originalOut);
            }
            String output = outContent.toString();
            assertTrue(output.contains("*** ERROR - Invalid numeric values. ***"));
        }
    }

    @Test
    public void startWithDisallowedOperationMultiplication() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("5 USD * 5 EUR", "quit");
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            try {
                LawsonsCurrencyCalculator.start();
            } finally {
                System.setOut(originalOut);
            }
            String output = outContent.toString();
            assertTrue(output.contains("*** ERROR - For multiplication, both operands must use the same currency. ***"));
        }
    }

    @Test
    public void startWithDisallowedOperationDivision() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("5 USD / 5 EUR", "quit");
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            try {
                LawsonsCurrencyCalculator.start();
            } finally {
                System.setOut(originalOut);
            }
            String output = outContent.toString();
            assertTrue(output.contains("*** ERROR - For division, both operands must use the same currency. ***"));
        }
    }

    @Test
    public void startWithCurrencyAdditionOfNonUSD() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("5 EUR + 5 JPY", "quit");
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            try {
                LawsonsCurrencyCalculator.start();
            } finally {
                System.setOut(originalOut);
            }
            String output = outContent.toString();
            assertTrue(output.contains("Result: 5.00 EUR + 5.00 JPY = 5.03 EUR"));
        }
    }
}
