package edu.kirkwood.controller;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Random;

public class MoneyCalculatorTest {
    // TEST: coinsToCents()
    // ---------------------------
    @Test
    public void testCoinsToCents_AllZero() {
        int[] coins = {0, 0, 0, 0, 0, 0};
        assertEquals(0, callCoinsToCents(coins));
    }

    @Test
    public void testCoinsToCents_OnlyDollars() {
        int[] coins = {3, 0, 0, 0, 0, 0}; // $3.00
        assertEquals(300, callCoinsToCents(coins));
    }

    @Test
    public void testCoinsToCents_MixedCoins() {
        int[] coins = {1, 1, 1, 1, 1, 4};
        // 1 Dollar  = 100
        // 1 Half    = 50
        // 1 Quarter = 25
        // 1 Dime    = 10
        // 1 Nickel  = 5
        // 4 Pennies = 4
        assertEquals(194, callCoinsToCents(coins));
    }

    @Test
    public void testCoinsToCents_NegativeCoinsAllowed() {
        int[] coins = {-1, 0, 0, 0, 0, 0};
        // -1 dollar = -100
        assertEquals(-100, callCoinsToCents(coins));
    }

    // ---------------------------
    // TEST: generateRandomCoins()
    // Must override the internal Random so tests are consistent
    // ---------------------------
    @Test
    public void testGenerateRandomCoins_Deterministic() throws Exception {
        // Set the private static Random rand = new Random()
        // to a seed we control
        Field randField = Money$Calculator.class.getDeclaredField("rand");
        randField.setAccessible(true);
        randField.set(null, new Random(12345)); // fixed seed

        int[] coins = callGenerateRandomCoins();

        // With seed=12345, rand.nextInt(5) will always produce this sequence
        int[] expected = {2, 4, 1, 2, 2, 1};

        assertArrayEquals(expected, coins);
    }

    // ------------------------------------------------
    // PRIVATE HELPERS (because methods are private)
    // Use reflection to call private static methods
    // ------------------------------------------------
    private int callCoinsToCents(int[] coins) {
        try {
            var method = Money$Calculator.class.getDeclaredMethod("coinsToCents", int[].class);
            method.setAccessible(true);
            return (int) method.invoke(null, (Object) coins);
        } catch (Exception e) {
            fail("Reflection call failed: " + e.getMessage());
            return -1;
        }
    }

    private int[] callGenerateRandomCoins() {
        try {
            var method = Money$Calculator.class.getDeclaredMethod("generateRandomCoins");
            method.setAccessible(true);
            return (int[]) method.invoke(null);
        } catch (Exception e) {
            fail("Reflection call failed: " + e.getMessage());
            return null;
        }
    }
}



