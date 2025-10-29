// file: /src/test/java/edu/kirkwood/model/UnitTypeTest.java v2 - Refactored to AAA pattern

package edu.kirkwood.model.edward;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UnitType enum using Arrange-Act-Assert pattern
 */
public class UnitTypeTest {

    @Test
    @DisplayName("Test UnitType values exist")
    void testUnitTypeValues() {
        // Arrange - N/A for enum existence

        // Act
        UnitType[] values = UnitType.values();

        // Assert
        assertNotNull(UnitType.VOLUME);
        assertNotNull(UnitType.WEIGHT);
        assertEquals(2, values.length);
    }

    @Test
    @DisplayName("Test getDisplayName")
    void testGetDisplayName() {
        // Arrange - enum values

        // Act
        String volumeDisplayName = UnitType.VOLUME.getDisplayName();
        String weightDisplayName = UnitType.WEIGHT.getDisplayName();

        // Assert
        assertEquals("Volume", volumeDisplayName);
        assertEquals("Weight", weightDisplayName);
    }

    @Test
    @DisplayName("Test getDescription")
    void testGetDescription() {
        // Arrange - enum values

        // Act
        String volumeDescription = UnitType.VOLUME.getDescription();
        String weightDescription = UnitType.WEIGHT.getDescription();

        // Assert
        assertEquals("Liquid and dry volume measurements", volumeDescription);
        assertEquals("Mass and weight measurements", weightDescription);
    }

    @Test
    @DisplayName("Test findByDisplayName")
    void testFindByDisplayName() {
        // Arrange
        String volumeName = "Volume";
        String weightName = "Weight";
        String invalidName = "Invalid";
        String nullName = null;

        // Act
        UnitType volumeResult = UnitType.findByDisplayName(volumeName);
        UnitType weightResult = UnitType.findByDisplayName(weightName);
        UnitType invalidResult = UnitType.findByDisplayName(invalidName);
        UnitType nullResult = UnitType.findByDisplayName(nullName);

        // Assert
        assertEquals(UnitType.VOLUME, volumeResult);
        assertEquals(UnitType.WEIGHT, weightResult);
        assertNull(invalidResult);
        assertNull(nullResult);
    }

    @Test
    @DisplayName("Test findByDisplayName case insensitive")
    void testFindByDisplayNameCaseInsensitive() {
        // Arrange
        String volumeLower = "volume";
        String weightUpper = "WEIGHT";
        String volumeProper = "Volume";

        // Act
        UnitType volumeLowerResult = UnitType.findByDisplayName(volumeLower);
        UnitType weightUpperResult = UnitType.findByDisplayName(weightUpper);
        UnitType volumeProperResult = UnitType.findByDisplayName(volumeProper);

        // Assert
        assertEquals(UnitType.VOLUME, volumeLowerResult);
        assertEquals(UnitType.WEIGHT, weightUpperResult);
        assertEquals(UnitType.VOLUME, volumeProperResult);
    }

    @Test
    @DisplayName("Test toString")
    void testToString() {
        // Arrange - enum values

        // Act
        String volumeString = UnitType.VOLUME.toString();
        String weightString = UnitType.WEIGHT.toString();

        // Assert
        assertEquals("Volume", volumeString);
        assertEquals("Weight", weightString);
    }
}