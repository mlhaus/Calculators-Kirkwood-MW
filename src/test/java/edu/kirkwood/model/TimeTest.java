package edu.kirkwood.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeTest {

    @Test
    void testToString() {
        Time time = new Time(1,"year");
        assertEquals("1 year", time.toString());
        Time time2 = new Time(1,"week");
        time.add(time2);
        assertEquals("1 year 1 week", time.toString());
        time2 = new Time(1,"day");
        time.add(time2);
        assertEquals("1 year 1 week 1 day", time.toString());
        time2 = new Time(1,"hour");
        time.add(time2);
        assertEquals("1 year 1 week 1 day 1 hour", time.toString());
        time2 = new Time(1,"minute");
        time.add(time2);
        assertEquals("1 year 1 week 1 day 1 hour 1 minute", time.toString());
        time2 = new Time(1,"second");
        time.add(time2);
        assertEquals("1 year 1 week 1 day 1 hour 1 minute 1 second", time.toString());
        time2 = new Time(1);
        time.add(time2);
        assertEquals("1 year 1 week 1 day 1 hour 1 minute 1 second 1 millisecond", time.toString());

    }

    @Test
    void subtract() {
        Time t1 = new Time(61,"minute");
        Time t2 = new Time(1,"minute");
        t1.subtract(t2);
        assertEquals(3600000,t1.getMilliseconds());
    }

    @Test
    void add() {
        Time t1 = new Time(59,"minute");
        Time t2 = new Time(1,"minute");
        t1.add(t2);
        assertEquals(3600000,t1.getMilliseconds());
    }

    @Test
    void multiply() {
        Time time = new Time(1,"hour");
        time.multiply(3);
        assertEquals("3 hours", time.toString());
    }

    @Test
    void divide() {
        Time time = new Time(1,"day");
        time.divide(4);
        assertEquals("6 hours", time.toString());
    }
    @Test
    void divideByZero() {
        Time time = new Time(1,"day");
        assertThrows(ArithmeticException.class, () -> time.divide(0));
    }

    @Test
    void divideByTime() {
        Time time = new Time(1,"day");
        Time time2 = new Time(6,"hour");
        assertEquals(4, time.divide(time2));
    }

    @Test
    void getMilliseconds() {
        for(int i = 1; i <= 100; i++){
            Time time = new Time(i);
            Time time2 = new Time(i, "millisecond");
            assertEquals(i,time.getMilliseconds());
            assertEquals(i,time2.getMilliseconds());
        }
    }

    @Test
    void testConvertion() {
        long num = 1;
        Time time = new Time(1,"millisecond");
        assertEquals(num, time.getMilliseconds());

        time = new Time(1,"second");
        num = num * 1000;
        assertEquals(num, time.getMilliseconds());

        time = new Time(1,"minute");
        num = num * 60;
        assertEquals(num, time.getMilliseconds());

        time = new Time(1,"hour");
        num = num * 60;
        assertEquals(num, time.getMilliseconds());

        time = new Time(1,"day");
        num = num * 24;
        assertEquals(num, time.getMilliseconds());

        time = new Time(1,"week");
        assertEquals(num * 7, time.getMilliseconds());

        time = new Time(1,"year");
        num = num * 365;
        assertEquals(num, time.getMilliseconds());

    }
    @Test
    void testInvalidUnit()
    {
        assertThrows(IllegalArgumentException.class, () -> new Time(1,"meek"));
    }
}