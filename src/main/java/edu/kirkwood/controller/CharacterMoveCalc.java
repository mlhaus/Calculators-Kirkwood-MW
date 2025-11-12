package edu.kirkwood.controller;

import edu.kirkwood.model.CharacterFighter;

import java.util.Objects;

import static edu.kirkwood.view.Messages.characterCalcGoodbye;
import static edu.kirkwood.view.Messages.characterCalcGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class CharacterMoveCalc {

    /**
     * Goes through the programs operations giving the instructions and calling the neccessary functions that
     * the system needs to get and produce the result
     */
    public static void start(){
        characterCalcGreet();
        while(true){
            String input = getString("Enter your character information (or 'q' to quit)");
            if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }
            // Validate the input
            String[] parts = null;
            try{
                parts = parseUserInput(input);
            } catch(IllegalArgumentException e){
                displayError(e.getMessage());
                continue; // restart the loop
            }
            //System.out.println(Arrays.toString(parts));

            //"Enter calculations in the format: [Character1 MoveStartup], [Character1 ChacracterStatus]," +
            //       " [Character1 MoveType], [Character2 MoveStartup], [Character2 ChacracterStatus], [Character2 MoveType]"
            int Character1MoveStartup = Integer.parseInt(parts[0]);
            String Character1ChacracterStatus = parts[1];
            String Character1MoveType = parts[2];
            int Character2MoveStartup = Integer.parseInt(parts[3]);
            String Character2ChacracterStatus = parts[4];
            String Character2MoveType = parts[5];

            CharacterFighter player1 = null;
            CharacterFighter player2 = null;

            try{
                player1 = new CharacterFighter(Character1MoveStartup, Character1ChacracterStatus, Character1MoveType);
                player2 = new CharacterFighter(Character2MoveStartup, Character2ChacracterStatus, Character2MoveType);
            }
            catch(IllegalArgumentException e){
                displayError(e.getMessage());
                continue; // restart the loop
            }
            /* This is just for testing to ensure everything is right after input
            System.out.println(player1.getMoveStartup());
            System.out.println(player1.getCharacterStatus());
            System.out.println(player1.getMoveType());
            System.out.println(player2.getMoveStartup());
            System.out.println(player2.getCharacterStatus());
            System.out.println(player2.getMoveType());
            */

            // Perform Comparisons
            String ComparisonResult = null;
            try{
                ComparisonResult = getComparisonResults(player1, player2);
            }
            catch(IllegalArgumentException e){
                displayError(e.getMessage());
            }


            // Display Results
            if(Objects.equals(ComparisonResult, "Win")){
                System.out.println("Player 1 successfully won the move comparison!");
            }
            else if(Objects.equals(ComparisonResult, "Lose")){
                System.out.println("Player 2 successfully won the move comparison!");
            }
            else if(Objects.equals(ComparisonResult, "Draw")){
                System.out.println("The move comparison turned out to be a draw.");
            }
            else{
                System.out.println("The move comparsion couldn't be calculated dude to invalid input.");
            }

        }
        characterCalcGoodbye();
        pressEnterToContinue();


    }

    /**
     * Checks the parameter given by the user to ensure it is in the correct format and each parameter
     * is a valid input/option for the specified parameter.
     * @param input The string the user has given
     * @return an array containing the user input in the correct format for the system to use.
     */
    public static String[] parseUserInput(String input) {
        String[] parts = null;

        if(input.contains(",")){
            parts = input.split(",");

            if(parts.length != 6){
                throw new IllegalArgumentException("Invalid Format. Please enter exactly 5 commas for the format.");
            }

            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim().toLowerCase(); // trims and lowercases for consistent comparison
            }

            // Validate Player 1 startup
            int p1Startup = parsePositiveInt(parts[0], "Player 1 Move startup");

            // Validate Player 1 status
            validateCharacterStatus(parts[1], "Player 1 Character Status");

            // Validate Player 1 move type
            validateMoveType(parts[2], "Player 1 Move Type");

            // Validate Player 2 startup
            int p2Startup = parsePositiveInt(parts[3], "Player 2 Move startup");

            // Validate Player 2 status
            validateCharacterStatus(parts[4], "Player 2 Character Status");

            // Validate Player 2 move type
            validateMoveType(parts[5], "Player 2 Move Type");

            return parts;
        }else{
            throw new IllegalArgumentException("Invalid Format. Please enter the neccesary comma's needed for the format specified.");
        }
    }

    /**
     * Does the comparison between the two characters based on what each parameter is.
     * @param player1 The CharacterFighter object for player 1 and its parameters
     * @param player2 The CharacterFighter object for player 2 and its parameters
     * @return A string with the result of the interaction from player 1's perspective
     */
    public static String getComparisonResults(CharacterFighter player1, CharacterFighter player2){
        String results = null;

        // If both of the characters have the same exact parameters
        if(player1.getMoveStartup() == player2.getMoveStartup() && player1.getMoveType().equals(player2.getMoveType())
                && player1.getCharacterStatus().equals(player2.getCharacterStatus())){
            results = "Draw";
        }


        if(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Hit") &&
                player1.CompareCharacterStatusToMoveType(player2.getMoveType()).equals("Hit")){

            if(Objects.equals(player1.CompareMoveTypes(player2.getMoveType()), "Win")){
                results = "Win";
            }

            if(Objects.equals(player1.CompareMoveTypes(player2.getMoveType()), "Trade")){
                if(Objects.equals(player1.CompareMoveStartup(player2.getMoveStartup()), "Win")){
                    results = "Win";
                }
                if(Objects.equals(player1.CompareMoveStartup(player2.getMoveStartup()), "Trade")){
                    results = "Draw";
                }
                if(Objects.equals(player1.CompareMoveStartup(player2.getMoveStartup()), "Lose")){
                    results = "Lose";
                }
            }

            if(Objects.equals(player1.CompareMoveTypes(player2.getMoveType()), "Lose")){
                results = "Lose";
            }
        }

        if(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Hit") &&
                player1.CompareCharacterStatusToMoveType(player2.getMoveType()).equals("Miss")){

            if(Objects.equals(player1.CompareMoveTypes(player2.getMoveType()), "Win")){
                results = "Win";
            }
            if(Objects.equals(player1.CompareMoveTypes(player2.getMoveType()), "Trade")){
                results = "Win";
            }
            if(Objects.equals(player1.CompareMoveTypes(player2.getMoveType()), "Lose")){
                results = "Draw";
            }
        }

        if(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Miss") &&
                player1.CompareCharacterStatusToMoveType(player2.getMoveType()).equals("Hit")){

            if(Objects.equals(player2.CompareMoveTypes(player1.getMoveType()), "Win")){
                results = "Lose";
            }
            if(Objects.equals(player2.CompareMoveTypes(player1.getMoveType()), "Trade")){
                results = "Lose";
            }
            if(Objects.equals(player2.CompareMoveTypes(player1.getMoveType()), "Lose")){
                results = "Draw";
            }
        }

        // If both players miss their move then no interaction happens and it's a draw.
        if(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Miss") &&
                player1.CompareCharacterStatusToMoveType(player2.getMoveType()).equals("Miss")){
            results = "Draw";
        }

        /*System.out.println(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()));
        System.out.println(player1.CompareCharacterStatusToMoveType(player2.getMoveType()));
        System.out.println(player1.CompareMoveStartup(player2.getMoveStartup()));
        System.out.println(player1.CompareMoveTypes(player2.getMoveType()));
        System.out.println(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Hit") &&
                player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Miss"));
        System.out.println(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Hit") &&
                player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Hit"));
        System.out.println(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Miss") &&
                player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Hit"));
        System.out.println(player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Miss") &&
                player1.CompareMoveTypeToOpponentStatus(player2.getCharacterStatus()).equals("Miss"));*/
        //System.out.println(results);
        return results;
    }

    /**
     * Checks to make sure the move startup is both a valid integer and is 0 or greater
     * @param str The parameter gotten from the user input
     * @param fieldName The player the move startup is from
     */
    private static int parsePositiveInt(String str, String fieldName) {
        try {
            int value = Integer.parseInt(str);
            if (value < 0) {
                throw new IllegalArgumentException("Invalid Input for " + fieldName + ". Please enter a positive integer");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Input for " + fieldName + ". Please enter a valid integer");
        }
    }

    /**
     * Checks to make sure each players character status is a valid option
     * @param status The parameter gotten from the user input
     * @param fieldName The player the character status is from
     */
    private static void validateCharacterStatus(String status, String fieldName) {
        if (!status.equals("stand") && !status.equals("crouch") && !status.equals("downed")) {
            throw new IllegalArgumentException("Invalid Input for " + fieldName + ". Please enter \"Stand\", \"Crouch\", or \"Downed\"");
        }
    }

    /**
     * Checks to make sure each players move type is a valid option
     * @param type The parameter gotten from the user input
     * @param fieldName The player the move type is from
     */
    private static void validateMoveType(String type, String fieldName) {
        if (!type.equals("high") &&
                !type.equals("medium") &&
                !type.equals("low") &&
                !type.equals("command grab") &&
                !type.equals("throw")) {
            throw new IllegalArgumentException("Invalid Input for " + fieldName + ". Please enter \"High\", \"Medium\", \"Low\", \"Command Grab\", or \"Throw\"");
        }
    }
}