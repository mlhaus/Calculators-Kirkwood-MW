package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterFighterTest {

    private CharacterFighter Player1;
    private CharacterFighter Player2;

    @BeforeEach
    void setUp()
    {
        Player1 = new CharacterFighter();
        Player2 = new CharacterFighter(5, "Crouch", "Low");
    }

    @Test
    void getMoveStartups()
    {
        assertEquals(10, Player1.getMoveStartup());
        assertEquals(5, Player2.getMoveStartup());

        // Test Statements for failure
        // assertEquals(1, Player1.getMoveStartup());
    }

    @Test
    void setMoveStartups()
    {
        Player1.setMoveStartup(9);
        assertEquals(9, Player1.getMoveStartup());

        Player2.setMoveStartup(10);
        assertEquals(10, Player2.getMoveStartup());

        // Test Statements for failure
        // assertEquals(5, Player1.getMoveStartup());

    }

    @Test
    void getCharacterStatuses()
    {
        assertEquals("Stand", Player1.getCharacterStatus());
        assertEquals("Crouch", Player2.getCharacterStatus());

        // Test Statements for failure
        // assertEquals("Downed", Player1.getCharacterStatus());
    }

    @Test
    void setCharacterStatus(){
        Player1.setCharacterStatus("Downed");
        assertEquals("Downed", Player1.getCharacterStatus());
        Player2.setCharacterStatus("Stand");
        assertEquals("Stand", Player2.getCharacterStatus());

        // Test Statement for failure
        // assertEquals("Crouch", Player1.getCharacterStatus());

    }

    @Test
    void getMoveTypes()
    {
        assertEquals("Medium", Player1.getMoveType());
        assertEquals("Low", Player2.getMoveType());

        // Test Statement for failure
        // assertEquals("Medium", Player2.getCharacterStatus());
    }

    @Test
    void setMoveTypes(){
        Player1.setMoveType("Command Grab");
        assertEquals("Command Grab", Player1.getMoveType());
        Player2.setMoveType("Throw");
        assertEquals("Throw", Player2.getMoveType());

        // Test Statement for failure
        // assertEquals("High", Player1.getMoveType());
    }

    @Test
    void compareMoveStartups(){
        //Tie
        Player1.setMoveStartup(10);
        Player2.setMoveStartup(10);
        assertEquals("Trade", Player1.CompareMoveStartup(Player2.getMoveStartup()));
        //Win
        Player1.setMoveStartup(9);
        Player2.setMoveStartup(10);
        assertEquals("Win", Player1.CompareMoveStartup(Player2.getMoveStartup()));
        //Lose
        Player1.setMoveStartup(11);
        Player2.setMoveStartup(10);
        assertEquals("Lose", Player1.CompareMoveStartup(Player2.getMoveStartup()));

        // Test Statement for failure
        /*
        Player1.setMoveStartup(11);
        Player2.setMoveStartup(11);
        assertEquals("Win", Player1.CompareMoveStartup(Player2.getMoveStartup()));
        */
    }

    @Test
    void CompareMoveTypeToOpponentStatus(){
        // Hits
        Player1.setMoveType("high");
        Player2.setCharacterStatus("stand");
        assertEquals("Hit", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("medium");
        Player2.setCharacterStatus("stand");
        assertEquals("Hit", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("low");
        Player2.setCharacterStatus("stand");
        assertEquals("Hit", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("command grab");
        Player2.setCharacterStatus("stand");
        assertEquals("Hit", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("throw");
        Player2.setCharacterStatus("stand");
        assertEquals("Hit", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("medium");
        Player2.setCharacterStatus("crouch");
        assertEquals("Hit", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("low");
        Player2.setCharacterStatus("crouch");
        assertEquals("Hit", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        //Misses
        Player1.setMoveType("high");
        Player2.setCharacterStatus("crouch");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("command grab");
        Player2.setCharacterStatus("crouch");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("throw");
        Player2.setCharacterStatus("crouch");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("high");
        Player2.setCharacterStatus("downed");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("medium");
        Player2.setCharacterStatus("downed");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("low");
        Player2.setCharacterStatus("downed");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("command grab");
        Player2.setCharacterStatus("downed");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        Player1.setMoveType("throw");
        Player2.setCharacterStatus("downed");
        assertEquals("Miss", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));

        // Test Statement for failure
        /*
        Player1.setMoveType("High");
        Player2.setCharacterStatus("Stand");
        assertEquals("Stand", Player1.CompareMoveTypeToOpponentStatus(Player2.getCharacterStatus()));
        */
    }

    @Test
    void CompareMoveTypes(){
        // Trades
        Player1.setMoveType("high");
        Player2.setMoveType("high");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("medium");
        Player2.setMoveType("high");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("command grab");
        Player2.setMoveType("high");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("throw");
        Player2.setMoveType("high");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("high");
        Player2.setMoveType("medium");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("medium");
        Player2.setMoveType("medium");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("low");
        Player2.setMoveType("medium");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("command grab");
        Player2.setMoveType("medium");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("throw");
        Player2.setMoveType("medium");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("medium");
        Player2.setMoveType("low");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("low");
        Player2.setMoveType("low");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("high");
        Player2.setMoveType("command grab");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("medium");
        Player2.setMoveType("command grab");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("command grab");
        Player2.setMoveType("command grab");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("throw");
        Player2.setMoveType("command grab");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("high");
        Player2.setMoveType("throw");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("medium");
        Player2.setMoveType("throw");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("command grab");
        Player2.setMoveType("throw");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("throw");
        Player2.setMoveType("throw");
        assertEquals("Trade", Player1.CompareMoveTypes(Player2.getMoveType()));

        // Wins
        Player1.setMoveType("low");
        Player2.setMoveType("high");
        assertEquals("Win", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("low");
        Player2.setMoveType("command grab");
        assertEquals("Win", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("low");
        Player2.setMoveType("throw");
        assertEquals("Win", Player1.CompareMoveTypes(Player2.getMoveType()));

        // Lose
        Player1.setMoveType("high");
        Player2.setMoveType("low");
        assertEquals("Lose", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("command grab");
        Player2.setMoveType("low");
        assertEquals("Lose", Player1.CompareMoveTypes(Player2.getMoveType()));

        Player1.setMoveType("throw");
        Player2.setMoveType("low");
        assertEquals("Lose", Player1.CompareMoveTypes(Player2.getMoveType()));

    }

    @Test
    void CompareCharacterStatusToMoveType(){
        // Hit
        Player1.setCharacterStatus("stand");
        Player2.setMoveType("high");
        assertEquals("Hit", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("stand");
        Player2.setMoveType("medium");
        assertEquals("Hit", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("stand");
        Player2.setMoveType("low");
        assertEquals("Hit", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("stand");
        Player2.setMoveType("command grab");
        assertEquals("Hit", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("stand");
        Player2.setMoveType("throw");
        assertEquals("Hit", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("crouch");
        Player2.setMoveType("medium");
        assertEquals("Hit", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("crouch");
        Player2.setMoveType("low");
        assertEquals("Hit", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        // Miss
        Player1.setCharacterStatus("crouch");
        Player2.setMoveType("high");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("crouch");
        Player2.setMoveType("command grab");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("crouch");
        Player2.setMoveType("throw");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("downed");
        Player2.setMoveType("high");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("downed");
        Player2.setMoveType("medium");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("downed");
        Player2.setMoveType("low");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("downed");
        Player2.setMoveType("command grab");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));

        Player1.setCharacterStatus("downed");
        Player2.setMoveType("throw");
        assertEquals("Miss", Player1.CompareCharacterStatusToMoveType(Player2.getMoveType()));


    }



}

