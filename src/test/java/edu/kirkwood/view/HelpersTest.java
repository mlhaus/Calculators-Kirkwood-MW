package edu.kirkwood.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static edu.kirkwood.view.Helpers.isDateInRange;
import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {
    // Dillon
    private double number;
    private int decimalPlaces;

    @BeforeEach
    void setUp()
    {
        number = 1.59151678;
        decimalPlaces = 2;
    }

    // Jason
    @Test
    void isValidStringValidInputs() {
        assertTrue(Helpers.isValidString("string"));
        assertTrue(Helpers.isValidString("Another String"));
        assertTrue(Helpers.isValidString("Jason"));
        assertTrue(Helpers.isValidString("Marc"));
        assertTrue(Helpers.isValidString("Yet Another String"));
        assertTrue(Helpers.isValidString("string2"));
        assertTrue(Helpers.isValidString("string3"));
    }

    // Jason
    @Test
    void isValidStringInvalidInputs() {
        assertFalse(Helpers.isValidString(""));
        assertFalse(Helpers.isValidString(null));
    }

    // Gabe
    @Test
    void isValidParameterValues(){
        // Arrange
        double expected = 1.59;
        // Act
        double actual = Double.parseDouble(Helpers.round(number, decimalPlaces));
        // Assert
        assertEquals(expected,actual);
    }

    // Gabe
    @Test
    void isInvalidParameterValues(){
        // Arrange
        String badInput = "apple";
        String goodInput = "1.543";
        double expected = 1.54;
        // Act
        double actual = Double.parseDouble(Helpers.round(Double.parseDouble(goodInput), decimalPlaces));
        //Assert
        assertEquals(expected,actual);
        assertThrows(NumberFormatException.class, () -> Helpers.round(Double.parseDouble(badInput), decimalPlaces));
    }

    // Hunter
    @Test
    void DateIsInThePast() {
        // arrange
        LocalDate input = LocalDate.of(1970, 1, 1);
        boolean expected = true;
        // act
        boolean actual = Helpers.isDateInThePast(input);
        // assert
        assertEquals(expected, actual);
    }

    // Hunter
    @Test
    void DateIsNotInThePast() {
        // arrange
        LocalDate input = LocalDate.now();
        // act
        boolean actual = Helpers.isDateInThePast(input);
        // assert
        assertFalse(actual);
    }

    // Hunter
    @Test
    void DateIsInTheFuture() {
        // Arrange - define input and expected outputs
        LocalDate input = null;
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> Helpers.isDateInThePast(input));
    }

    // Lizbeth
    @Test
    public void testFormatDateLong_WithTypicalDate() {
        LocalDate date = LocalDate.of(2025, 1, 1);
        String result = Helpers.formatDateLong(date);
        // Example expected: "January 1, 2025" — could vary based on locale
        assertTrue(result.contains("2025"), "Result should contain the year");
        assertTrue(result.toLowerCase().contains("jan") || result.toLowerCase().contains("january"),
                "Result should contain the month name");
        assertTrue(result.contains("1"), "Result should contain the day");
    }

    // Lizbeth
    @Test
    public void testFormatDateLong_WithEndOfYear() {
        LocalDate date = LocalDate.of(2025, 12, 31);
        String result = Helpers.formatDateLong(date);
        assertTrue(result.contains("2025"), "Result should contain the year");
        assertTrue(result.toLowerCase().contains("dec") || result.toLowerCase().contains("december"),
                "Result should contain the month name");
        assertTrue(result.contains("31"), "Result should contain the day");
    }

    // Lizbeth
    @Test
    public void testFormatDateLong_WithLeapDay() {
        LocalDate date = LocalDate.of(2024, 2, 29);
        String result = Helpers.formatDateLong(date);
        assertTrue(result.contains("2024"), "Result should contain the year");
        assertTrue(result.toLowerCase().contains("feb") || result.toLowerCase().contains("february"),
                "Result should contain the month name");
        assertTrue(result.contains("29"), "Result should contain the day");
    }

    // Edward
    @Test
    public void testFormatDateShort_WithSingleDigitMonthAndDay() {
        // Arrange
        LocalDate date = LocalDate.of(2025, 3, 5);

        // Act
        String result = Helpers.formatDateShort(date);

        // Assert
        assertEquals("3/5/2025", result, "Date with single-digit month and day should format without leading zeros");
    }

    // Edward
    @Test
    public void testFormatDateShort_WithDoubleDigitMonthAndDay() {
        // Arrange
        LocalDate date = LocalDate.of(2025, 12, 25);

        // Act
        String result = Helpers.formatDateShort(date);

        // Assert
        assertEquals("12/25/2025", result, "Date with double-digit month and day should format correctly");
    }

    // Edward
    @Test
    public void testFormatDateShort_WithLeapYearDate() {
        // Arrange
        LocalDate date = LocalDate.of(2024, 2, 29);

        // Act
        String result = Helpers.formatDateShort(date);

        // Assert
        assertEquals("2/29/2024", result, "Leap year date should format correctly");
    }

    // Yousif
    @Test
    void testPositiveAmount() {
        Locale.setDefault(Locale.US); // Ensure consistent formatting
        String result = Helpers.toCurrency(123.45);
        assertEquals("$123.45", result);
    }

    // Yousif
    @Test
    void testZeroAmount() {
        Locale.setDefault(Locale.US);
        String result = Helpers.toCurrency(0.0);
        assertEquals("$0.00", result);
    }

    // Calder
    @Test
    void testIsDateInRangeTrue() {
        //Arrange
        LocalDate date1 = LocalDate.of(2015, 4, 13);
        LocalDate date2 = LocalDate.of(2015, 4, 12);
        LocalDate date3 = LocalDate.of(2010, 6, 9);
        //Act
        boolean IDIR = isDateInRange(date2, date3, date1);
        //Assert
        assertTrue(IDIR);
    }

    // Calder
    @Test
    void testIsDateInRangeFalse() {
        //Arrange
        LocalDate date1 = LocalDate.of(2015, 4, 13);
        LocalDate date2 = LocalDate.of(2015, 5, 12);
        LocalDate date3 = LocalDate.of(2010, 6, 9);
        //Act
        boolean IDIR = isDateInRange(date2, date3, date1);
        //Assert
        assertFalse(IDIR);
    }

    // Calder
    @Test
    void testIsDateInRangeTrueInclusive() {
        //Arrange
        LocalDate date1 = LocalDate.of(2015, 4, 13);
        LocalDate date2 = LocalDate.of(2010, 6, 9);
        LocalDate date3 = LocalDate.of(2010, 6, 9);
        //Act
        boolean IDIR = isDateInRange(date2, date3, date1);
        //Assert
        assertTrue(IDIR);
    }
}