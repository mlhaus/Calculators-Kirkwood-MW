package edu.kirkwood.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the core business logic in InvestmentCalculator.solveCalculation().
 */
public class InvestmentCalculatorTest {

    // Delta is used for comparing doubles due to floating-point imprecision.
    private static final double DELTA = 0.0001;

    // 1. Basic Case: Equal investments (50/50 split).
    @Test
    void test01_BasicCase_50_50_Split() {
        // ARRANGE: $1000 total, 5% and 8% rates, $65 interest. Expected: $500/$500
        double total = 1000.0;
        double stockRate = 5.0;
        double bondRate = 8.0;
        double interest = 65.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        assertNotNull(result, "1. Calculation should not return null for valid inputs.");
        assertEquals(500.00, result[0], DELTA, "1. Stock investment incorrect.");
        assertEquals(500.00, result[1], DELTA, "1. Bond investment incorrect.");
    }

    // 2. Asymmetric Case: More investment in the lower rate asset (70/30 split).
    @Test
    void test02_AsymmetricCase_MoreInLowerRate() {
        // ARRANGE: $1000 total, 5% and 8% rates, $59 interest. Expected: $700/$300
        double total = 1000.0;
        double stockRate = 5.0;
        double bondRate = 8.0;
        double interest = 59.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        assertNotNull(result, "2. Calculation should not return null.");
        assertEquals(700.00, result[0], DELTA, "2. Stock investment incorrect.");
        assertEquals(300.00, result[1], DELTA, "2. Bond investment incorrect.");
    }

    // 3. Asymmetric Case: More investment in the higher rate asset (30/70 split).
    @Test
    void test03_AsymmetricCase_MoreInHigherRate() {
        // ARRANGE: $1000 total, 5% and 8% rates, $71 interest. Expected: $300/$700
        double total = 1000.0;
        double stockRate = 5.0;
        double bondRate = 8.0;
        double interest = 71.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        assertNotNull(result, "3. Calculation should not return null.");
        assertEquals(300.00, result[0], DELTA, "3. Stock investment incorrect.");
        assertEquals(700.00, result[1], DELTA, "3. Bond investment incorrect.");
    }

    // 4. Boundary Case: All investment goes into the first asset (Stocks).
    @Test
    void test04_BoundaryCase_AllInStocks() {
        // ARRANGE: 10% and 5% rates. Max possible interest is $100 (10% of $1000). Expected: $1000/$0
        double total = 1000.0;
        double stockRate = 10.0;
        double bondRate = 5.0;
        double interest = 100.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        assertNotNull(result, "4. Calculation should not return null.");
        assertEquals(1000.00, result[0], DELTA, "4. Should be $1000.00 in stocks.");
        assertEquals(0.00, result[1], DELTA, "4. Should be $0.00 in bonds.");
    }

    // 5. Boundary Case: All investment goes into the second asset (Bonds).
    @Test
    void test05_BoundaryCase_AllInBonds() {
        // ARRANGE: 5% and 10% rates. Max possible interest is $100 (10% of $1000). Expected: $0/$1000
        double total = 1000.0;
        double stockRate = 5.0;
        double bondRate = 10.0;
        double interest = 100.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        assertNotNull(result, "5. Calculation should not return null.");
        assertEquals(0.00, result[0], DELTA, "5. Should be $0.00 in stocks.");
        assertEquals(1000.00, result[1], DELTA, "5. Should be $1000.00 in bonds.");
    }

    // 6. Boundary Case: Zero total investment.
    @Test
    void test06_BoundaryCase_ZeroTotalInvestment() {
        // ARRANGE: $0 total, $0 interest.
        double total = 0.0;
        double stockRate = 5.0;
        double bondRate = 8.0;
        double interest = 0.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        assertNotNull(result, "6. Calculation should not return null.");
        assertEquals(0.00, result[0], DELTA, "6. Stock investment should be zero.");
        assertEquals(0.00, result[1], DELTA, "6. Bond investment should be zero.");
    }

    // 7. Error Case: Interest is too high (Mathematically impossible).
    @Test
    void test07_ErrorCase_InterestTooHigh() {
        // ARRANGE: Max possible interest is $80 (8% of $1000). Requesting $90 is impossible.
        double total = 1000.0;
        double stockRate = 5.0;
        double bondRate = 8.0;
        double interest = 90.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        // The solveCalculation catches the exception and returns null.
        assertNull(result, "7. Should return null if the required interest is too high to reach.");
    }

    // 8. Error Case: Interest is too low (Mathematically impossible).
    @Test
    void test08_ErrorCase_InterestTooLow() {
        // ARRANGE: Min possible interest is $50 (5% of $1000). Requesting $40 is impossible.
        double total = 1000.0;
        double stockRate = 5.0;
        double bondRate = 8.0;
        double interest = 40.0;

        // ACT
        double[] result = InvestmentCalculator.solveCalculation(total, stockRate, bondRate, interest);

        // ASSERT
        assertNull(result, "8. Should return null if the required interest is too low to reach.");
    }
}