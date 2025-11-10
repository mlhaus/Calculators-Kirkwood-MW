package edu.kirkwood.view;

import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInputTest {
    private Scanner mockScanner;
    private Scanner scanner;

    @BeforeEach
    void setup() throws Exception {
        mockScanner = mock(Scanner.class);

        // Robert
        Field scannerField = UserInput.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(null, mockScanner); // Null due to it being static

        // Lawson
        scanner = (Scanner) scannerField.get(null);
    }

    // Robert
    @Test
    @DisplayName("Tests a basic double input")
    void testValidDouble() {
        // Arrange
        Double expected = 5.5;
        when(mockScanner.nextLine()).thenReturn("5.5");

        // Act
        double actual = UserInput.getDouble("Enter a double: ");

        // Assert
        assertEquals(expected, actual);
    }

    // Robert
    @Test
    @DisplayName("Tests a double input within a given range")
    void testValidDoubleWithinRange() {
        // Arrange
        Double expected = 3.3;
        when(mockScanner.nextLine()).thenReturn( "3.3");

        // Act
        double actual = UserInput.getDouble("Enter a double: ", true, 0, 10);

        // Assert
        assertEquals(expected, actual);
    }

    // Dine
    @Test
    @DisplayName("Test getDouble with a valid input on the first try")
    void getDouble_ValidInput_ReturnsDouble() {
        // Arrange: Configure the mock scanner to return a valid double string
        when(mockScanner.nextLine()).thenReturn("85.5");

        // Act: Call the method under test
        double result = UserInput.getDouble("Enter your grade", true, 0.0, 100.0);

        // Assert: Check if the returned value is correct
        assertEquals(85.5, result, 0.001);
    }

    // Dine
    @Test
    @DisplayName("Test getDouble with invalid text, then a valid number")
    void getDouble_InvalidTextThenValidNumber_ReturnsDouble() {
        // Arrange: Simulate the user entering "abc" first, then "12.3"
        when(mockScanner.nextLine()).thenReturn("abc", "12.3");

        // Act: Call the method under test
        double result = UserInput.getDouble("Enter a value", true, 10.0, 20.0);

        // Assert: The method should re-prompt and eventually return the valid number
        assertEquals(12.3, result, 0.001);
    }

    // Lawson
    @Test
    void testGetInt_ValidInputWithinRange() throws Exception {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("10");
        Field scannerField = UserInput.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(null, mockScanner);
        try (MockedStatic<UserInput> mockedStatic = mockStatic(UserInput.class, CALLS_REAL_METHODS)) {
            int result = UserInput.getInt("Enter a number", true, 5, 20);
            assertEquals(10, result);
            verify(mockScanner, times(1)).nextLine();
        }
    }

    // Lawson
    @Test
    void testValidInteger() {
        int expected = 5;
        when(scanner.nextLine()).thenReturn("5");
        int actual = UserInput.getInt("Enter a number: ");
        assertEquals(expected, actual);
    }

    // Abraham
    @Test
    void testGetDate_ValidInput() {
        LocalDate expected = LocalDate.of(2025, 10, 8);
        try (MockedStatic<UserInput> mockedStatic = mockStatic(UserInput.class, CALLS_REAL_METHODS)) {
            // Mock the overloaded method that getDate(prompt) delegates to
            mockedStatic.when(() -> UserInput.getDate("Enter a date:", true)).thenReturn(expected);
            LocalDate actual = UserInput.getDate("Enter a date:");
            assertEquals(expected, actual);
        }
    }

    // Abraham
    @Test
    void testGetDate_DifferentPrompt() {
        LocalDate expected = LocalDate.of(1999, 12, 31);
        try (MockedStatic<UserInput> mockedStatic = mockStatic(UserInput.class, CALLS_REAL_METHODS)) {
            mockedStatic.when(() -> UserInput.getDate("Birthday:", true)).thenReturn(expected);
            LocalDate actual = UserInput.getDate("Birthday:");
            assertEquals(expected, actual);
        }
    }

    // Blake
    @Test
    void getBooleanReturnsTrueWithLowerY() {
        try (MockedStatic<UserInput> mockedUserInput = mockStatic(UserInput.class)) {
            // Arrange
            mockedUserInput.when(() -> UserInput.getString("Continue? [y/n]", true))
                    .thenReturn("y");
            mockedUserInput.when(() -> UserInput.getBoolean("Continue?", true))
                    .thenCallRealMethod();

            // Act
            boolean result = UserInput.getBoolean("Continue?", true);

            // Assert
            assertTrue(result);
            mockedUserInput.verify(() -> UserInput.getString("Continue? [y/n]", true), times(1));
        }
    }

    // Blake
    @Test
    void getBooleanReturnsFalseWithUpperNO() {
        try (MockedStatic<UserInput> mockedUserInput = mockStatic(UserInput.class)) {
            // Arrange
            mockedUserInput.when(() -> UserInput.getString("Continue? [y/n]", true))
                    .thenReturn("NO");
            mockedUserInput.when(() -> UserInput.getBoolean("Continue?", true))
                    .thenCallRealMethod();

            // Act
            boolean result = UserInput.getBoolean("Continue?", true);

            // Assert
            assertFalse(result);
        }
    }

    // Blake
    @Test
    void testGetBooleanReturnsErrorWithInvalidInput() {
        try (MockedStatic<UserInput> mockedUserInput = mockStatic(UserInput.class);
             MockedStatic<UIUtility> mockedUIUtility = mockStatic(UIUtility.class)) {

            // Arrange
            mockedUserInput.when(() -> UserInput.getString("Continue? [y/n]", true))
                    .thenReturn("invalid")  // First call returns invalid input
                    .thenReturn("maybe")    // Second call returns another invalid input
                    .thenReturn("y");       // Third call returns valid input

            mockedUIUtility.when(() -> UIUtility.displayError("Invalid input"))
                    .then(invocation -> null);

            mockedUserInput.when(() -> UserInput.getBoolean("Continue?", true))
                    .thenCallRealMethod();

            // Act
            boolean result = UserInput.getBoolean("Continue?", true);

            // Assert
            assertTrue(result);
            // getString should be called 3 times
            mockedUserInput.verify(() -> UserInput.getString("Continue? [y/n]", true), times(3));
            // displayError should be called 2 times
            mockedUIUtility.verify(() -> UIUtility.displayError("Invalid input"), times(2));
        }
    }
}