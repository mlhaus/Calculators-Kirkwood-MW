package edu.kirkwood.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AngleTest {

    private Angle degreeAngle1;
    private Angle degreeAngle2;
    private Angle degreeAngle3;
    private Angle radianAngle1;
    private Angle radianAngle2;

    @BeforeEach
    void setup() {
        degreeAngle1 = new Angle(45.0);
        degreeAngle2 = new Angle(37.3864);
        degreeAngle3 = new Angle(-390);
        radianAngle1 = new Angle(6.283184);
        radianAngle2 = new Angle(-45);
    }

    @Test
    void testCalcCosBasicAngle() {
        String actual = "Root(2)/2";
        String expected = degreeAngle1.calcCos();
        assertEquals(expected, actual);
    }

    @Test
    void testCalcCosDecimalAngle() {
        String actual = "0.79456";
        String expected = degreeAngle2.calcCos();
        assertEquals(expected, actual);
    }
    
    @Test
    void testCalcCosRadianDecimalAngle() {
        radianAngle1.correctAngle(false);
        String actual = "1";
        String expected = radianAngle1.calcCos();
        assertEquals(expected, actual);
    }

    @Test
    void testCalcSinNegModIntAngle() {
        degreeAngle3.correctAngle( true);
        String actual = "-1/2";
        String expected = degreeAngle3.calcSin();
        assertEquals(expected, actual);
    }

    @Test
    void testCalcSinRadianNegBasicIntAngle() {
        radianAngle2.correctAngle(false);
        String actual = "-0.8509";
        String expected = radianAngle2.calcSin();
        assertEquals(expected, actual);
    }

    @Test
    void testCalcSinNegative() {
        Angle anglePositive = new Angle(90);
        anglePositive.setQuadrant();
        Angle angleNegative = new Angle(270);
        angleNegative.setQuadrant();
        assertNotEquals(anglePositive.calcSin(), angleNegative.calcSin());
    }

    @Test
    void testCalcCosNegative() {
        Angle anglePositive = new Angle(0);
        anglePositive.setQuadrant();
        Angle angleNegative = new Angle(180);
        angleNegative.setQuadrant();
        assertNotEquals(anglePositive.calcCos(), angleNegative.calcCos());
    }

    @Test
    void testCorrectAngleDegrees() {
        double actual = 330;
        degreeAngle3.correctAngle(true);
        assertEquals(degreeAngle3.getAngle(), actual);
    }

    @Test
    void testCorrectAngleRadians() {
        double actual = 301.68992191129564;
        radianAngle2.correctAngle(false);
        assertEquals(radianAngle2.getAngle(), actual);
    }

    @Test
    void testSetQuadrant() {
        int originalQuadrant = degreeAngle1.getQuadrant(); //Quadrant 1
        degreeAngle1.setAngle(135); //Quadrant 2
        degreeAngle1.setQuadrant();
        assertNotEquals(originalQuadrant, degreeAngle1.getQuadrant());
    }

    @Test
    void testSetQuadrantOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> degreeAngle3.setQuadrant(), "Angle out of range [0, 360)");
    }

    @Test
    void testNewAngleObjectAndGetAngle() {
        Angle angleObject = new Angle(45);
        assertTrue(angleObject.getAngle() == 45);
    }
}