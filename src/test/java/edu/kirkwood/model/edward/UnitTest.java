// file: /src/test/java/edu/kirkwood/model/UnitTest.java v2 - Refactored to AAA pattern

package edu.kirkwood.model.edward;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Unit enum using Arrange-Act-Assert pattern
 */
public class UnitTest {

    // ========== ENUM STRUCTURE TESTS ==========
    @Test
    @DisplayName("Test all volume units exist")
    void testVolumeUnitsExist() {
        // Arrange - N/A for enum existence

        // Act - N/A for enum existence

        // Assert
        assertNotNull(Unit.TSP);
        assertNotNull(Unit.TBSP);
        assertNotNull(Unit.FL_OZ);
        assertNotNull(Unit.CUP);
        assertNotNull(Unit.PINT);
        assertNotNull(Unit.QUART);
    }

    @Test
    @DisplayName("Test all weight units exist")
    void testWeightUnitsExist() {
        // Arrange - N/A for enum existence

        // Act - N/A for enum existence

        // Assert
        assertNotNull(Unit.OZ);
        assertNotNull(Unit.LB);
        assertNotNull(Unit.GRAM);
        assertNotNull(Unit.KILOGRAM);
    }

    @Test
    @DisplayName("Test volume units have correct type")
    void testVolumeUnitsType() {
        // Arrange - volume units

        // Act
        UnitType tspType = Unit.TSP.getType();
        UnitType tbspType = Unit.TBSP.getType();
        UnitType flOzType = Unit.FL_OZ.getType();
        UnitType cupType = Unit.CUP.getType();
        UnitType pintType = Unit.PINT.getType();
        UnitType quartType = Unit.QUART.getType();

        // Assert
        assertEquals(UnitType.VOLUME, tspType);
        assertEquals(UnitType.VOLUME, tbspType);
        assertEquals(UnitType.VOLUME, flOzType);
        assertEquals(UnitType.VOLUME, cupType);
        assertEquals(UnitType.VOLUME, pintType);
        assertEquals(UnitType.VOLUME, quartType);
    }

    @Test
    @DisplayName("Test weight units have correct type")
    void testWeightUnitsType() {
        // Arrange - weight units

        // Act
        UnitType ozType = Unit.OZ.getType();
        UnitType lbType = Unit.LB.getType();
        UnitType gramType = Unit.GRAM.getType();
        UnitType kgType = Unit.KILOGRAM.getType();

        // Assert
        assertEquals(UnitType.WEIGHT, ozType);
        assertEquals(UnitType.WEIGHT, lbType);
        assertEquals(UnitType.WEIGHT, gramType);
        assertEquals(UnitType.WEIGHT, kgType);
    }

    // ========== GETTER TESTS ==========
    @Test
    @DisplayName("Test getAbbreviation")
    void testGetAbbreviation() {
        // Arrange - units to test

        // Act
        String tspAbbrev = Unit.TSP.getAbbreviation();
        String tbspAbbrev = Unit.TBSP.getAbbreviation();
        String cupAbbrev = Unit.CUP.getAbbreviation();
        String ozAbbrev = Unit.OZ.getAbbreviation();
        String gramAbbrev = Unit.GRAM.getAbbreviation();

        // Assert
        assertEquals("tsp", tspAbbrev);
        assertEquals("tbsp", tbspAbbrev);
        assertEquals("cup", cupAbbrev);
        assertEquals("oz", ozAbbrev);
        assertEquals("g", gramAbbrev);
    }

    @Test
    @DisplayName("Test getFullName")
    void testGetFullName() {
        // Arrange - units to test

        // Act
        String tspName = Unit.TSP.getFullName();
        String tbspName = Unit.TBSP.getFullName();
        String cupName = Unit.CUP.getFullName();
        String ozName = Unit.OZ.getFullName();
        String gramName = Unit.GRAM.getFullName();

        // Assert
        assertEquals("teaspoon", tspName);
        assertEquals("tablespoon", tbspName);
        assertEquals("cup", cupName);
        assertEquals("ounce", ozName);
        assertEquals("gram", gramName);
    }

    @Test
    @DisplayName("Test getConversionFactor")
    void testGetConversionFactor() {
        // Arrange - units to test

        // Act
        double tspFactor = Unit.TSP.getConversionFactor();
        double tbspFactor = Unit.TBSP.getConversionFactor();
        double cupFactor = Unit.CUP.getConversionFactor();
        double ozFactor = Unit.OZ.getConversionFactor();
        double kgFactor = Unit.KILOGRAM.getConversionFactor();

        // Assert
        assertEquals(1.0, tspFactor, 0.001);
        assertEquals(3.0, tbspFactor, 0.001);
        assertEquals(48.0, cupFactor, 0.001);
        assertEquals(28.3495, ozFactor, 0.001);
        assertEquals(1000.0, kgFactor, 0.001);
    }

    // ========== COMPATIBILITY TESTS ==========
    @Test
    @DisplayName("Test unit compatibility - volume units")
    void testVolumeUnitsCompatible() {
        // Arrange - volume units

        // Act
        boolean tspCupCompatible = Unit.TSP.isCompatible(Unit.CUP);
        boolean tbspPintCompatible = Unit.TBSP.isCompatible(Unit.PINT);
        boolean flOzQuartCompatible = Unit.FL_OZ.isCompatible(Unit.QUART);
        boolean cupTspCompatible = Unit.CUP.isCompatible(Unit.TSP);

        // Assert
        assertTrue(tspCupCompatible);
        assertTrue(tbspPintCompatible);
        assertTrue(flOzQuartCompatible);
        assertTrue(cupTspCompatible);
    }

    @Test
    @DisplayName("Test unit compatibility - weight units")
    void testWeightUnitsCompatible() {
        // Arrange - weight units

        // Act
        boolean ozLbCompatible = Unit.OZ.isCompatible(Unit.LB);
        boolean gramKgCompatible = Unit.GRAM.isCompatible(Unit.KILOGRAM);
        boolean lbGramCompatible = Unit.LB.isCompatible(Unit.GRAM);

        // Assert
        assertTrue(ozLbCompatible);
        assertTrue(gramKgCompatible);
        assertTrue(lbGramCompatible);
    }

    @Test
    @DisplayName("Test unit incompatibility - volume vs weight")
    void testVolumeWeightIncompatible() {
        // Arrange - volume and weight units

        // Act
        boolean cupOzIncompatible = Unit.CUP.isCompatible(Unit.OZ);
        boolean tspGramIncompatible = Unit.TSP.isCompatible(Unit.GRAM);
        boolean lbPintIncompatible = Unit.LB.isCompatible(Unit.PINT);
        boolean kgQuartIncompatible = Unit.KILOGRAM.isCompatible(Unit.QUART);

        // Assert
        assertFalse(cupOzIncompatible);
        assertFalse(tspGramIncompatible);
        assertFalse(lbPintIncompatible);
        assertFalse(kgQuartIncompatible);
    }

    @Test
    @DisplayName("Test isCompatible with null")
    void testIsCompatibleNull() {
        // Arrange
        Unit nullUnit = null;

        // Act
        boolean cupNullCompatible = Unit.CUP.isCompatible(nullUnit);
        boolean gramNullCompatible = Unit.GRAM.isCompatible(nullUnit);

        // Assert
        assertFalse(cupNullCompatible);
        assertFalse(gramNullCompatible);
    }

    @Test
    @DisplayName("Test self compatibility")
    void testSelfCompatibility() {
        // Arrange - same units

        // Act
        boolean cupSelfCompatible = Unit.CUP.isCompatible(Unit.CUP);
        boolean gramSelfCompatible = Unit.GRAM.isCompatible(Unit.GRAM);

        // Assert
        assertTrue(cupSelfCompatible);
        assertTrue(gramSelfCompatible);
    }

    // ========== UTILITY METHOD TESTS ==========
    @Test
    @DisplayName("Test isVolumeUnit")
    void testIsVolumeUnit() {
        // Arrange - volume and weight units

        // Act
        boolean tspIsVolume = Unit.TSP.isVolumeUnit();
        boolean cupIsVolume = Unit.CUP.isVolumeUnit();
        boolean ozIsVolume = Unit.OZ.isVolumeUnit();
        boolean gramIsVolume = Unit.GRAM.isVolumeUnit();

        // Assert
        assertTrue(tspIsVolume);
        assertTrue(cupIsVolume);
        assertFalse(ozIsVolume);
        assertFalse(gramIsVolume);
    }

    @Test
    @DisplayName("Test isWeightUnit")
    void testIsWeightUnit() {
        // Arrange - weight and volume units

        // Act
        boolean ozIsWeight = Unit.OZ.isWeightUnit();
        boolean gramIsWeight = Unit.GRAM.isWeightUnit();
        boolean tspIsWeight = Unit.TSP.isWeightUnit();
        boolean cupIsWeight = Unit.CUP.isWeightUnit();

        // Assert
        assertTrue(ozIsWeight);
        assertTrue(gramIsWeight);
        assertFalse(tspIsWeight);
        assertFalse(cupIsWeight);
    }

    @Test
    @DisplayName("Test getBaseUnit")
    void testGetBaseUnit() {
        // Arrange - units to test

        // Act
        Unit cupBaseUnit = Unit.CUP.getBaseUnit();
        Unit pintBaseUnit = Unit.PINT.getBaseUnit();
        Unit ozBaseUnit = Unit.OZ.getBaseUnit();
        Unit kgBaseUnit = Unit.KILOGRAM.getBaseUnit();

        // Assert
        assertEquals(Unit.TSP, cupBaseUnit);
        assertEquals(Unit.TSP, pintBaseUnit);
        assertEquals(Unit.GRAM, ozBaseUnit);
        assertEquals(Unit.GRAM, kgBaseUnit);
    }

    @Test
    @DisplayName("Test toBaseUnit conversion")
    void testToBaseUnit() {
        // Arrange
        double quantity = 1.0;

        // Act
        double tbspToBase = Unit.TBSP.toBaseUnit(quantity);
        double cupToBase = Unit.CUP.toBaseUnit(quantity);
        double kgToBase = Unit.KILOGRAM.toBaseUnit(quantity);

        // Assert
        assertEquals(3.0, tbspToBase, 0.001);
        assertEquals(48.0, cupToBase, 0.001);
        assertEquals(1000.0, kgToBase, 0.001);
    }

    @Test
    @DisplayName("Test fromBaseUnit conversion")
    void testFromBaseUnit() {
        // Arrange
        double tbspBaseQuantity = 3.0;
        double cupBaseQuantity = 48.0;
        double kgBaseQuantity = 1000.0;

        // Act
        double tbspFromBase = Unit.TBSP.fromBaseUnit(tbspBaseQuantity);
        double cupFromBase = Unit.CUP.fromBaseUnit(cupBaseQuantity);
        double kgFromBase = Unit.KILOGRAM.fromBaseUnit(kgBaseQuantity);

        // Assert
        assertEquals(1.0, tbspFromBase, 0.001);
        assertEquals(1.0, cupFromBase, 0.001);
        assertEquals(1.0, kgFromBase, 0.001);
    }

    // ========== STATIC METHOD TESTS ==========
    @Test
    @DisplayName("Test getVolumeUnits")
    void testGetVolumeUnits() {
        // Arrange - N/A for static method

        // Act
        Unit[] volumeUnits = Unit.getVolumeUnits();

        // Assert
        assertEquals(6, volumeUnits.length);
        for (Unit unit : volumeUnits) {
            assertTrue(unit.isVolumeUnit());
        }
    }

    @Test
    @DisplayName("Test getWeightUnits")
    void testGetWeightUnits() {
        // Arrange - N/A for static method

        // Act
        Unit[] weightUnits = Unit.getWeightUnits();

        // Assert
        assertEquals(4, weightUnits.length);
        for (Unit unit : weightUnits) {
            assertTrue(unit.isWeightUnit());
        }
    }

    @Test
    @DisplayName("Test findByAbbreviation")
    void testFindByAbbreviation() {
        // Arrange
        String tspAbbrev = "tsp";
        String cupAbbrev = "cup";
        String gramAbbrev = "g";
        String invalidAbbrev = "invalid";
        String nullAbbrev = null;

        // Act
        Unit tspResult = Unit.findByAbbreviation(tspAbbrev);
        Unit cupResult = Unit.findByAbbreviation(cupAbbrev);
        Unit gramResult = Unit.findByAbbreviation(gramAbbrev);
        Unit invalidResult = Unit.findByAbbreviation(invalidAbbrev);
        Unit nullResult = Unit.findByAbbreviation(nullAbbrev);

        // Assert
        assertEquals(Unit.TSP, tspResult);
        assertEquals(Unit.CUP, cupResult);
        assertEquals(Unit.GRAM, gramResult);
        assertNull(invalidResult);
        assertNull(nullResult);
    }

    @Test
    @DisplayName("Test findByAbbreviation case insensitive")
    void testFindByAbbreviationCaseInsensitive() {
        // Arrange
        String tspUpper = "TSP";
        String cupUpper = "CUP";
        String gramUpper = "G";

        // Act
        Unit tspResult = Unit.findByAbbreviation(tspUpper);
        Unit cupResult = Unit.findByAbbreviation(cupUpper);
        Unit gramResult = Unit.findByAbbreviation(gramUpper);

        // Assert
        assertEquals(Unit.TSP, tspResult);
        assertEquals(Unit.CUP, cupResult);
        assertEquals(Unit.GRAM, gramResult);
    }

    @Test
    @DisplayName("Test findByFullName")
    void testFindByFullName() {
        // Arrange
        String tspName = "teaspoon";
        String cupName = "cup";
        String gramName = "gram";
        String invalidName = "invalid";
        String nullName = null;

        // Act
        Unit tspResult = Unit.findByFullName(tspName);
        Unit cupResult = Unit.findByFullName(cupName);
        Unit gramResult = Unit.findByFullName(gramName);
        Unit invalidResult = Unit.findByFullName(invalidName);
        Unit nullResult = Unit.findByFullName(nullName);

        // Assert
        assertEquals(Unit.TSP, tspResult);
        assertEquals(Unit.CUP, cupResult);
        assertEquals(Unit.GRAM, gramResult);
        assertNull(invalidResult);
        assertNull(nullResult);
    }

    @Test
    @DisplayName("Test findByFullName case insensitive")
    void testFindByFullNameCaseInsensitive() {
        // Arrange
        String tspUpper = "TEASPOON";
        String cupUpper = "CUP";
        String gramUpper = "GRAM";

        // Act
        Unit tspResult = Unit.findByFullName(tspUpper);
        Unit cupResult = Unit.findByFullName(cupUpper);
        Unit gramResult = Unit.findByFullName(gramUpper);

        // Assert
        assertEquals(Unit.TSP, tspResult);
        assertEquals(Unit.CUP, cupResult);
        assertEquals(Unit.GRAM, gramResult);
    }

    // ========== VALIDATION TESTS ==========
    @Test
    @DisplayName("Test conversion factors are positive")
    void testConversionFactorsPositive() {
        // Arrange - all units

        // Act & Assert
        for (Unit unit : Unit.values()) {
            double factor = unit.getConversionFactor();
            assertTrue(factor > 0,
                    "Conversion factor for " + unit + " should be positive");
        }
    }

    @Test
    @DisplayName("Test abbreviations are not null or empty")
    void testAbbreviationsValid() {
        // Arrange - all units

        // Act & Assert
        for (Unit unit : Unit.values()) {
            String abbreviation = unit.getAbbreviation();
            assertNotNull(abbreviation);
            assertFalse(abbreviation.trim().isEmpty());
        }
    }

    @Test
    @DisplayName("Test full names are not null or empty")
    void testFullNamesValid() {
        // Arrange - all units

        // Act & Assert
        for (Unit unit : Unit.values()) {
            String fullName = unit.getFullName();
            assertNotNull(fullName);
            assertFalse(fullName.trim().isEmpty());
        }
    }

    // ========== STRING METHOD TESTS ==========
    @Test
    @DisplayName("Test toString")
    void testToString() {
        // Arrange - units to test

        // Act
        String tspString = Unit.TSP.toString();
        String gramString = Unit.GRAM.toString();

        // Assert
        assertTrue(tspString.contains("tsp"));
        assertTrue(tspString.contains("teaspoon"));
        assertTrue(gramString.contains("g"));
        assertTrue(gramString.contains("gram"));
    }
}