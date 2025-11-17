package edu.kirkwood.controller;

import edu.kirkwood.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YousifCalculatorTest {

    @Test
    void splitCalculationAdd1() {

        String input = "100USD + 100USD";
        String[] e = {"100USD", "+", "100USD"};
        assertArrayEquals(YousifMoneyCalculator.splitCalculation(input), e);
    }

    @Test
    void splitCalculationSubtract() {

        String input = "100USD - 100USD";
        String[] e = {"100USD", "-", "100USD"};
        assertArrayEquals(YousifMoneyCalculator.splitCalculation(input), e);
    }

    @Test
    void parseMoneyUSD() {

        String input = "100USD";
        Money e = new Money(100.0, "USD");
        assertEquals(YousifMoneyCalculator.parseMoney(input), e);

    }

    @Test
    void parseMoneyCAD() {

        String input = "100CAD";
        Money e = new Money(100.0, "CAD");
        assertEquals(YousifMoneyCalculator.parseMoney(input), e);

    }

    @Test
    void parseMoneyGBP() {

        String input = "100GBP";
        Money e = new Money(100.0, "GBP");
        assertEquals(YousifMoneyCalculator.parseMoney(input), e);

    }

    @Test
    void testBadCurrency() {
        Exception e = Assertions.assertThrows(
                Exception.class,
                () -> YousifMoneyCalculator.parseMoney("100SD")
        );

        assertEquals("Invalid format. could not recognize money. try again", e.getMessage());
    }

    @Test
    void testBadInput() {
        Exception e = Assertions.assertThrows(
                Exception.class,
                () -> YousifMoneyCalculator.splitCalculation("100SD")
        );

        assertEquals("Invalid format. Ensure operator (+, -, *, /) has a space on both sides.", e.getMessage());
    }

    @Test
    void testMissingOperatorInput() {
        Exception e = Assertions.assertThrows(
                Exception.class,
                () -> YousifMoneyCalculator.splitCalculation("100USD 100USD")
        );

        assertEquals("Invalid format. Ensure operator (+, -, *, /) has a space on both sides.", e.getMessage());
    }


}