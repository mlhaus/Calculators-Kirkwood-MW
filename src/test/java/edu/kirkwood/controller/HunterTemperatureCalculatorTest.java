package edu.kirkwood.controller;

import edu.kirkwood.model.HunterTemperature;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HunterTemperatureCalculatorTest {

    @Test
    void splitUserInputWholeTemperature() {
        String input = "56 F to C";
        String[] expected = {"56","f","to","c"};
        String[] actual = HunterTemperatureCalculator.splitUserInput(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitUserInputDecimalTemperature() {
        String input = "56.13 F to K";
        String[] expected = {"56.13","f","to","k"};
        String[] actual = HunterTemperatureCalculator.splitUserInput(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitUserInputNegativeWholeTemperature() {
        String input = "-56 C to F";
        String[] expected = {"-56","c","to","f"};
        String[] actual = HunterTemperatureCalculator.splitUserInput(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitUserInputNegativeDecimalTemperature() {
        String input = "-56.13 C to K";
        String[] expected = {"-56.13","c","to","k"};
        String[] actual = HunterTemperatureCalculator.splitUserInput(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitUserInputTooManySpaces(){
        String input = "  56  K       to           C";
        String[] expected = {"56","k","to","c"};
        String[] actual = HunterTemperatureCalculator.splitUserInput(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitUserInputNoSpaces(){
        String input = "56ktoc";
        Exception e =  assertThrows(IllegalArgumentException.class, () -> HunterTemperatureCalculator.splitUserInput(input));

        String expectedError = "Invalid format";
        String actualError = e.getMessage();
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitUserInputFirstTypeMissing() {
        String input = " 56 to K";
        Exception e =  assertThrows(IllegalArgumentException.class, () -> HunterTemperatureCalculator.splitUserInput(input));

        String expectedError = "Invalid format";
        String actualError = e.getMessage();
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitUserInputSecondTypeMissing() {
        String input = "56.13 C to ";
        Exception e =  assertThrows(IllegalArgumentException.class, () -> HunterTemperatureCalculator.splitUserInput(input));

        String expectedError = "Invalid format";
        String actualError = e.getMessage();
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitUserInputInvalidTemperatureType1() {
        String input = "56.13 h to f";
        Exception e =  assertThrows(IllegalArgumentException.class, () -> HunterTemperatureCalculator.splitUserInput(input));

        String expectedError = "Invalid temperature type";
        String actualError = e.getMessage();
        assertTrue(actualError.contains(expectedError));
    }
    @Test
    void splitUserInputInvalidTemperatureType2() {
        String input = "56.13 k to o";
        Exception e =  assertThrows(IllegalArgumentException.class, () -> HunterTemperatureCalculator.splitUserInput(input));

        String expectedError = "Invalid temperature type";
        String actualError = e.getMessage();
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void splitUserInputMissingToInInput(){
        String input = "31 c t kelvin";
        Exception e =  assertThrows(IllegalArgumentException.class, () -> HunterTemperatureCalculator.splitUserInput(input));

        String expectedError = "Invalid format";
        String actualError = e.getMessage();
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void containsTwoTemperatureTypes(){
        List<String> input = Arrays.asList("56","f","to","k");
        boolean expected = true;
        boolean actual = HunterTemperatureCalculator.containsTwoTemperatureTypes(input);
        assertTrue(expected == actual);
    }

    @Test
    void testContainsTwoTemperatureTypesWithOneType(){
        List<String> input = Arrays.asList("56","kelvin","to","d");
        boolean expected = true;
        boolean actual = HunterTemperatureCalculator.containsTwoTemperatureTypes(input);
        assertFalse(expected == actual);
    }

    @Test
    void testContainsTwoTemperatureTypesWithFullName(){
        List<String> input = Arrays.asList("56","fahrenheit","to","Kelvin");
        boolean expected = true;
        boolean actual = HunterTemperatureCalculator.containsTwoTemperatureTypes(input);
        assertTrue(expected == actual);
    }

    @Test
    void testparseTemperature(){
        String[] input = {"56","f","to","k"};
        HunterTemperature expected = new HunterTemperature(56,"f");
        HunterTemperature actual = HunterTemperatureCalculator.parseTemperature(input);
        assertEquals(expected.getTemperature(),actual.getTemperature());
        assertEquals(expected.getTemperatureType(),actual.getTemperatureType());
    }
    @Test
    void testparseTemperatureIllegalArgumentException(){
        String[] input = {"56nd","f","to","k"};
        Exception e = assertThrows(IllegalArgumentException.class, () -> HunterTemperatureCalculator.parseTemperature(input));

        String expectedError = "Invalid temperature value";
        String actualError = e.getMessage();
        assertTrue(actualError.contains(expectedError));
    }
}