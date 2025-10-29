package edu.kirkwood.model; // This should be in your test source root, e.g., src/test/java/edu/kirkwood/model

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Depreciation} model.
 * Verifies the correctness of depreciation calculations and proper handling
 * of input constraints, adhering to the Arrange-Act-Assert pattern.
 */

class DepreciationTest {

    private Depreciation depreciationAsset;
    private int initialCost;
    private int initialSalvage;
    private int initialLife;

    /**
     * Sets up a common Depreciation object before each test method runs.
     */
    @BeforeEach
    void setUp() {
        initialCost = 100000;
        initialSalvage = 10000;
        initialLife = 5;
        // Arrange: Create a standard Depreciation object for most tests
        depreciationAsset = new Depreciation(initialCost, initialSalvage, initialLife);
    }

    // --- Constructor Tests (Minimum 8 methods total for this class) ---

    @Test
    void testConstructorValidInputs() {
        // Arrange is done in @BeforeEach
        assertEquals(new Fraction(initialCost, 1), depreciationAsset.getAssetCost());
        // Act: Object is created
        assertEquals(new Fraction(initialSalvage, 1), depreciationAsset.getSalvageValue());
        // Assert: Verify state
        assertEquals(initialLife, depreciationAsset.getUsefulLife());
    }

    @Test
    void testConstructorThrowsForZeroUsefulLife() {
        // Arrange: Define invalid inputs
        int badLife = 0;
        // Act & Assert: Verify exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new Depreciation(initialCost, initialSalvage, badLife);
        }, "Should throw for zero useful life.");
    }

    @Test
    void testConstructorThrowsForNegativeUsefulLife() {
        // Arrange: Define invalid inputs
        int badLife = -5;
        // Act & Assert: Verify exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new Depreciation(initialCost, initialSalvage, badLife);
        }, "Should throw for negative useful life.");
    }

    @Test
    void testConstructorThrowsForCostLessThanSalvage() {
        // Arrange: Define invalid inputs
        int badCost = 1000;
        int goodSalvage = 2000;
        // Act & Assert: Verify exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            new Depreciation(badCost, goodSalvage, initialLife);
        }, "Should throw if asset cost is less than salvage value.");
    }

    // --- Setter Tests ---

    @Test
    void testSetAssetCostValid() {
        // Arrange
        int newCost = 120000;
        // Act: Modify the state
        depreciationAsset.setAssetCost(newCost);
        // Assert: Verify the new state
        assertEquals(new Fraction(newCost, 1), depreciationAsset.getAssetCost(), "Asset cost should be updated.");
    }

    @Test
    void testSetAssetCostThrowsIfLessThanSalvage() {
        // Arrange: Current salvage is 10000. Try to set cost lower.
        int invalidNewCost = 5000;
        // Act & Assert: Verify exception
        assertThrows(IllegalArgumentException.class, () -> {
            depreciationAsset.setAssetCost(invalidNewCost);
        }, "Setting asset cost below salvage value should throw an exception.");
    }

    @Test
    void testSetSalvageValueValid() {
        // Arrange 
        int newSalvage = 5000;
        // Act: Modify the state
        depreciationAsset.setSalvageValue(newSalvage);
        // Assert: Verify the new state
        assertEquals(new Fraction(newSalvage, 1), depreciationAsset.getSalvageValue(), "Salvage value should be updated.");
    }

    @Test
    void testSetSalvageValueThrowsIfGreaterThanCost() {
        // Arrange: Try to set salvage higher.
        int invalidNewSalvage = 110000;
        // Act & Assert: Verify exception
        assertThrows(IllegalArgumentException.class, () -> {
            depreciationAsset.setSalvageValue(invalidNewSalvage);
        }, "Setting salvage value above asset cost should throw an exception.");
    }

    @Test
    void testSetUsefulLifeValid() {
        // Arrange 
        int newLife = 7;
        // Act: Modify the state
        depreciationAsset.setUsefulLife(newLife);
        // Assert: Verify the new state
        assertEquals(newLife, depreciationAsset.getUsefulLife(), "Useful life should be updated.");
    }

    @Test
    void testSetUsefulLifeThrowsForZero() {
        // Arrange
        int invalidLife = 0;
        // Act & Assert: Verify exception
        assertThrows(IllegalArgumentException.class, () -> {
            depreciationAsset.setUsefulLife(invalidLife);
        }, "Setting useful life to zero should throw an exception.");
    }
    

    @Test
    void testCalculateAnnualDepreciationBasic() {
        // Arrange 
        Fraction expectedAnnualDep = new Fraction(18000, 1); // (100000 - 10000) / 5 = 18000
        // Act: Perform the calculation
        Fraction actualAnnualDep = depreciationAsset.calculateAnnualDepreciation();
        // Assert: Compare actual to expected
        assertEquals(expectedAnnualDep, actualAnnualDep, "Annual depreciation should be 18000/1.");
    }

    @Test
    void testCalculateAnnualDepreciationNoSalvage() {
        // Arrange: Setup asset with no salvage
        depreciationAsset = new Depreciation(50000, 0, 10);
        Fraction expectedAnnualDep = new Fraction(5000, 1); // (50000 - 0) / 10 = 5000
        // Act: Perform the calculation
        Fraction actualAnnualDep = depreciationAsset.calculateAnnualDepreciation();
        // Assert: Compare actual to expected
        assertEquals(expectedAnnualDep, actualAnnualDep, "Annual depreciation should be 5000/1 when salvage is zero.");
    }

    @Test
    void testCalculateAnnualDepreciationSalvageEqualsCost() {
        // Arrange: Setup asset where salvage equals cost
        depreciationAsset = new Depreciation(50000, 50000, 5);
        Fraction expectedAnnualDep = new Fraction(0, 1); // (50000 - 50000) / 5 = 0
        // Act: Perform the calculation
        Fraction actualAnnualDep = depreciationAsset.calculateAnnualDepreciation();
        // Assert: Compare actual to expected
        assertEquals(expectedAnnualDep, actualAnnualDep, "Annual depreciation should be 0/1 when salvage equals cost.");
    }

    @Test
    void testCalculateAccumulatedDepreciationIntermediateYear() {
        // Arrange 
        int years = 2;
        Fraction expectedAccumulatedDep = new Fraction(36000, 1); // 18000 * 2 = 36000
        // Act: Perform the calculation
        Fraction actualAccumulatedDep = depreciationAsset.calculateAccumulatedDepreciation(years);
        // Assert: Compare actual to expected
        assertEquals(expectedAccumulatedDep, actualAccumulatedDep, "Accumulated depreciation after 2 years should be 36000/1.");
    }

    @Test
    void testCalculateAccumulatedDepreciationZeroYears() {
        // Arrange 
        int years = 0;
        Fraction expectedAccumulatedDep = new Fraction(0, 1);
        // Act: Perform the calculation
        Fraction actualAccumulatedDep = depreciationAsset.calculateAccumulatedDepreciation(years);
        // Assert: Compare actual to expected
        assertEquals(expectedAccumulatedDep, actualAccumulatedDep, "Accumulated depreciation after 0 years should be 0/1.");
    }

    @Test
    void testCalculateAccumulatedDepreciationFullLife() {
        // Arrange 
        int years = initialLife; // 5 years
        Fraction expectedAccumulatedDep = new Fraction(initialCost - initialSalvage, 1); // 90000/1
        // Act: Perform the calculation
        Fraction actualAccumulatedDep = depreciationAsset.calculateAccumulatedDepreciation(years);
        // Assert: Compare actual to expected
        assertEquals(expectedAccumulatedDep, actualAccumulatedDep, "Accumulated depreciation at full life should equal depreciable base.");
    }

    @Test
    void testCalculateAccumulatedDepreciationThrowsForNegativeYears() {
        // Arrange
        int invalidYears = -1;
        // Act & Assert: Verify exception
        assertThrows(IllegalArgumentException.class, () -> {
            depreciationAsset.calculateAccumulatedDepreciation(invalidYears);
        }, "Calculating accumulated depreciation for negative years should throw an exception.");
    }

    @Test
    void testCalculateAccumulatedDepreciationThrowsForExceedingLife() {
        // Arrange
        int invalidYears = initialLife + 1; // 6 years
        // Act & Assert: Verify exception
        assertThrows(IllegalArgumentException.class, () -> {
            depreciationAsset.calculateAccumulatedDepreciation(invalidYears);
        }, "Calculating accumulated depreciation for years beyond useful life should throw an exception.");
    }

    @Test
    void testCalculateItemValueIntermediateYear() {
        // Arrange: cost=100000, salvage=10000, life=5. Annual=18000.
        int years = 3;
        Fraction expectedItemValue = new Fraction(46000, 1);
        // Act: Perform the calculation
        Fraction actualItemValue = depreciationAsset.calculateItemValue(years);
        // Assert: Compare actual to expected
        assertEquals(expectedItemValue, actualItemValue, "Item value after 3 years should be 46000/1.");
    }

    @Test
    void testCalculateItemValueZeroYears() {
        // Arrange
        int years = 0;
        Fraction expectedItemValue = new Fraction(initialCost, 1);
        // Act: Perform the calculation
        Fraction actualItemValue = depreciationAsset.calculateItemValue(years);
        // Assert: Compare actual to expected
        assertEquals(expectedItemValue, actualItemValue, "Item value at 0 years should equal initial asset cost.");
    }

    @Test
    void testCalculateItemValueEndOfLife() {
        // Arrange
        int years = initialLife; // 5 years
        Fraction expectedItemValue = new Fraction(initialSalvage, 1);
        // Act: Perform the calculation
        Fraction actualItemValue = depreciationAsset.calculateItemValue(years);
        // Assert: Compare actual to expected
        assertEquals(expectedItemValue, actualItemValue, "Item value at end of useful life should equal salvage value.");
    }

    @Test
    void testCalculateItemValueThrowsForNegativeYears() {
        // Arrange
        int invalidYears = -1;
        // Act & Assert: Verify exception
        assertThrows(IllegalArgumentException.class, () -> {
            depreciationAsset.calculateItemValue(invalidYears);
        }, "Calculating Item value for negative years should throw an exception.");
    }

    @Test
    void testCalculateItemValueThrowsForExceedingLife() {
        // Arrange
        int invalidYears = initialLife + 1; // 6 years
        // Act & Assert: Verify exception
        assertThrows(IllegalArgumentException.class, () -> {
            depreciationAsset.calculateItemValue(invalidYears);
        }, "Calculating Item value for years beyond useful life should throw an exception.");
    }

    @Test
    void testEqualsTrue() {
        // Arrange: Create two identical objects
        Depreciation dep1 = new Depreciation(10000, 1000, 5);
        Depreciation dep2 = new Depreciation(10000, 1000, 5);
        // Act & Assert: Compare them
        assertTrue(dep1.equals(dep2), "Two Depreciation objects with same properties should be equal.");
    }

    @Test
    void testEqualsFalseDifferentCost() {
        // Arrange: Create two different objects
        Depreciation dep1 = new Depreciation(10000, 1000, 5);
        Depreciation dep2 = new Depreciation(12000, 1000, 5);
        // Act & Assert: Compare them
        assertFalse(dep1.equals(dep2), "Depreciation objects with different costs should not be equal.");
    }

    @Test
    void testEqualsFalseDifferentUsefulLife() {
        // Arrange: Create two different objects
        Depreciation dep1 = new Depreciation(10000, 1000, 5);
        Depreciation dep2 = new Depreciation(10000, 1000, 6);
        // Act & Assert: Compare them
        assertFalse(dep1.equals(dep2), "Depreciation objects with different useful lives should not be equal.");
    }

    @Test
    void testHashCodeConsistency() {
        // Arrange: Create two identical objects
        Depreciation dep1 = new Depreciation(10000, 1000, 5);
        Depreciation dep2 = new Depreciation(10000, 1000, 5);
        // Act & Assert: Compare their hash codes
        assertEquals(dep1.hashCode(), dep2.hashCode(), "Equal objects must have equal hash codes.");
    }
}