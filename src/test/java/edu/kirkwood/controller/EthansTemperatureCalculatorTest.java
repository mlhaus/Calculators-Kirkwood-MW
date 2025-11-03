package edu.kirkwood.controller;

import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EthansTemperatureCalculatorTest {

    @Test
    void startWithValidCelsiusToFahrenheit() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange - choice, value, scale, quit
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("2", "100", "C", "q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString())).thenAnswer(inv -> null);
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert - no errors shown
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    @Test
    void startWithValidFahrenheitToKelvin() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("3", "32", "F", "q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString())).thenAnswer(inv -> null);
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    @Test
    void startWithValidKelvinToCelsius() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("1", "273.15", "K", "q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString())).thenAnswer(inv -> null);
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    @Test
    void startWithInvalidNumber() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange - invalid numeric value
            List<String> errors = new ArrayList<>();
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("1", "notANumber", "q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString()))
                    .thenAnswer(inv -> { errors.add(inv.getArgument(0)); return null; });
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert - error about invalid number was shown
            assertTrue(errors.stream().anyMatch(s -> s.toLowerCase().contains("invalid number")));
        }
    }

    @Test
    void startWithBelowAbsoluteZero() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange - value below absolute zero
            List<String> errors = new ArrayList<>();
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("1", "-500", "C", "q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString()))
                    .thenAnswer(inv -> { errors.add(inv.getArgument(0)); return null; });
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert - error about absolute zero displayed
            assertTrue(errors.stream().anyMatch(s -> s.toLowerCase().contains("absolute")));
        }
    }

    @Test
    void startWithInvalidScale() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange - invalid scale
            List<String> errors = new ArrayList<>();
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("2", "100", "X", "q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString()))
                    .thenAnswer(inv -> { errors.add(inv.getArgument(0)); return null; });
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert - displayError called and pressEnterToContinue used (error path)
            assertFalse(errors.isEmpty());
            staticUIUtility.verify(UIUtility::pressEnterToContinue, atLeastOnce());
        }
    }

    @Test
    @DisplayName("Start quits immediately with q")
    void startQuitImmediately() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange - quit directly
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString())).thenAnswer(inv -> null);
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert - no errors shown
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    @Test
    void startWithInvalidMenuChoice() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)
        ) {
            // Arrange - invalid menu choice but provide value+scale so flow reaches default switch-case
            List<String> errors = new ArrayList<>();
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("99", "100", "C", "q");
            staticUIUtility.when(() -> UIUtility.displayError(anyString()))
                    .thenAnswer(inv -> { errors.add(inv.getArgument(0)); return null; });
            staticUIUtility.when(UIUtility::pressEnterToContinue).thenAnswer(inv -> null);

            // Act
            EthansTemperatureCalculator.start();

            // Assert - invalid choice message displayed
            assertTrue(errors.stream().anyMatch(s -> s.toLowerCase().contains("invalid choice")));
        }
    }
}
