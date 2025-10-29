package edu.kirkwood.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DistanceUnitTest {
    // Tests distance unit conversions
    @Test
    @DisplayName("Method: Tests: Converting miles to kilometers.")
    void testConvertMilesToKilometers() {
        // Arrange
        double expectedValue = 8.0467;
        double inputValue = 5.0;

        // Act
        double actualValue = DistanceUnit.convert(inputValue, DistanceUnit.MILE, DistanceUnit.KILOMETER);

        // Assert
        assertEquals(expectedValue, actualValue, 0.001);
    }


    @Test
    @DisplayName("Method: Tests: Converting centimeters to feet.")
    void testConvertCentimetersToFeet() {
        // Arrange
        double expectedValue = 32.80839895;
        double inputValue = 1000.0;

        // Act
        double actualValue = DistanceUnit.convert(inputValue, DistanceUnit.CENTIMETER, DistanceUnit.FOOT);

        // Assert
        assertEquals(expectedValue, actualValue, 0.001);
    }


    @Test
    @DisplayName("Method: convert Tests: Converting kilometers to inches.")
    void testConvertKilometersToInches() {
        // Arrange
        double expectedValue = 98425.19685;
        double inputValue = 2.5;

        // Act
        double actualValue = DistanceUnit.convert(inputValue, DistanceUnit.KILOMETER, DistanceUnit.INCH);

        // Assert
        assertEquals(expectedValue, actualValue, 0.001);
    }


    // Tests distance unit names
    @Test
    @DisplayName("Method: getSingularUnitName: Tests the accessor for the associated singular name for a unit of measurement.")
    void testGetSingularUnitMame() {
        // Arrange
        String expected = "foot";
        DistanceUnit mile = DistanceUnit.FOOT;

        // Act
        String actual = mile.getSingularUnitName();

        // Assert
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Method: getPluralUnitName: Tests the accessor for the associated plural name for a unit of measurement.")
    void testPluralUnitName() {
        // Arrange
        String expected = "feet";
        DistanceUnit mile = DistanceUnit.FOOT;

        // Act
        String actual = mile.getPluralUnitName();

        // Assert
        assertEquals(expected, actual);
    }

    // Tests formating values
    @Test
    @DisplayName("Method: formatValue Tests: Formatting the string output for values and an associated unit of measurement.")
    void testFormatSingularValue() {
        // Arrange
        String expected = "1.0 mile";
        DistanceUnit mile = DistanceUnit.MILE;

        // Act
        String actual = mile.formatValue(1.0);
        String pluralResult = mile.formatValue(5.0);

        // Assert
        assertEquals(expected, actual);
        assertEquals("5.0 miles", pluralResult);
    }


    @Test
    @DisplayName("Method: formatValue: Tests: Formatting the string output for values and an associated unit of measurement.")
    void testFormatPluralValue() {
        // Arrange
        String expected = "5.0 miles";
        DistanceUnit mile = DistanceUnit.MILE;

        // Act
        String actual = mile.formatValue(5.0);

        // Assert
        assertEquals(expected, actual);
    }


    // Tests exception handling
    @Test
    @DisplayName("Method: parseUnit Tests: Exception handling when parsing a string that does not match any existing unit.")
    void testExceptionParseUnitWithNonExistingUnitName() {
        // Arrange
        String invalidInput = "gooftric";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> DistanceUnit.parseUnit(invalidInput));
    }


    // Tests invalid conversions
    @Test
    @DisplayName("Method: convert Tests: Exception handling when converting to the same unit of measurement.")
    void testExceptionConvertToSameUnitOfMeasurement() {
        // Arrange
        double value = 123.45;

        // Act
        Executable action = () -> DistanceUnit.convert(value, DistanceUnit.FOOT, DistanceUnit.FOOT);

        // Assert
        assertThrows(IllegalArgumentException.class, action);
    }

}