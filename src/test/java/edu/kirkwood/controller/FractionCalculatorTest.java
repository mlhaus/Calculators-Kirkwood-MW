package edu.kirkwood.controller;

import edu.kirkwood.model.Fraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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



    @Test
    void parseFractionPositiveWholeNumber() {
        // Arrange
        String input = "5";
        Fraction expected = new Fraction(5, 1);
        // Act
        Fraction actual = FractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionNegativeWholeNumber() {
        Fraction actual =  FractionCalculator.parseFraction("-5");
        assertEquals(-5, actual.getNumerator());
        assertEquals(1, actual.getDenominator());
    }

    @Test
    void parseFractionSimpleFraction() {
        // Arrange
        String input = "5/7";
        Fraction expected = new Fraction(5, 7);
        // Act
        Fraction actual = FractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionNegativeImproperFraction() {
        // Arrange
        String input = "-5/3";
        Fraction expected = new Fraction(-5, 3);
        // Act
        Fraction actual = FractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionMixedFraction() {
        // Arrange
        String input = "2 1/3";
        Fraction expected = new Fraction(7, 3);
        // Act
        Fraction actual = FractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionNegativeMixedFraction() {
        // Arrange
        String input = "-3 1/4";
        Fraction expected = new Fraction(-13, 4);
        // Act
        Fraction actual = FractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionWithText() {
        // One-liner
        assertThrows(IllegalArgumentException.class, () -> FractionCalculator.parseFraction("X"));

        // Arrange
        String input = "X";
        Class expected = IllegalArgumentException.class;
        // Act
        Executable actual = () -> FractionCalculator.parseFraction(input);
        // Assert
        assertThrows(expected, actual);
    }

    @Test
    void parseFractionInvalidSlash() {
        // one-liner
        assertThrows(IllegalArgumentException.class, () -> FractionCalculator.parseFraction("1 2"));

        // Arrange
        String input = "1 2";
        Class expected = IllegalArgumentException.class;
        // Act
        Executable actual = () -> FractionCalculator.parseFraction(input);
        // Assert
        assertThrows(expected, actual);
    }

    @Test
    void parseFractionDivideByZero() {
        // one-liner
        assertThrows(IllegalArgumentException.class, () -> FractionCalculator.parseFraction("1/0"));

        // Arrange
        String input = "1/0";
        Class expected = IllegalArgumentException.class;
        // Act
        Executable actual = () -> FractionCalculator.parseFraction(input);
        // Assert
        assertThrows(expected, actual);
    }

    @Test
    void parseFractionInvalidNumerator() {
        // one-liner
        assertThrows(IllegalArgumentException.class, () -> FractionCalculator.parseFraction("N/1"));

        // Arrange
        String input = "N/1";
        Class expected = IllegalArgumentException.class;
        // Act
        Executable actual = () -> FractionCalculator.parseFraction(input);
        // Assert
        assertThrows(expected, actual);
    }


    @Test
    void parseFractionInvalidDenominator() {
        // one-liner
        assertThrows(IllegalArgumentException.class, () -> FractionCalculator.parseFraction("1/N"));

        // Arrange
        String input = "1/N";
        Class expected = IllegalArgumentException.class;
        // Act
        Executable actual = () -> FractionCalculator.parseFraction(input);
        // Assert
        assertThrows(expected, actual);
    }

    @Test
    void parseFractionNegativeDenominator() {
        // Arrange
        String input = "1/-3";
        Fraction expected = new Fraction(-1, 3);
        // Act
        Fraction actual = FractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }


    @Test
    void parseFractionNegativeNumeratorMixedNumber() {
        // Arrange
        String input = "1 -1/3";
        Fraction expected = new Fraction(-4, 3);
        // Act
        Fraction actual =  FractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }











}