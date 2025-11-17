package edu.kirkwood.controller;

import edu.kirkwood.model.CharacterFighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterMoveSetCalc {


    @Test
    void parseUserInputGetNoCommas(){
        // Arrange
        String input = "";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                CharacterMoveCalc.parseUserInput(input));

        assertTrue(e.getMessage().contains("Invalid Format. Please enter the neccesary comma's needed for the format specified."));
    }

    @Test
    void parseUserInputGetIncorrectFormat(){
        // Arrange
        String Input = " , , , , ";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                CharacterMoveCalc.parseUserInput(Input));

        // Assert
        assertTrue(e.getMessage().contains("Invalid Format. Please enter exactly 5 commas for the format."));
    }

    @Test
    void getInvalidPlayer1MovestartupNumber(){
        // Arrange
        String Input = "-1,stand,High,1,Stand,High";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 1 Move startup. Please enter a positive integer"));
    }

    @Test
    void getInvalidPlayer1MoveStartupInput(){
        // Arrange
        String Input = "Apple,stand,High,1,Stand,High";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 1 Move startup. Please enter a valid integer"));
    }

    @Test
    void getInvalidPlayer2MovestartupNumber(){
        // Arrange
        String Input = "1,stand,High,-1,Stand,High";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 2 Move startup. Please enter a positive integer"));
    }

    @Test
    void getInvalidPlayer2MoveStartupInput(){
        // Arrange
        String Input = "1,stand,High,Apple,Stand,High";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 2 Move startup. Please enter a valid integer"));
    }

    @Test
    void getInvalidPlayer1CharacterStatus(){
        // Arrange
        String Input = "1,Hi,High,1,Stand,High";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 1 Character Status. Please enter \"Stand\", \"Crouch\", or \"Downed\""));
    }

    @Test
    void getInvalidPlayer2CharacterStatus(){
        // Arrange
        String Input = "1,Stand,High,1,Staaand,High";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 2 Character Status. Please enter \"Stand\", \"Crouch\", or \"Downed\""));
    }

    @Test
    void getInvalidPlayer1MoveType(){
        // Arrange
        String Input = "1,Stand,Meh,1,Stand,High";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 1 Move Type. Please enter \"High\", \"Medium\", \"Low\", \"Command Grab\", or \"Throw\""));
    }

    @Test
    void getInvalidPlayer2MoveType(){
        // Arrange
        String Input = "1,Stand,High,1,Stand,Meh";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> CharacterMoveCalc.parseUserInput(Input));
        // Assert
        assertTrue(e.getMessage().contains("Invalid Input for Player 2 Move Type. Please enter \"High\", \"Medium\", \"Low\", \"Command Grab\", or \"Throw\""));
    }

    @Test
    void parseUserInput_validInput_returnsParts() {
        String input = "5, stand, High, 10, Crouch, Throw";

        String[] result = CharacterMoveCalc.parseUserInput(input);

        assertArrayEquals(new String[]{"5", "stand", "high", "10", "crouch", "throw"}, result);
    }

    @Test
    void getComparisonResultsWin(){
        // Arrange
        CharacterFighter Player1 = new CharacterFighter(5, "stand", "high");
        CharacterFighter Player2 = new CharacterFighter(10, "stand", "high");

        String expected = "Win";
        // Act
        String actual = CharacterMoveCalc.getComparisonResults(Player1, Player2);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getComparisonResultsLose(){
        // Arrange
        CharacterFighter Player1 = new CharacterFighter(10, "stand", "high");
        CharacterFighter Player2 = new CharacterFighter(5, "stand", "high");

        String expected = "Lose";
        // Act
        String actual = CharacterMoveCalc.getComparisonResults(Player1, Player2);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void getComparisonResultsDraw(){
        // Arrange
        CharacterFighter Player1 = new CharacterFighter(1, "Stand", "High");
        CharacterFighter Player2 = new CharacterFighter(1, "Stand", "High");

        String expected = "Draw";
        // Act
        String actual = CharacterMoveCalc.getComparisonResults(Player1, Player2);
        // Assert
        assertEquals(expected, actual);
    }
}

