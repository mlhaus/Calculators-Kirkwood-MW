package edu.kirkwood.controller;

import edu.kirkwood.model.Time;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TimeCalculatorTest {
    @Test
    void splitCalculationWithAddition() {
        // Arrange - define input and expected outputs
        String input = "1 hour + 1 minute";
        String[] expected = {"1 hour", "+", "1 minute"};
        // Act - call the method to be tested
        String[] actual = TimeCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCalculationWithSubtraction() {
        // Arrange - define input and expected outputs
        String input = "1 hour - 1 minute";
        String[] expected = {"1 hour", "-", "1 minute"};
        // Act - call the method to be tested
        String[] actual = TimeCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCalculationWithMultiplication() {
        // Arrange - define input and expected outputs
        String input = "1 hour * 2";
        String[] expected = {"1 hour", "*", "2"};
        // Act - call the method to be tested
        String[] actual = TimeCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCalculationWithDivision() {
        // Arrange - define input and expected outputs
        String input = "1 hour / 2";
        String[] expected = {"1 hour", "/", "2"};
        // Act - call the method to be tested
        String[] actual = TimeCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }
    @Test
    void splitCalculationWithDivisionAndUnit() {
        // Arrange - define input and expected outputs
        String input = "1 hour / 15 minute";
        String[] expected = {"1 hour", "/", "15 minute"};
        // Act - call the method to be tested
        String[] actual = TimeCalculator.splitCalculation(input);
        // Assert - call one of the assert methods
        assertArrayEquals(expected, actual);
    }

    @Test
    void parceTime(){
        // Arrange - define input and expected outputs
        String input = "1 hour";
        Time expected = new Time(1,"hour");
        // Act - call the method to be tested
        Time actual = TimeCalculator.parseTime(input);
        // Assert - call one of the assert methods
        assertEquals(expected.toString(), actual.toString());
    }
    @Test
    void parceTimeNoUnit(){
        // Arrange - define input and expected outputs
        String input = "1";

        Class expected = IllegalArgumentException.class;
        // Act - call the method to be tested
        Executable actual = () -> TimeCalculator.parseTime(input);
        // Assert - call one of the assert methods
        assertThrows(expected, actual);
    }
    @Test
    void parceTimeWrongFormat(){
        // Arrange - define input and expected outputs
        String input = "1hour";
        Class expected = IllegalArgumentException.class;
        // Act - call the method to be tested
        Executable actual = () -> TimeCalculator.parseTime(input);
        // Assert - call one of the assert methods
        assertThrows(expected, actual);
    }
}