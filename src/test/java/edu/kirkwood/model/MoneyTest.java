package edu.kirkwood.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void getAmount() {
        Money money = new Money(100, "USD");
        assertEquals(money.getAmount(), 100);
    }

    @Test
    void setAmount() {
        Money money = new Money(100, "USD");
        money.setAmount(200);
        assertEquals(money.getAmount(), 200);
    }

    @Test
    void getCurrency() {
        Money money = new Money(100, "USD");
        assertEquals(money.getCurrency(), "USD");
    }

    @Test
    void setCurrency() {
        Money money = new Money(100, "USD");
        money.setCurrency("GBP");
        assertEquals(money.getCurrency(), "GBP");
    }

    @Test
    void setBadCurrency() {
        Exception e = Assertions.assertThrows(
                Exception.class,
                () -> new Money(20, "MBC")
        );

        assertEquals("currency not allowed", e.getMessage());
    }

    @Test
    void convertTo() {
        Money money = new Money(100, "USD");
        money.convertTo("GBP", 1.67);
        assertEquals(money.getCurrency(), "GBP");
        assertEquals(money.getAmount(), 167);
    }

    @Test
    void add() {
        Money money = new Money(100, "USD");
        money.add(new Money(100, "USD"));
        assertEquals(money.getAmount(), 200);
        assertEquals(money.getCurrency(), "USD");
    }

    @Test
    void addDifferentCurrencies() {
        Money money = new Money(100, "USD");
        money.add(new Money(100, "CAD"));
        double d = 100 * 0.71654;
        assertEquals(money.getAmount(), 100 + d);
        assertEquals(money.getCurrency(), "USD");
    }

    @Test
    void divideDifferentCurrencies() {
        Money money = new Money(100, "USD");
        money.divide(new Money(100, "CAD"));
        double d = 100 * 0.71654;
        assertEquals(money.getAmount(), 100 / d);
        assertEquals(money.getCurrency(), "USD");
    }

    @Test
    void multiplyDifferentCurrencies() {
        Money money = new Money(100, "USD");
        money.multiply(new Money(100, "CAD"));
        double d = 100 * 0.71654;
        assertEquals(money.getAmount(), 100 * d);
        assertEquals(money.getCurrency(), "USD");
    }

    @Test
    void subtractDifferentCurrencies() {
        Money money = new Money(100, "USD");
        money.subtract(new Money(100, "CAD"));
        double d = 100 * 0.71654;
        assertEquals(money.getAmount(), 100 - d);
        assertEquals(money.getCurrency(), "USD");
    }

    @Test
    void subtract() {
        Money money = new Money(100, "USD");
        money.subtract(new Money(10, "USD"));
        assertEquals(money.getAmount(), 90);
    }

    @Test
    void testZeroAmount() {
        Money money = new Money(0.0, "GBP");
        assertEquals(0.0, money.getAmount());
    }

    @Test
    void testNegativeAmount() {

        Exception e = Assertions.assertThrows(
                Exception.class,
                () -> new Money(-20, "CAD")
        );

        assertEquals("amount cannot be negative", e.getMessage());
    }

    @Test
    void testLargeAmount() {
        Money money = new Money(1_000_000.99, "USD");
        assertEquals(1_000_000.99, money.getAmount());
        assertEquals("USD", money.getCurrency());
    }

    @Test
    void testEmptyCurrency() {
        Exception e = Assertions.assertThrows(
                Exception.class,
                () -> new Money(20, "")
        );

        assertEquals("currency not allowed", e.getMessage());
    }

}

