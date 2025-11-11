// file: /src/test/java/edu/kirkwood/model/UnitConverterTest.java v2 - Refactored to AAA pattern

package edu.kirkwood.model.edward;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UnitConverter utility using Arrange-Act-Assert pattern
 */
public class UnitConverterTest {

    private UnitConverter converter;

    @BeforeEach
    void setUp() {
        converter = new UnitConverter();
    }

    // ========== BASIC CONVERSION TESTS ==========
    @Test
    @DisplayName("Test volume conversion - tablespoons to teaspoons")
    void testVolumeConversionTbspToTsp() {
        // Arrange
        double quantity = 2.0;
        Unit fromUnit = Unit.TBSP;
        Unit toUnit = Unit.TSP;

        // Act
        double result = converter.convert(quantity, fromUnit, toUnit);

        // Assert
        assertEquals(6.0, result, 0.001);
    }

    @Test
    @DisplayName("Test volume conversion - cups to fluid ounces")
    void testVolumeConversionCupToFlOz() {
        // Arrange
        double quantity = 1.0;
        Unit fromUnit = Unit.CUP;
        Unit toUnit = Unit.FL_OZ;

        // Act
        double result = converter.convert(quantity, fromUnit, toUnit);

        // Assert
        assertEquals(8.0, result, 0.001);
    }

    @Test
    @DisplayName("Test volume conversion - teaspoons to cups")
    void testVolumeConversionTspToCup() {
        // Arrange
        double quantity = 48.0;
        Unit fromUnit = Unit.TSP;
        Unit toUnit = Unit.CUP;

        // Act
        double result = converter.convert(quantity, fromUnit, toUnit);

        // Assert
        assertEquals(1.0, result, 0.001);
    }

    @Test
    @DisplayName("Test weight conversion - pounds to ounces")
    void testWeightConversionLbToOz() {
        // Arrange
        double quantity = 1.0;
        Unit fromUnit = Unit.LB;
        Unit toUnit = Unit.OZ;

        // Act
        double result = converter.convert(quantity, fromUnit, toUnit);

        // Assert
        assertEquals(16.0, result, 0.1);
    }

    @Test
    @DisplayName("Test weight conversion - kilograms to grams")
    void testWeightConversionKgToGram() {
        // Arrange
        double quantity = 2.5;
        Unit fromUnit = Unit.KILOGRAM;
        Unit toUnit = Unit.GRAM;

        // Act
        double result = converter.convert(quantity, fromUnit, toUnit);

        // Assert
        assertEquals(2500.0, result, 0.001);
    }

    @Test
    @DisplayName("Test weight conversion - grams to kilograms")
    void testWeightConversionGramToKg() {
        // Arrange
        double quantity = 3000.0;
        Unit fromUnit = Unit.GRAM;
        Unit toUnit = Unit.KILOGRAM;

        // Act
        double result = converter.convert(quantity, fromUnit, toUnit);

        // Assert
        assertEquals(3.0, result, 0.001);
    }

    @Test
    @DisplayName("Test same unit conversion returns original value")
    void testSameUnitConversion() {
        // Arrange
        double cupQuantity = 5.0;
        double gramQuantity = 10.0;

        // Act
        double cupResult = converter.convert(cupQuantity, Unit.CUP, Unit.CUP);
        double gramResult = converter.convert(gramQuantity, Unit.GRAM, Unit.GRAM);

        // Assert
        assertEquals(5.0, cupResult, 0.001);
        assertEquals(10.0, gramResult, 0.001);
    }

    // ========== ERROR HANDLING TESTS ==========
    @Test
    @DisplayName("Test incompatible unit conversion throws exception")
    void testIncompatibleUnits() {
        // Arrange
        double quantity = 1.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(quantity, Unit.CUP, Unit.OZ);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(quantity, Unit.GRAM, Unit.TSP);
        });
    }

    @Test
    @DisplayName("Test null fromUnit throws exception")
    void testNullFromUnit() {
        // Arrange
        double quantity = 1.0;
        Unit fromUnit = null;
        Unit toUnit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(quantity, fromUnit, toUnit);
        });
    }

    @Test
    @DisplayName("Test null toUnit throws exception")
    void testNullToUnit() {
        // Arrange
        double quantity = 1.0;
        Unit fromUnit = Unit.CUP;
        Unit toUnit = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(quantity, fromUnit, toUnit);
        });
    }

    @Test
    @DisplayName("Test both units null throws exception")
    void testBothUnitsNull() {
        // Arrange
        double quantity = 1.0;
        Unit fromUnit = null;
        Unit toUnit = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(quantity, fromUnit, toUnit);
        });
    }

    // ========== COMPATIBILITY TESTS ==========
    @Test
    @DisplayName("Test areCompatible method")
    void testAreCompatible() {
        // Arrange - various unit combinations

        // Act
        boolean tspCupCompatible = converter.areCompatible(Unit.TSP, Unit.CUP);
        boolean ozLbCompatible = converter.areCompatible(Unit.OZ, Unit.LB);
        boolean gramKgCompatible = converter.areCompatible(Unit.GRAM, Unit.KILOGRAM);
        boolean cupGramIncompatible = converter.areCompatible(Unit.CUP, Unit.GRAM);
        boolean tspOzIncompatible = converter.areCompatible(Unit.TSP, Unit.OZ);

        // Assert
        assertTrue(tspCupCompatible);
        assertTrue(ozLbCompatible);
        assertTrue(gramKgCompatible);
        assertFalse(cupGramIncompatible);
        assertFalse(tspOzIncompatible);
    }

    @Test
    @DisplayName("Test areCompatible with null units")
    void testAreCompatibleNull() {
        // Arrange
        Unit nullUnit = null;

        // Act
        boolean nullCupCompatible = converter.areCompatible(nullUnit, Unit.CUP);
        boolean cupNullCompatible = converter.areCompatible(Unit.CUP, nullUnit);
        boolean bothNullCompatible = converter.areCompatible(nullUnit, nullUnit);

        // Assert
        assertFalse(nullCupCompatible);
        assertFalse(cupNullCompatible);
        assertFalse(bothNullCompatible);
    }

    @Test
    @DisplayName("Test areCompatible with same unit")
    void testAreCompatibleSameUnit() {
        // Arrange - same units

        // Act
        boolean cupSelfCompatible = converter.areCompatible(Unit.CUP, Unit.CUP);
        boolean gramSelfCompatible = converter.areCompatible(Unit.GRAM, Unit.GRAM);

        // Assert
        assertTrue(cupSelfCompatible);
        assertTrue(gramSelfCompatible);
    }

    // ========== UTILITY METHOD TESTS ==========
    @Test
    @DisplayName("Test getConversionFactor")
    void testGetConversionFactor() {
        // Arrange
        Unit fromUnit1 = Unit.TBSP;
        Unit toUnit1 = Unit.TSP;
        Unit fromUnit2 = Unit.TSP;
        Unit toUnit2 = Unit.TBSP;

        // Act
        double factor1 = converter.getConversionFactor(fromUnit1, toUnit1);
        double factor2 = converter.getConversionFactor(fromUnit2, toUnit2);

        // Assert
        assertEquals(3.0, factor1, 0.001);
        assertEquals(1.0/3.0, factor2, 0.001);
    }

    @Test
    @DisplayName("Test getConversionFactor same unit")
    void testGetConversionFactorSameUnit() {
        // Arrange
        Unit sameUnit = Unit.CUP;

        // Act
        double factor = converter.getConversionFactor(sameUnit, sameUnit);

        // Assert
        assertEquals(1.0, factor, 0.001);
    }

    @Test
    @DisplayName("Test getConversionFactor incompatible units")
    void testGetConversionFactorIncompatible() {
        // Arrange
        Unit volumeUnit = Unit.CUP;
        Unit weightUnit = Unit.GRAM;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            converter.getConversionFactor(volumeUnit, weightUnit);
        });
    }

    @Test
    @DisplayName("Test roundToPrecision")
    void testRoundToPrecision() {
        // Arrange
        double value = 1.23456789;

        // Act
        double rounded3 = converter.roundToPrecision(value, 3);
        double rounded2 = converter.roundToPrecision(value, 2);
        double rounded1 = converter.roundToPrecision(value, 1);
        double rounded0 = converter.roundToPrecision(value, 0);

        // Assert
        assertEquals(1.235, rounded3, 0.001);
        assertEquals(1.23, rounded2, 0.001);
        assertEquals(1.2, rounded1, 0.001);
        assertEquals(1.0, rounded0, 0.001);
    }

    @Test
    @DisplayName("Test convertAndRound")
    void testConvertAndRound() {
        // Arrange
        double quantity1 = 1.0;
        double quantity2 = 1.23456;

        // Act
        double result1 = converter.convertAndRound(quantity1, Unit.TBSP, Unit.TSP);
        double result2 = converter.convertAndRound(quantity2, Unit.CUP, Unit.TBSP);

        // Assert
        assertEquals(3.000, result1, 0.001);
        assertEquals(19.753, result2, 0.001); // 1.23456 * 16 = 19.75296, rounded to 19.753
    }

    @Test
    @DisplayName("Test getCompatibleUnits for volume")
    void testGetCompatibleUnitsVolume() {
        // Arrange
        Unit volumeUnit = Unit.CUP;

        // Act
        Unit[] compatibleUnits = converter.getCompatibleUnits(volumeUnit);

        // Assert
        assertEquals(6, compatibleUnits.length);
        for (Unit unit : compatibleUnits) {
            assertTrue(unit.isVolumeUnit());
        }
    }

    @Test
    @DisplayName("Test getCompatibleUnits for weight")
    void testGetCompatibleUnitsWeight() {
        // Arrange
        Unit weightUnit = Unit.GRAM;

        // Act
        Unit[] compatibleUnits = converter.getCompatibleUnits(weightUnit);

        // Assert
        assertEquals(4, compatibleUnits.length);
        for (Unit unit : compatibleUnits) {
            assertTrue(unit.isWeightUnit());
        }
    }

    @Test
    @DisplayName("Test getCompatibleUnits with null")
    void testGetCompatibleUnitsNull() {
        // Arrange
        Unit nullUnit = null;

        // Act
        Unit[] compatibleUnits = converter.getCompatibleUnits(nullUnit);

        // Assert
        assertEquals(0, compatibleUnits.length);
    }

    @Test
    @DisplayName("Test isValidConversion")
    void testIsValidConversion() {
        // Arrange
        double validQuantity = 1.0;
        double largeQuantity = 500.0;
        double negativeQuantity = -1.0;
        double zeroQuantity = 0.0;

        // Act
        boolean validConversion = converter.isValidConversion(validQuantity, Unit.CUP, Unit.TSP);
        boolean validWeightConversion = converter.isValidConversion(largeQuantity, Unit.GRAM, Unit.KILOGRAM);
        boolean incompatibleConversion = converter.isValidConversion(validQuantity, Unit.CUP, Unit.GRAM);
        boolean negativeConversion = converter.isValidConversion(negativeQuantity, Unit.CUP, Unit.TSP);
        boolean zeroConversion = converter.isValidConversion(zeroQuantity, Unit.CUP, Unit.TSP);

        // Assert
        assertTrue(validConversion);
        assertTrue(validWeightConversion);
        assertFalse(incompatibleConversion); // Incompatible
        assertFalse(negativeConversion); // Negative quantity
        assertFalse(zeroConversion); // Zero quantity
    }

    // ========== PRECISION TESTS ==========
    @Test
    @DisplayName("Test conversion precision with small values")
    void testConversionPrecisionSmall() {
        // Arrange
        double smallQuantity = 0.001;
        Unit fromUnit = Unit.TSP;
        Unit toUnit = Unit.TBSP;

        // Act
        double result = converter.convert(smallQuantity, fromUnit, toUnit);

        // Assert
        assertTrue(result > 0);
        assertEquals(0.001/3.0, result, 0.000001);
    }

    @Test
    @DisplayName("Test conversion precision with large values")
    void testConversionPrecisionLarge() {
        // Arrange
        double largeQuantity = 1000.0;
        Unit fromUnit = Unit.KILOGRAM;
        Unit toUnit = Unit.GRAM;

        // Act
        double result = converter.convert(largeQuantity, fromUnit, toUnit);

        // Assert
        assertEquals(1000000.0, result, 0.1);
    }

    // ========== COMPLEX CONVERSION TESTS ==========
    @Test
    @DisplayName("Test complex volume conversions")
    void testComplexVolumeConversions() {
        // Arrange
        double quarts = 1.0;

        // Act - Test chain: quart -> pint -> cup -> fl oz -> tbsp -> tsp
        double pints = converter.convert(quarts, Unit.QUART, Unit.PINT);
        double cups = converter.convert(pints, Unit.PINT, Unit.CUP);
        double flOz = converter.convert(cups, Unit.CUP, Unit.FL_OZ);
        double tbsp = converter.convert(flOz, Unit.FL_OZ, Unit.TBSP);
        double tsp = converter.convert(tbsp, Unit.TBSP, Unit.TSP);

        // Assert
        assertEquals(2.0, pints, 0.001);
        assertEquals(4.0, cups, 0.001);
        assertEquals(32.0, flOz, 0.001);
        assertEquals(64.0, tbsp, 0.001);
        assertEquals(192.0, tsp, 0.001);
    }

    @Test
    @DisplayName("Test complex weight conversions")
    void testComplexWeightConversions() {
        // Arrange
        double kg = 2.0;

        // Act - Test chain: kg -> g -> oz -> lb
        double grams = converter.convert(kg, Unit.KILOGRAM, Unit.GRAM);
        double oz = converter.convert(grams, Unit.GRAM, Unit.OZ);
        double lb = converter.convert(oz, Unit.OZ, Unit.LB);

        // Assert
        assertEquals(2000.0, grams, 0.001);
        assertEquals(70.548, oz, 0.1); // 2000 / 28.3495
        assertEquals(4.409, lb, 0.1); // 70.548 / 16
    }

    // ========== OBJECT METHOD TESTS ==========
    @Test
    @DisplayName("Test toString")
    void testToString() {
        // Arrange - using converter

        // Act
        String result = converter.toString();

        // Assert
        assertTrue(result.contains("UnitConverter"));
        assertTrue(result.contains("precision"));
        assertTrue(result.contains("3")); // DECIMAL_PRECISION
    }
}