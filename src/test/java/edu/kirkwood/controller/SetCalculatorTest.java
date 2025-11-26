package edu.kirkwood.controller;

import edu.kirkwood.model.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SetCalculatorTest {

    @BeforeEach
    void setUp() {
        SetCalculator.setList.clear();

    }

    @Test
    void getSetNames() {
        // Arrange
        List<Set> setList = new ArrayList<>();
        setList.add(new Set("Alpha", new ArrayList<String>()));
        setList.add(new Set("Beta", new ArrayList<String>()));
        setList.add(new Set("Gamma",new ArrayList<String>()));

        // Act
        String[] actual = SetCalculator.getSetNames(setList);

        // Assert
        assertArrayEquals(new String[] {"Alpha", "Beta", "Gamma"}, actual);
    }

    @Test
    void getSetNamesWithNoSets() {
        // Arrange
        List<Set> setList = new ArrayList<>();

        // Act
        String[] actual = SetCalculator.getSetNames(setList);

        // Assert
        assertArrayEquals(new String[]{}, actual);
    }


    @Test
    void parseSetValuesWithNumbersAndSpaces() {
        // Arrange
        String userInput = "1, 2, 3, 4, 5, 6, 7, 8, 9";
        //Act
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9"));
        ArrayList<String> actual = SetCalculator.parseSetValues(userInput);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseSetValuesWithExtraSpaces() {
        // Arrange
        String userInput = "dog,   Cat, Cow   , Chicken, dog2, 6,  7 , 8 , 9 ";
        //Act
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("dog","Cat","Cow","Chicken","dog2","6","7","8","9"));
        ArrayList<String> actual = SetCalculator.parseSetValues(userInput);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseSetValuesWithExtraCommas() {
        // Arrange
        String userInput = ",dog,,,   Cat, Cow   , ,Chicken, ,dog2, 6,  7, , 8 , 9 ";
        //Act
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("dog","Cat","Cow","Chicken","dog2","6","7","8","9"));
        ArrayList<String> actual = SetCalculator.parseSetValues(userInput);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseSetValuesWithOnlyCommas() {
        // Arrange
        String userInput = ",    ,   ,";
        //Act
        ArrayList<String> expected = new ArrayList<String>();
        ArrayList<String> actual = SetCalculator.parseSetValues(userInput);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseSetValuesWithNoCommas() {
        // Arrange
        String userInput = "1";
        //Act
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("1"));
        ArrayList<String> actual = SetCalculator.parseSetValues(userInput);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseSetValuesWithNoInput() {
        // Arrange
        String userInput = "";
        //Act
        ArrayList<String> expected = new ArrayList<String>();
        ArrayList<String> actual = SetCalculator.parseSetValues(userInput);
        // Assert
        assertEquals(expected, actual);
    }
}