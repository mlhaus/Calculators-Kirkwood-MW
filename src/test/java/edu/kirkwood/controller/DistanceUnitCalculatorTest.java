package edu.kirkwood.controller;

import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DistanceUnitCalculatorTest {


    @Test
    @DisplayName("start(): Tests: Initializing the DistanceUnitCalculator App and immediately quitting with 'q'")
    void testStartAndQuitImmediatelyWithQ() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
             MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

            // Arrange
            // Mocks the user inputting "q" immediately
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("q");

            // Act
            // Starts the DistanceUnitCalculator
            DistanceUnitCalculator.start();

            // Assert
            // Verifies that an error message is never displayed
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }


    @Test
    @DisplayName("Method: start Tests: Initializing the DistanceUnitCalculator App, using a valid input, then quitting")
    void testStartWithValidInputAndQuit() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
             MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

            // Arrange
            // Mocks the user typing a valid input, then quitting.
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("1 foot to inches", "quit");

            // Act
            // Starts the DistanceUnitCalculator
            DistanceUnitCalculator.start();

            // Assert
            // Verifies that an error message is never displayed
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    // Tests distance unit conversions
    @Test
    @DisplayName("Method: splitConversion Tests: Splitting a valid input string into value, from-unit, and to-unit")
    void testSplitConversionValidInput() {
        // Arrange
        String input = "5 mile to kilometer";
        String[] expected = {"5", "mile", "kilometer"};

        // Act
        String[] actual = DistanceUnitCalculator.splitConversion(input);

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Method: splitConversion Tests: Exception handling when the input is missing ' to '")
    void testSplitConversionMissingToKeyword() {
        // Arrange
        String input = "5 mile kilometer";
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;

        // Act
        Executable actual = () -> DistanceUnitCalculator.splitConversion(input);

        // Assert
        assertThrows(expected, actual);
    }

    @Test
    @DisplayName("Method: splitConversion Tests: Exception handling when the input is missing the starting value")
    void testSplitConversionMissingStartingValue() {
        // Arrange
        String input = "miles to kilometers";
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;

        // Act
        Executable actual = () -> DistanceUnitCalculator.splitConversion(input);

        // Assert
        assertThrows(expected, actual);
    }



    @Test
    @DisplayName("Method: splitConversion Tests: Exception handling when the input is missing the target Unit")
    void testSplitConversionMissingTargetUnit() {
        // Arrange
        String input = "5 mile to";
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;

        // Act
        Executable actual = () -> DistanceUnitCalculator.splitConversion(input);

        // Assert
        assertThrows(expected, actual);
    }


    @Test
    @DisplayName("Method: splitConversion Tests: Exception handling when the input is empty")
    void testSplitConversionEmptyInput() {
        // Arrange
        String input = "";
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;

        // Act
        Executable actual = () -> DistanceUnitCalculator.splitConversion(input);

        // Assert
        assertThrows(expected, actual);
    }

    @Test
    @DisplayName("Method: splitConversion Tests: Exception handling when the input is null")
    void testSplitConversionNullInput() {
        // Arrange
        String input = null;
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;

        // Act

        // The warning seems to be related to intelliJ misinterpreting the splitConversion method
        // Code works as intended
        Executable actual = () -> DistanceUnitCalculator.splitConversion(input);

        // Assert
        assertThrows(expected, actual);
    }

}