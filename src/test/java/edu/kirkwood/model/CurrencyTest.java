package edu.kirkwood.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyTest {

    @Test
    public void testConvertEURtoUSD() {
        // Arrange
        Currency currency = new Currency(10.0, "EUR");

        // Act
        double result = currency.convert("USD");

        // Assert
        assertEquals(11.0, result, 0.01);
    }

    @Test
    public void testConvertJPYtoGBP() {
        // Arrange
        Currency currency = new Currency(1000.0, "JPY");

        // Act
        double result = currency.convert("GBP");

        // Assert
        double expected = (1000 * 0.0067) / 1.3;
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testParseCurrency() {
        // Arrange
        Currency currency = new Currency();

        // Act
        double parsed = currency.parseCurrency("$123.45");

        // Assert
        assertEquals(123.45, parsed);
    }

    @Test
    public void testSimplifyValue() {
        // Arrange
        Currency currency = new Currency();

        // Act
        double result = currency.simplify(12.3456);

        // Assert
        assertEquals(12.35, result, 0.001);
    }

    @Test
    public void testAddDifferentCurrencies() {
        // Arrange
        Currency currency = new Currency();

        // Act
        double result = currency.addDifferentCurrencies(10.0, "USD", 10.0, "EUR");

        // Assert
        double expected = 10.0 + (10.0 * 1.1);
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testSubDifferentCurrencies() {
        // Arrange
        Currency currency = new Currency();

        // Act
        double result = currency.subDifferentCurrencies(20.0, "USD", 10.0, "EUR");

        // Assert
        double expected = 20.0 - (10.0 * 1.1);
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testMultiplyDifferentCurrencies() {
        // Arrange
        Currency currency = new Currency();

        // Act
        double result = currency.multiplyDifferentCurrencies(10.0, "EUR");

        // Assert
        assertEquals(11.0, result, 0.01);
    }

    @Test
    public void testDivideDifferentCurrencies() {
        // Arrange
        Currency currency = new Currency();

        // Act
        double result = currency.divideDifferentCurrencies(11.0, "EUR");

        // Assert
        assertEquals(10.0, result, 0.01);
    }

    @Test
    public void testUnsupportedCurrencyConvert() {
        // Arrange
        Currency currency = new Currency(10.0, "XYZ");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            currency.convert("USD");
        });
    }

    @Test
    public void testUnsupportedCurrencyOperation() {
        // Arrange
        Currency currency = new Currency();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            currency.multiplyDifferentCurrencies(10.0, "XYZ");
        });
    }
}
