package edu.kirkwood.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EthansTemperatureTest {

    @Test
    void testCelsiusToFahrenheit() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(0, "C");

        // Act
        double result = temp.toFahrenheit();

        // Assert
        assertEquals(32, result, 0.001);
    }

    @Test
    void testFahrenheitToCelsius() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(32, "F");

        // Act
        double result = temp.toCelsius();

        // Assert
        assertEquals(0, result, 0.001);
    }

    @Test
    void testKelvinToCelsius() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(273.15, "K");

        // Act
        double result = temp.toCelsius();

        // Assert
        assertEquals(0, result, 0.001);
    }

    @Test
    void testKelvinToFahrenheit() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(273.15, "K");

        // Act
        double result = temp.toFahrenheit();

        // Assert
        assertEquals(32, result, 0.001);
    }

    @Test
    void testCelsiusToKelvin() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(100, "C");

        // Act
        double result = temp.toKelvin();

        // Assert
        assertEquals(373.15, result, 0.001);
    }

    @Test
    void testInvalidScale() {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new EthansTemperature(100, "X").toCelsius());
    }

    @Test
    void testGetters() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(25, "C");

        // Act
        double value = temp.getValue();
        String scale = temp.getScale();

        // Assert
        assertEquals(25, value);
        assertEquals("C", scale);
    }

    @Test
    void testFreezingPoint() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(32, "F");

        // Act
        double result = temp.toCelsius();

        // Assert
        assertTrue(result == 0);
    }

    @Test
    void testBoilingPoint() {
        // Arrange
        EthansTemperature temp = new EthansTemperature(100, "C");

        // Act
        double result = temp.toFahrenheit();

        // Assert
        assertTrue(result == 212);
    }

    @Test
    void setScaleValid() {
        EthansTemperature t = new EthansTemperature(0, "C");
        t.setScale("F");
        assertEquals("F", t.getScale());
    }

    @Test
    void setScaleInvalidThrows() {
        EthansTemperature t = new EthansTemperature(0, "C");
        assertThrows(IllegalArgumentException.class, () -> t.setScale("X"));
    }

    @Test
    void setValueValid() {
        EthansTemperature t = new EthansTemperature(0, "C");
        t.setValue("100");
        assertEquals(100, t.getValue());
    }

    @Test
    void setValueBelowAbsoluteZeroThrows() {
        EthansTemperature t = new EthansTemperature(0, "C");
        assertThrows(ArithmeticException.class, () -> t.setValue("-300"));
    }
}
