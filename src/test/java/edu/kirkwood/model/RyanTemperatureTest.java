package edu.kirkwood.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RyanTemperatureTest {

    @Test
    @DisplayName("Celsius to Fahrenheit freezing point")
    void celsiusToFahrenheitFreezingPoint() {
        assertEquals(32.0, RyanTemperature.celsiusToFahrenheit(0));
    }

    @Test
    @DisplayName("Fahrenheit to Celsius boiling point")
    void fahrenheitToCelsiusBoilingPoint() {
        assertEquals(100.0, RyanTemperature.fahrenheitToCelsius(212));
    }

    @Test
    @DisplayName("Celsius to negative Fahrenheit")
    void celsiusToFahrenheitNegative() {
        assertEquals(14.0, RyanTemperature.celsiusToFahrenheit(-10));
    }

    @Test
    @DisplayName("Celsius to Kelvin absolute zero")
    void celsiusToKelvinAbsoluteZero() {
        assertEquals(0, RyanTemperature.celsiusToKelvin(-273.15));
    }

    @Test
    @DisplayName("Kelvin to Celsius freezing point")
    void kelvinToCelsiusFreezingPoint() {
        assertEquals(0, RyanTemperature.kelvinToCelsius(273.15));
    }

    @Test
    @DisplayName("Kelvin to negative Celsius")
    void negativeCelsiusToKelvin() {
        assertEquals(-5, RyanTemperature.kelvinToCelsius(268.15));
    }

    @Test
    @DisplayName("Fahrenheit to Kelvin absolute zero")
    void fahrenheitToKelvinAbsoluteZero() {
        assertEquals(0, RyanTemperature.fahrenheitToKelvin(-459.67));
    }

    @Test
    @DisplayName("Kelvin to Fahrenheit boiling point")
    void kelvinToFahrenheitBoilingPoint() {
        assertEquals(212, RyanTemperature.kelvinToFahrenheit(373.15));
    }

    @Test
    @DisplayName("Kelvin to negative Fahrenheit")
    void kelvinToFahrenheitNegative() {
        assertEquals(-32, Math.round(RyanTemperature.kelvinToFahrenheit(237.594)));
    }

    @Test
    void testCelsiusToFahrenheitNaNThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            double result = RyanTemperature.celsiusToFahrenheit(Double.NaN);
            if (Double.isNaN(result)) {
                throw new IllegalArgumentException("NaN not allowed");
            }
        });
    }
}