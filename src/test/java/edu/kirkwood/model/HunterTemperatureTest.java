package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HunterTemperatureTest {

    private HunterTemperature temperature1;
    private HunterTemperature temperature2;

    @BeforeEach
    void setUp() {
        temperature1 = new HunterTemperature();
        temperature2 = new HunterTemperature(78, "kelvin");
    }

    @Test
    void getTemperature() {
        assertEquals(0, temperature1.getTemperature());
        assertEquals(78, temperature2.getTemperature());
    }

    @Test
    void getTemperatureType() {
        assertEquals("fahrenheit", temperature1.getTemperatureType());
        assertEquals("kelvin", temperature2.getTemperatureType());
    }

    @Test
    void setTemperature() {
        // Arrange
        double inputF = 4;
        double expectedF = 4;
        // Act
        temperature1.setTemperatureType("fahrenheit");
        temperature1.setTemperature(inputF);
        // Assert
        assertEquals(expectedF, temperature1.getTemperature());

        // Arrange
        double inputC = 4;
        double expectedC = 4;
        // Act
        temperature1.setTemperatureType("celsius");
        temperature1.setTemperature(inputC);
        // Assert
        assertEquals(expectedC, temperature1.getTemperature());

        // Arrange
        double inputK = 4;
        double expectedK = 4;
        // Act
        temperature1.setTemperatureType("kelvin");
        temperature1.setTemperature(inputK);
        // Assert
        assertEquals(expectedK, temperature1.getTemperature());
    }

    @Test
    void setTemperatureFahrenheitLowerBound() {
        // Arrange
        double input = -459.67;
        double expected = -459.67;
        // Act
        temperature1.setTemperatureType("fahrenheit");
        temperature1.setTemperature(input);
        // Assert
        assertEquals(expected, temperature1.getTemperature());

        // Arrange
        double lowerInput = -459.68;
        // Act
        temperature1.setTemperatureType("fahrenheit");
        Executable actual = () -> temperature1.setTemperature(lowerInput);
        // Assert
        assertThrows(IllegalArgumentException.class, actual);
    }

    @Test
    void setTemperatureCelsiusLowerBound() {
        // Arrange
        double input = -273.15;
        double expected = -273.15;
        // Act
        temperature1.setTemperatureType("celsius");
        temperature1.setTemperature(input);
        // Assert
        assertEquals(expected, temperature1.getTemperature());

        // Arrange
        double lowerInput = -273.16;
        // Act
        temperature1.setTemperatureType("celsius");
        Executable actual = () -> temperature1.setTemperature(lowerInput);
        // Assert
        assertThrows(IllegalArgumentException.class, actual);
    }

    @Test
    void setTemperatureKelvinLowerBound() {
        // Arrange
        double input = 0;
        double expected = 0;
        // Act
        temperature1.setTemperatureType("kelvin");
        temperature1.setTemperature(input);
        // Assert
        assertEquals(expected, temperature1.getTemperature());

        // Arrange
        double lowerInput = -1;
        // Act
        temperature1.setTemperatureType("kelvin");
        Executable actual = () -> temperature1.setTemperature(lowerInput);
        // Assert
        assertThrows(IllegalArgumentException.class, actual);
    }

    @Test
    void setTemperatureTypeFahrenheit() {
        // Arrange
        String inputF = "f";
        String expectedF = "f";
        // Act
        temperature1.setTemperatureType(inputF);
        // Assert
        assertEquals(expectedF, temperature1.getTemperatureType());

        // Arrange
        String inputFahrenheit = "FAHRENHEIT";
        String expectedFahrenheit = "fahrenheit";
        // Act
        temperature1.setTemperatureType(inputFahrenheit);
        // Assert
        assertEquals(expectedFahrenheit, temperature1.getTemperatureType());
    }

    @Test
    void setTemperatureTypeCelsius() {
        // Arrange
        String inputC = "c";
        String expectedC = "c";
        // Act
        temperature1.setTemperatureType(inputC);
        // Assert
        assertEquals(expectedC, temperature1.getTemperatureType());

        // Arrange
        String inputCelsius = "CELSIUS";
        String expectedCelsius = "celsius";
        // Act
        temperature1.setTemperatureType(inputCelsius);
        // Assert
        assertEquals(expectedCelsius, temperature1.getTemperatureType());
    }

    @Test
    void setTemperatureTypeKelvin() {
        // Arrange
        String inputK = "k";
        String expectedK = "k";
        // Act
        temperature1.setTemperatureType(inputK);
        // Assert
        assertEquals(expectedK, temperature1.getTemperatureType());

        // Arrange
        String inputKelvin = "KELVIN";
        String expectedKelvin = "kelvin";
        // Act
        temperature1.setTemperatureType(inputKelvin);
        // Assert
        assertEquals(expectedKelvin, temperature1.getTemperatureType());
    }

    @Test
    void setTemperatureTypeError() {
        // Arrange
        String inputError = "Invalid";
        // Act
        Executable actual = () -> temperature1.setTemperatureType(inputError);
        // Assert
        assertThrows(IllegalArgumentException.class, actual);
    }

    @Test
    void testToString() {

        // Arrange
        String expected = "kelvin: 78.0";
        // Act
        String actual = temperature2.toString();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToFahrenheitFromFahrenheit() {
        // Arrange
        double expected = 42;
        // Act
        temperature2.setTemperatureType("fahrenheit");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToFahrenheit();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToFahrenheitFromCelsius() {
        // Arrange
        double expected = 107.6;
        // Act
        temperature2.setTemperatureType("celsius");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToFahrenheit();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToFahrenheitFromKelvin() {
        // Arrange
        double expected = -384.07;
        // Act
        temperature2.setTemperatureType("kelvin");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToFahrenheit();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToCelsiusFromFahrenheit() {
        // Arrange
        double expected = 5.56;
        // Act
        temperature2.setTemperatureType("fahrenheit");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToCelsius();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToCelsiusFromCelsius() {
        // Arrange
        double expected = 42;
        // Act
        temperature2.setTemperatureType("celsius");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToCelsius();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToCelsiusFromKelvin() {
        // Arrange
        double expected = -231.15;
        // Act
        temperature2.setTemperatureType("kelvin");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToCelsius();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToKelvinFromFahrenheit() {
        // Arrange
        double expected = 278.71;
        // Act
        temperature2.setTemperatureType("fahrenheit");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToKelvin();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToKelvinFromCelsius() {
        // Arrange
        double expected = 315.15;
        // Act
        temperature2.setTemperatureType("celsius");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToKelvin();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testConvertToKelvinFromKelvin() {
        // Arrange
        double expected = 42;
        // Act
        temperature2.setTemperatureType("kelvin");
        temperature2.setTemperature(42);
        double actual = temperature2.convertToKelvin();
        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void testFormatDecimalNumbers(){
        // Arrange
        double expected = 78.29;
        // Act
        double actual = temperature1.Format(78.291);
        // Assert
        assertEquals(expected,actual);

        // Arrange
        double expectedRound = 78.3;
        // Act
        double actualRound = temperature1.Format(78.299);
        // Assert
        assertEquals(expectedRound,actualRound);

        // Arrange
        double expectedNegative = -78;
        // Act
        double actualNegative = temperature1.Format(-78.000001);
        // Assert
        assertEquals(expectedNegative,actualNegative);
    }

}