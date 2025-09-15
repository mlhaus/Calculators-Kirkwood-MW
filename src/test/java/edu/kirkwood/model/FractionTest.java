package edu.kirkwood.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    private Fraction f1;
    private Fraction f2;

    @BeforeEach
    void setUp() {
        f1 = new Fraction();
        f2 = new Fraction(2, 3);
    }

    @Test
    void getNumerator() {
        assertEquals(1, f1.getNumerator());
        assertEquals(2, f2.getNumerator());
    }

    @Test
    void setNumerator() {
        f1.setNumerator(2); // Was 1
        assertEquals(2, f1.getNumerator());
        f1.setNumerator(0); // Was 2
        assertEquals(0, f1.getNumerator());
        f1.setNumerator(-2); // Was 0
        assertEquals(-2, f1.getNumerator());
    }

    @Test
    void getDenominator() {
        assertEquals(1, f1.getDenominator());
        assertEquals(3, f2.getDenominator());
    }

    @Test
    void setDenominator() {
        f1.setDenominator(2); // Was 1
        assertEquals(2, f1.getDenominator(), "Positive denominators are allowed");

        assertThrows(ArithmeticException.class, () -> f1.setDenominator(0), "Zero denominators are NOT allowed");

        // Fraction is 1/1
        f1.setDenominator(-2); // Fraction should be -1/2, not 1/-2
        assertEquals(-1, f1.getNumerator(), "Negative denominators will cause the numerator to flip");
        assertEquals(2, f1.getDenominator(), "Negative denominators should cause a positive denominator");

        f1.setDenominator(-2); // Fraction should be 1/2, not -1/-2
        assertEquals(1, f1.getNumerator());
        assertEquals(2, f1.getDenominator());

    }

    @Test
    void testToString() {
        assertEquals("1/1", f1.toString());
        assertEquals("2/3", f2.toString());
    }

    @Test
    void compareToEqual() {
        f2 = new Fraction();
        assertEquals(0, f1.compareTo(f2), "1/1 == 1/1");
        f2 = new Fraction(2, 2);
        assertEquals(0, f1.compareTo(f2), "1/1 == 2/2");
    }

    @Test
    void compareToGreaterThan() {
        f2 = new Fraction(1,2);
        assertTrue(f1.compareTo(f2) > 0, "1/1 > 1/2");
        f2 = new Fraction(2,4);
        assertTrue(f1.compareTo(f2) > 0, "1/1 > 2/4");
    }

    @Test
    void compareToLessThan() {
        f2 = new Fraction(1,2);
        assertTrue(f2.compareTo(f1) < 0, "1/2 < 1/1");
        f2 = new Fraction(2,4);
        assertTrue(f2.compareTo(f1) < 0, "2/4 < 1/1");
    }

    @Test
    void equals() {
        f2 = new Fraction();
        assertTrue(f1.equals(f2), "1/1 == 1/1");
        f2 = new Fraction(2, 2);
        assertTrue(f1.equals(f2), "1/1 == 2/2");
    }

    @Test
    void gcd() {
        assertEquals(15, Fraction.gcd(75, 45));
        assertEquals(2, Fraction.gcd(2, 4));
    }

    @Test
    void gcdNegatives() {
        assertEquals(1, Fraction.gcd(5, 7));
        assertEquals(1, Fraction.gcd(-5, 7));
        assertEquals(1, Fraction.gcd(5, -7));
        assertEquals(1, Fraction.gcd(-5, -7));
    }

    @Test
    @DisplayName("Test LCM with two positive integers")
    void testLcmWithPositiveIntegers() {
        assertEquals(24, Fraction.lcm(6, 8));
    }

    @Test
    @DisplayName("Test LCM where one number is a multiple of the other")
    void testLcmWithMultiple() {
        assertEquals(12, Fraction.lcm(4, 12));
    }

    @Test
    @DisplayName("Test LCM with two prime numbers")
    void testLcmWithPrimes() {
        // The lcm of two prime numbers is their product.
        assertEquals(77, Fraction.lcm(7, 11));
    }

    @Test
    @DisplayName("Test LCM with the number 1")
    void testLcmWithOne() {
        assertEquals(9, Fraction.lcm(1, 9));
        assertEquals(9, Fraction.lcm(9, 1));
    }

    @Test
    @DisplayName("Test LCM with identical numbers")
    void testLcmWithIdenticalNumbers() {
        assertEquals(5, Fraction.lcm(5, 5));
    }

    @Test
    @DisplayName("Test LCM where one of the inputs is zero")
    void testLcmWithZero() {
        assertEquals(0, Fraction.lcm(10, 0));
        assertEquals(0, Fraction.lcm(0, 10));
        assertEquals(0, Fraction.lcm(0, 0));
    }

    @Test
    void testSimplify()
    {
        Fraction fWhole = new Fraction(4, 2);
        Fraction fWholeNeg = new Fraction(-4, 2);
        fWhole.simplify();
        fWholeNeg.simplify();
        assertEquals(fWhole, ((Object)new Fraction(2,1)));
        assertEquals(fWholeNeg, ((Object)new Fraction(-2,1)));
    }

    @Test
    void toMixedNumber() {
        assertEquals("1", f1.toMixedNumber());
        assertEquals("2/3", f2.toMixedNumber());
    }

    @Test
    void toMixedNumerImproperFraction() {
        f1.setNumerator(5);
        f1.setDenominator(3);
        f2.setNumerator(10);
        f2.setDenominator(3);
        assertEquals("1 2/3", f1.toMixedNumber());
        assertEquals("3 1/3", f2.toMixedNumber());
    }

    @Test
    void toMixedNumerImproperFractionNegative() {
        f1.setNumerator(-5);
        f1.setDenominator(3);
        f2.setNumerator(10);
        f2.setDenominator(-3);
        assertEquals("-1 2/3", f1.toMixedNumber());
        assertEquals("-3 1/3", f2.toMixedNumber());
    }

    @Test
    @DisplayName("Test 1/1 + 2/3 = 5/3")
    void addWholeNumberToFraction() {
        Fraction f3 = f1.add(f2);
        assertEquals(5, f3.getNumerator());
        assertEquals(3, f3.getDenominator());
    }

    @Test
    @DisplayName("Test -1/4 + 2/3 = 5/12")
    void addNegativeNumberToFraction() {
        f1.setNumerator(-1);
        f1.setDenominator(4);
        Fraction f3 = f1.add(f2);
        assertEquals(5, f3.getNumerator());
        assertEquals(12, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 25/-100 + -10/-15 = 5/12")
    void addNegativeNumberToFractionRequiringSimplification() {
        f1.setNumerator(25);
        f1.setDenominator(-100);
        f2.setNumerator(-10);
        f2.setDenominator(-15);
        Fraction f3 = f1.add(f2);
        assertEquals(5, f3.getNumerator());
        assertEquals(12, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 1/1 - 1/4 = 3/4")
    void subtractFromWholeNumberToFraction() {
        f1.setNumerator(1);
        f1.setDenominator(1);
        f2.setNumerator(1);
        f2.setDenominator(4);

        Fraction f3 = f1.subtract(f2);
        f3.simplify();
        assertEquals(3, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 1/2 - -3/4 = 5/4")
    void subtractFromFractionByNegativeNumber() {
        f1.setNumerator(1);
        f1.setDenominator(2);
        f2.setNumerator(-3);
        f2.setDenominator(4);

        Fraction f3 = f1.subtract(f2);
        f3.simplify();
        assertEquals(5, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 10/1 - 15/4 = 25/4")
    void subtractFromWholeNunberByFraction() {
        f1.setNumerator(10);
        f1.setDenominator(1);
        f2.setNumerator(15);
        f2.setDenominator(4);

        Fraction f3 = f1.subtract(f2);
        f3.simplify();
        assertEquals(25, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    void multiplyWholeFractions() {
        f1.setNumerator(1);
        f1.setDenominator(1);
        f2.setNumerator(2);
        f2.setDenominator(3);
        Fraction f3 = f1.multiply(f2);
        assertEquals(2, f3.getNumerator());
        assertEquals(3, f3.getDenominator());
    }

    @Test
    void multipyFractionsWithNegatives() {
        f1.setNumerator(-1);
        f1.setDenominator(4);
        Fraction f3 = f1.multiply(f2);
        assertEquals(-1, f3.getNumerator());
        assertEquals(6, f3.getDenominator());
    }

    @Test
    void multipleFractionsNeedSimplification() {
        f1.setNumerator(25);
        f1.setDenominator(-100);
        f2.setNumerator(10);
        f2.setDenominator(15);
        Fraction f3 = f1.multiply(f2);
        assertEquals(-1, f3.getNumerator());
        assertEquals(6, f3.getDenominator());
    }

    @Test
    void basicDivide() {
        f1.setNumerator(2);
        f1.setDenominator(5);
        f2.setNumerator(5);
        f2.setDenominator(3);
        Fraction f3 = f1.divide(f2);
        assertEquals(25, f3.getDenominator());
        assertEquals(6, f3.getNumerator());
    }
    @Test
    void basicDivideNegToPos() {
        f1.setNumerator(-2);
        f1.setDenominator(5);
        f2.setNumerator(5);
        f2.setDenominator(-3);
        Fraction f3 = f1.divide(f2);
        assertEquals(6, f3.getNumerator());
        assertEquals(25, f3.getDenominator());
    }
    @Test
    void basicDivideNegToNeg() {
        f1.setNumerator(-2);
        f1.setDenominator(5);
        f2.setNumerator(5);
        f2.setDenominator(3);
        Fraction f4 = f1.divide(f2);
        assertEquals(-6, f4.getNumerator());
    }
}