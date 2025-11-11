package edu.kirkwood.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import edu.kirkwood.model.Angle;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;

public class UnitCircleCalculatorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Test start() loop with a valid angle")
    void startWithValidInput() {
        try (
            MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
            MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class);) {
                // "Arrange" code by Gemini. Prompt: Help me write unit tests using Mockito for the `start()` method in this Java class. 
                // Arrange
                // 1. User chooses "radians"
                staticUserInput.when(() -> UserInput.getString(eq("Is your angle in degrees or radians?"), eq(true)))
                        .thenReturn("radians");
                // 2. User enters 3 for the angle
                staticUserInput.when(() -> UserInput.getDouble(anyString(), eq(true)))
                        .thenReturn(3.0);
                // 3. User chooses "n" to not calculate another angle
                staticUserInput.when(() -> UserInput.getString(eq("Would you like to calculate another angle?"), eq(true)))
                        .thenReturn("n");
                // Act
                UnitCircleCalculator.start();
                // Assert
                staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }

    }

    @Test
    @DisplayName("Test start() loop with an invalid angle")
    void startWithInvalidIsDegrees() {
        try (MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
            MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

                // "Arrange" code by Marc Hauschildt
                // Arrange
                // 1. User types "deg" as an incorrect input message, then types "degrees"
                staticUserInput.when(() -> UserInput.getString(eq("Is your angle in degrees or radians?"), eq(true)))
                        .thenReturn("deg", "degrees");
                // 2. User enters 45 for the angle
                staticUserInput.when(() -> UserInput.getDouble(anyString(), eq(true)))
                        .thenReturn(45.0);
                // 3. User chooses "n" to not calculate another angle
                staticUserInput.when(() -> UserInput.getString(eq("Would you like to calculate another angle?"), eq(true)))
                        .thenReturn("n");
                // Act
                UnitCircleCalculator.start();

                // Assert: Verify that displayError was called exactly ONCE with the expected message.
                staticUIUtility.verify(() -> UIUtility.displayError(eq("Input must be either \"Degrees\" or \"Radians\"")), times(1));
        }
    }

    @Test
    @DisplayName("Checks if formatInput() returns a value")
    void formatInputOutputTest() {
        // Arrange
        Angle angle = new Angle(46.0);
        boolean isDegrees = true;
        // Act
        String formattedInput = "";
        formattedInput = UnitCircleCalculator.formatInput(angle.getAngle(), isDegrees);
        // Assert
        assertFalse(formattedInput.isEmpty());
    }

    @Test
    @DisplayName("Checks if formatInput() returns a proper message when given a radian value")
    void formatInputRadiansTest() {
        // Arrange
        Angle angle = new Angle(4);
        boolean isDegrees = false;
        angle.correctAngle(isDegrees);
        String expected = "4 radians";
        // Act
        String actual = UnitCircleCalculator.formatInput(Math.toRadians(angle.getAngle()), isDegrees);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Checks if formatInput() returns a proper message when given a degree value")
    void formatInputDegreesTest() {
        // Arrange
        Angle angle = new Angle(93.5);
        boolean isDegrees = true;
        String expected = "93.5 degrees";
        // Act
        String actual = UnitCircleCalculator.formatInput(angle.getAngle(), isDegrees);
        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("Checks if printAngleDetails() returns a proper message when given a radian value")
    void printAngleDetailsRadiansTest() {
        // Arrange
        Angle angle = new Angle(4);
        boolean isDegrees = false;
        angle.correctAngle(isDegrees);
        String expected = "The sin of 4 radians is -0.7568; the cos of 4 radians is -0.65364; your angle is in quadrant 3.\n\n";
        // Act
        UnitCircleCalculator.printAngleDetails(angle, isDegrees);
        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    @DisplayName("Checks if printAngleDetails() returns a proper message when given one of the seventeen basic degree value")
    void printAngleDetailsBasicAngleDegreesTest() {
        // Arrange
        Angle angle = new Angle(45);
        boolean isDegrees = true;
        angle.correctAngle(isDegrees);
        String expected = "The sin of 45 degrees is Root(2)/2; the cos of 45 degrees is Root(2)/2; your angle is in quadrant 1.\n\n";
        // Act
        UnitCircleCalculator.printAngleDetails(angle, isDegrees);
        // Assert
        assertEquals(expected, outContent.toString());
    }
}
