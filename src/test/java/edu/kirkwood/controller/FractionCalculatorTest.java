package edu.kirkwood.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionCalculatorTest {

    @Test
    void splitCalculationWithAddition() {
        // Arrange - define input and expected outputs
        String input = "1/2 + 3/4";
        String[] expected = {"1/2", "+", "3/4"};
        // Act - call the method to be tested
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCalculationWithSubtraction() {
        // Arrange - define input and expected outputs
        String input = "3 1/4 - 1/2";
        String[] expected = {"3 1/4", "-", "1/2"};
        // Act - call the method to be tested
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCalculationWithMultiplication() {
        // Arrange - define input and expected outputs
        String input = "-5 * -2 1/3";
        String[] expected = {"-5", "*", "-2 1/3"};
        // Act - call the method to be tested
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCalculationWithDivision() {
        // Arrange - define input and expected outputs
        String input = "10/3 / 5";
        String[] expected = {"10/3", "/", "5"};
        // Act - call the method to be tested
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }


    @Test
    void splitCalculationTooManySpaces() {
        // Arrange - define input and expected outputs
        String input = "     10/3     /    5     ";
        String[] expected = {"10/3", "/", "5"};
        // Act - call the method to be tested
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCalculationNoSpacesAroundOperator() {
        // Arrange - define input and expected outputs
        String input = "10/3/5";
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> FractionCalculator.splitCalculation(input));

        // Arrange
        String expectedError = "Invalid format";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitCalculationInvalidOperator1() {
        // Arrange - define input and expected outputs
        String input = "10/3 ! 5";
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> FractionCalculator.splitCalculation(input));

        // Arrange
        String expectedError = "Invalid format";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitCalculationInvalidOperator2() {
        // Arrange - define input and expected outputs
        String input = "10/3 x 5";
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> FractionCalculator.splitCalculation(input));

        // Arrange
        String expectedError = "Invalid format";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitCalculationFirstFractionMissing() {
        // Arrange - define input and expected outputs
        String input = " / 5";
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> FractionCalculator.splitCalculation(input));

        // Arrange
        String expectedError = "Missing fraction";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitCalculationSecondFractionMissing() {
        // Arrange - define input and expected outputs
        String input = "5 / ";
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> FractionCalculator.splitCalculation(input));

        // Arrange
        String expectedError = "Missing fraction";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }




}