package edu.kirkwood.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConeCalculatorTest {

    //Radius
    @Test
    void testCalculateRadius() {
        // Arrange
        double height = 3.0;
        double slantHeight = 5.0;
        double expected = 4.0;

        // Act
        double result = Cone.calculateRadius(height, slantHeight);

        // Assert
        assertEquals(expected, result);
    }

    //Height
    @Test
    void testCalculateHeight() {
        //Arrange
        double radius = 4.0;
        double slantHeight = 5.0;
        double expected = 3.0;

        // Act
        double result = Cone.calculateHeight(radius, slantHeight);

        // Assert
        assertEquals(expected, result);
    }

    //Slant Height
    @Test
    void testCalculateSlantHeight() {
        // Arrange
        double radius = 3.0;
        double height = 4.0;
        double expected = 5.0;

        // Act
        double result = Cone.calculateSlantHeight(radius, height);

        // Assert
        assertEquals(expected, result);
    }


    //Radius
    @Test
    void testCalculateRadius2() {
        // Arrange
        double height = 5.0;
        double slantHeight = 12.0;
        double expected = 10.908712114635714;

        // Act
        double result = Cone.calculateRadius(height, slantHeight);

        // Assert
        assertEquals(expected, result);
    }

    //Height
    @Test
    void testCalculateHeight2() {
        // Arrange
        double radius = 9.0;
        double slantHeight = 15.0;
        double expected = 12.0;

        // Act
        double result = Cone.calculateHeight(radius, slantHeight);

        // Assert
        assertEquals(expected, result);
    }

    //Slant Height
    @Test
    void testCalculateSlantHeight2() {
        // Arrange
        double radius = 7.0;
        double height = 24.0;
        double expected = 25.0;

        // Act
        double result = Cone.calculateSlantHeight(radius, height);

        // Assert
        assertEquals(expected, result);
    }

    //Volume
    @Test
    void testVolume() {
        // Arrange
        Cone cone = new Cone(3, 4, 5.0); // slantHeight doesn’t affect volume

        // Act
        double actual = cone.getVolume();

        // Assert
        double expected = Math.PI * Math.pow(3, 2) * 4 / 3;
        assertEquals(expected, actual);
    }

    //Lateral surface (curved surface)
    @Test
    void testLateralSurfaceArea() {
        // Arrange
        Cone cone = new Cone(3, 4, 5.0);

        // Act
        double actual = cone.getLateralSurfaceArea();

        // Assert
        double expected = Math.PI * 3 * 5;
        assertEquals(expected, actual);
    }

    //total surface area
    @Test
    void testTotalSurfaceArea() {
        // Arrange
        //
        Cone cone = new Cone(3, 4, 5.0);

        // Act
        double actual = cone.getTotalSurfaceArea();

        // Assert
        double expected = Math.PI * 3 * (3 + 5);
        assertEquals(expected, actual);
    }
}