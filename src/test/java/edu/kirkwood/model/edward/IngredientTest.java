// file: /src/test/java/edu/kirkwood/model/IngredientTest.java v2 - Refactored to AAA pattern

package edu.kirkwood.model.edward;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Ingredient model using Arrange-Act-Assert pattern
 */
public class IngredientTest {

    private Ingredient flour;
    private Ingredient sugar;
    private Ingredient milk;
    private Ingredient salt;

    @BeforeEach
    void setUp() {
        flour = new Ingredient("Flour", 2.5, Unit.CUP);
        sugar = new Ingredient("Sugar", 1.0, Unit.CUP);
        milk = new Ingredient("Milk", 8.0, Unit.FL_OZ);
        salt = new Ingredient("Salt", 1.5, Unit.TSP);
    }

    // ========== CONSTRUCTOR TESTS ==========
    @Test
    @DisplayName("Test primary constructor with valid parameters")
    void testPrimaryConstructorValid() {
        // Arrange
        String name = "Salt";
        double quantity = 1.5;
        Unit unit = Unit.TSP;

        // Act
        Ingredient ingredient = new Ingredient(name, quantity, unit);

        // Assert
        assertEquals("Salt", ingredient.getName());
        assertEquals(1.5, ingredient.getQuantity(), 0.001);
        assertEquals(Unit.TSP, ingredient.getUnit());
    }

    @Test
    @DisplayName("Test constructor with null name throws exception")
    void testConstructorNullName() {
        // Arrange
        String name = null;
        double quantity = 1.0;
        Unit unit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(name, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test constructor with empty name throws exception")
    void testConstructorEmptyName() {
        // Arrange
        String name = "";
        double quantity = 1.0;
        Unit unit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(name, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test constructor with whitespace name throws exception")
    void testConstructorWhitespaceName() {
        // Arrange
        String name = "   ";
        double quantity = 1.0;
        Unit unit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(name, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test constructor with long name throws exception")
    void testConstructorLongName() {
        // Arrange
        String longName = "a".repeat(51); // Over MAX_INGREDIENT_NAME_LENGTH
        double quantity = 1.0;
        Unit unit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(longName, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test constructor with negative quantity for shortage tracking")
    void testConstructorNegativeQuantity() {
        // Arrange
        String name = "Test";
        double quantity = -1.0;
        Unit unit = Unit.CUP;

        // Act
        Ingredient ingredient = new Ingredient(name, quantity, unit);

        // Assert
        assertEquals(-1.0, ingredient.getQuantity(), 0.001);
        assertEquals("Test", ingredient.getName());
        assertEquals(Unit.CUP, ingredient.getUnit());
    }

    @Test
    @DisplayName("Test constructor with zero quantity")
    void testConstructorZeroQuantity() {
        // Arrange
        String name = "Test";
        double quantity = 0.0;
        Unit unit = Unit.CUP;

        // Act
        Ingredient ingredient = new Ingredient(name, quantity, unit);

        // Assert
        assertEquals(0.0, ingredient.getQuantity(), 0.001);
        assertEquals("Test", ingredient.getName());
        assertEquals(Unit.CUP, ingredient.getUnit());
    }

    @Test
    @DisplayName("Test constructor with very small quantity throws exception")
    void testConstructorVerySmallQuantity() {
        // Arrange
        String name = "Test";
        double quantity = 0.0005; // Below MIN_QUANTITY
        Unit unit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(name, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test constructor with very small negative quantity throws exception")
    void testConstructorVerySmallNegativeQuantity() {
        // Arrange
        String name = "Test";
        double quantity = -0.0005; // Below MIN_QUANTITY threshold
        Unit unit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(name, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test constructor with very large quantity throws exception")
    void testConstructorVeryLargeQuantity() {
        // Arrange
        String name = "Test";
        double quantity = 1000000.0; // Above MAX_QUANTITY
        Unit unit = Unit.CUP;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(name, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test constructor with null unit throws exception")
    void testConstructorNullUnit() {
        // Arrange
        String name = "Test";
        double quantity = 1.0;
        Unit unit = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(name, quantity, unit);
        });
    }

    @Test
    @DisplayName("Test default name constructor")
    void testDefaultNameConstructor() {
        // Arrange
        double quantity = 2.0;
        Unit unit = Unit.TBSP;

        // Act
        Ingredient ingredient = new Ingredient(quantity, unit);

        // Assert
        assertEquals("Ingredient", ingredient.getName());
        assertEquals(2.0, ingredient.getQuantity(), 0.001);
        assertEquals(Unit.TBSP, ingredient.getUnit());
    }

    @Test
    @DisplayName("Test copy constructor")
    void testCopyConstructor() {
        // Arrange
        Ingredient original = flour;

        // Act
        Ingredient copy = new Ingredient(original);

        // Assert
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getQuantity(), copy.getQuantity(), 0.001);
        assertEquals(original.getUnit(), copy.getUnit());
        assertNotSame(original, copy);
    }

    @Test
    @DisplayName("Test copy constructor with null ingredient throws exception")
    void testCopyConstructorNull() {
        // Arrange
        Ingredient nullIngredient = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(nullIngredient);
        });
    }

    // ========== GETTER TESTS ==========
    @Test
    @DisplayName("Test getName")
    void testGetName() {
        // Arrange - using setup ingredients

        // Act
        String flourName = flour.getName();
        String sugarName = sugar.getName();

        // Assert
        assertEquals("Flour", flourName);
        assertEquals("Sugar", sugarName);
    }

    @Test
    @DisplayName("Test getQuantity")
    void testGetQuantity() {
        // Arrange - using setup ingredients

        // Act
        double flourQuantity = flour.getQuantity();
        double sugarQuantity = sugar.getQuantity();

        // Assert
        assertEquals(2.5, flourQuantity, 0.001);
        assertEquals(1.0, sugarQuantity, 0.001);
    }

    @Test
    @DisplayName("Test getUnit")
    void testGetUnit() {
        // Arrange - using setup ingredients

        // Act
        Unit flourUnit = flour.getUnit();
        Unit saltUnit = salt.getUnit();

        // Assert
        assertEquals(Unit.CUP, flourUnit);
        assertEquals(Unit.TSP, saltUnit);
    }

    // ========== SETTER TESTS ==========
    @Test
    @DisplayName("Test setName with valid name")
    void testSetNameValid() {
        // Arrange
        String newName = "Whole Wheat Flour";

        // Act
        flour.setName(newName);

        // Assert
        assertEquals("Whole Wheat Flour", flour.getName());
    }

    @Test
    @DisplayName("Test setName trims whitespace")
    void testSetNameTrimsWhitespace() {
        // Arrange
        String nameWithWhitespace = "  Brown Sugar  ";

        // Act
        flour.setName(nameWithWhitespace);

        // Assert
        assertEquals("Brown Sugar", flour.getName());
    }

    @Test
    @DisplayName("Test setName with null throws exception")
    void testSetNameNull() {
        // Arrange
        String nullName = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.setName(nullName);
        });
    }

    @Test
    @DisplayName("Test setName with empty string throws exception")
    void testSetNameEmpty() {
        // Arrange
        String emptyName = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.setName(emptyName);
        });
    }

    @Test
    @DisplayName("Test setQuantity with valid quantity")
    void testSetQuantityValid() {
        // Arrange
        double newQuantity = 3.5;

        // Act
        flour.setQuantity(newQuantity);

        // Assert
        assertEquals(3.5, flour.getQuantity(), 0.001);
    }

    @Test
    @DisplayName("Test setQuantity with minimum quantity")
    void testSetQuantityMinimum() {
        // Arrange
        double minimumQuantity = 0.001;

        // Act
        flour.setQuantity(minimumQuantity);

        // Assert
        assertEquals(0.001, flour.getQuantity(), 0.001);
    }

    @Test
    @DisplayName("Test setQuantity below minimum throws exception")
    void testSetQuantityBelowMinimum() {
        // Arrange
        double belowMinimum = 0.0005;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.setQuantity(belowMinimum);
        });
    }

    @Test
    @DisplayName("Test setQuantity above maximum throws exception")
    void testSetQuantityAboveMaximum() {
        // Arrange
        double aboveMaximum = 1000000.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.setQuantity(aboveMaximum);
        });
    }

    @Test
    @DisplayName("Test setUnit with valid unit")
    void testSetUnitValid() {
        // Arrange
        Unit newUnit = Unit.PINT;

        // Act
        flour.setUnit(newUnit);

        // Assert
        assertEquals(Unit.PINT, flour.getUnit());
    }

    @Test
    @DisplayName("Test setUnit with null throws exception")
    void testSetUnitNull() {
        // Arrange
        Unit nullUnit = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.setUnit(nullUnit);
        });
    }

    // ========== ARITHMETIC OPERATION TESTS ==========
    @Test
    @DisplayName("Test ingredient addition with same units")
    void testAdditionSameUnits() {
        // Arrange - using flour (2.5 cups) and sugar (1.0 cup)

        // Act
        Ingredient result = flour.add(sugar);

        // Assert
        assertEquals(3.5, result.getQuantity(), 0.001);
        assertEquals(Unit.CUP, result.getUnit());
        assertTrue(result.getName().contains("+"));
    }

    @Test
    @DisplayName("Test ingredient addition with different compatible units")
    void testAdditionDifferentUnits() {
        // Arrange
        Ingredient tbspSugar = new Ingredient("Sugar", 16.0, Unit.TBSP); // 16 tbsp = 1 cup

        // Act
        Ingredient result = flour.add(tbspSugar);

        // Assert
        assertEquals(3.5, result.getQuantity(), 0.1); // Allow for rounding
        assertEquals(Unit.CUP, result.getUnit());
    }

    @Test
    @DisplayName("Test ingredient addition with incompatible units throws exception")
    void testAdditionIncompatibleUnits() {
        // Arrange
        Ingredient butter = new Ingredient("Butter", 8.0, Unit.OZ); // Weight unit

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.add(butter); // Volume + Weight should fail
        });
    }

    @Test
    @DisplayName("Test ingredient addition with null throws exception")
    void testAdditionNull() {
        // Arrange
        Ingredient nullIngredient = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.add(nullIngredient);
        });
    }

    @Test
    @DisplayName("Test ingredient subtraction")
    void testSubtraction() {
        // Arrange - using flour (2.5 cups) and sugar (1.0 cup)

        // Act
        Ingredient result = flour.subtract(sugar);

        // Assert
        assertEquals(1.5, result.getQuantity(), 0.001);
        assertEquals(Unit.CUP, result.getUnit());
        assertTrue(result.getName().contains("-"));
    }

    @Test
    @DisplayName("Test ingredient subtraction resulting in negative for shortage tracking")
    void testSubtractionNegativeResult() {
        // Arrange - sugar (1.0 cup) minus flour (2.5 cups) = negative

        // Act
        Ingredient result = sugar.subtract(flour); // 1.0 - 2.5 = -1.5

        // Assert
        assertEquals(-1.5, result.getQuantity(), 0.001);
        assertEquals(Unit.CUP, result.getUnit());
        assertTrue(result.getName().contains("-"));
    }

    @Test
    @DisplayName("Test ingredient multiplication")
    void testMultiplication() {
        // Arrange
        double multiplier = 2.0;

        // Act
        Ingredient result = flour.multiply(multiplier);

        // Assert
        assertEquals(5.0, result.getQuantity(), 0.001);
        assertEquals(Unit.CUP, result.getUnit());
        assertTrue(result.getName().contains("×"));
    }

    @Test
    @DisplayName("Test ingredient multiplication with zero throws exception")
    void testMultiplicationZero() {
        // Arrange
        double zeroMultiplier = 0.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.multiply(zeroMultiplier);
        });
    }

    @Test
    @DisplayName("Test ingredient multiplication with negative throws exception")
    void testMultiplicationNegative() {
        // Arrange
        double negativeMultiplier = -1.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.multiply(negativeMultiplier);
        });
    }

    @Test
    @DisplayName("Test ingredient division")
    void testDivision() {
        // Arrange
        double divisor = 2.0;

        // Act
        Ingredient result = flour.divide(divisor);

        // Assert
        assertEquals(1.25, result.getQuantity(), 0.001);
        assertEquals(Unit.CUP, result.getUnit());
        assertTrue(result.getName().contains("÷"));
    }

    @Test
    @DisplayName("Test division by zero throws exception")
    void testDivisionByZero() {
        // Arrange
        double zeroDivisor = 0.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.divide(zeroDivisor);
        });
    }

    @Test
    @DisplayName("Test division by negative throws exception")
    void testDivisionNegative() {
        // Arrange
        double negativeDivisor = -2.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.divide(negativeDivisor);
        });
    }

    // ========== CONVERSION TESTS ==========
    @Test
    @DisplayName("Test convertTo same unit returns copy")
    void testConvertToSameUnit() {
        // Arrange
        Unit sameUnit = Unit.CUP;

        // Act
        Ingredient result = flour.convertTo(sameUnit);

        // Assert
        assertEquals(flour.getQuantity(), result.getQuantity(), 0.001);
        assertEquals(flour.getUnit(), result.getUnit());
        assertEquals(flour.getName(), result.getName());
        assertNotSame(flour, result); // Should be a copy
    }

    @Test
    @DisplayName("Test convertTo compatible unit")
    void testConvertToCompatibleUnit() {
        // Arrange
        Unit targetUnit = Unit.TBSP;

        // Act
        Ingredient result = flour.convertTo(targetUnit);

        // Assert
        assertEquals(40.0, result.getQuantity(), 0.1); // 2.5 cups = 40 tbsp
        assertEquals(Unit.TBSP, result.getUnit());
        assertEquals("Flour", result.getName());
    }

    @Test
    @DisplayName("Test convertTo incompatible unit throws exception")
    void testConvertToIncompatibleUnit() {
        // Arrange
        Unit incompatibleUnit = Unit.OZ; // Volume to weight

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.convertTo(incompatibleUnit);
        });
    }

    @Test
    @DisplayName("Test convertTo null unit throws exception")
    void testConvertToNullUnit() {
        // Arrange
        Unit nullUnit = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            flour.convertTo(nullUnit);
        });
    }

    // ========== UTILITY METHOD TESTS ==========
    @Test
    @DisplayName("Test isCompatibleWith same type units")
    void testIsCompatibleWithSameType() {
        // Arrange - using setup ingredients (flour and sugar are both volume)

        // Act
        boolean flourSugarCompatible = flour.isCompatibleWith(sugar);
        boolean flourMilkCompatible = flour.isCompatibleWith(milk);

        // Assert
        assertTrue(flourSugarCompatible); // Both volume
        assertTrue(flourMilkCompatible);  // Both volume
    }

    @Test
    @DisplayName("Test isCompatibleWith different type units")
    void testIsCompatibleWithDifferentType() {
        // Arrange
        Ingredient butter = new Ingredient("Butter", 8.0, Unit.OZ);

        // Act
        boolean compatible = flour.isCompatibleWith(butter);

        // Assert
        assertFalse(compatible); // Volume vs weight
    }

    @Test
    @DisplayName("Test isCompatibleWith null")
    void testIsCompatibleWithNull() {
        // Arrange
        Ingredient nullIngredient = null;

        // Act
        boolean compatible = flour.isCompatibleWith(nullIngredient);

        // Assert
        assertFalse(compatible);
    }

    @Test
    @DisplayName("Test getRoundedQuantity")
    void testGetRoundedQuantity() {
        // Arrange
        Ingredient precise = new Ingredient("Test", 1.23456789, Unit.CUP);

        // Act
        double rounded = precise.getRoundedQuantity();

        // Assert
        assertEquals(1.235, rounded, 0.001);
    }

    @Test
    @DisplayName("Test getFormattedQuantity")
    void testGetFormattedQuantity() {
        // Arrange - using setup ingredients

        // Act
        String flourFormatted = flour.getFormattedQuantity();
        String sugarFormatted = sugar.getFormattedQuantity();

        // Assert
        // Helpers.round() strips trailing zeros, so "2.5" instead of "2.500"
        assertEquals("2.5", flourFormatted);
        assertEquals("1", sugarFormatted);
    }

    // ========== OBJECT METHOD TESTS ==========
    @Test
    @DisplayName("Test toString")
    void testToString() {
        // Arrange - using flour

        // Act
        String result = flour.toString();

        // Assert
        assertTrue(result.contains("Flour"));
        assertTrue(result.contains("2.5")); // Helpers.round strips trailing zeros
        assertTrue(result.contains("cup"));
    }

    @Test
    @DisplayName("Test toString with negative quantity shows shortage")
    void testToStringNegative() {
        // Arrange
        Ingredient shortage = new Ingredient("Flour", -1.5, Unit.CUP);

        // Act
        String result = shortage.toString();

        // Assert
        assertTrue(result.contains("SHORTAGE"));
        assertTrue(result.contains("Flour"));
        assertTrue(result.contains("-1.5"));
        assertTrue(result.contains("cup"));
    }



    @Test
    @DisplayName("Test equals with same object")
    void testEqualsSameObject() {
        // Arrange - using flour

        // Act
        boolean isEqual = flour.equals(flour);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test equals with equal objects")
    void testEqualsEqualObjects() {
        // Arrange
        Ingredient flour2 = new Ingredient("Flour", 2.5, Unit.CUP);

        // Act
        boolean isEqual = flour.equals(flour2);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test equals with different name")
    void testEqualsDifferentName() {
        // Arrange
        Ingredient flour2 = new Ingredient("WheatFlour", 2.5, Unit.CUP);

        // Act
        boolean isEqual = flour.equals(flour2);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test equals with different quantity")
    void testEqualsDifferentQuantity() {
        // Arrange
        Ingredient flour2 = new Ingredient("Flour", 3.0, Unit.CUP);

        // Act
        boolean isEqual = flour.equals(flour2);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test equals with different unit")
    void testEqualsDifferentUnit() {
        // Arrange
        Ingredient flour2 = new Ingredient("Flour", 2.5, Unit.PINT);

        // Act
        boolean isEqual = flour.equals(flour2);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test equals with null")
    void testEqualsNull() {
        // Arrange
        Ingredient nullIngredient = null;

        // Act
        boolean isEqual = flour.equals(nullIngredient);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test equals with different class")
    void testEqualsDifferentClass() {
        // Arrange
        String notAnIngredient = "Not an ingredient";

        // Act
        boolean isEqual = flour.equals(notAnIngredient);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test hashCode consistency")
    void testHashCodeConsistency() {
        // Arrange
        Ingredient flour2 = new Ingredient("Flour", 2.5, Unit.CUP);

        // Act
        int hash1 = flour.hashCode();
        int hash2 = flour2.hashCode();

        // Assert
        assertEquals(hash1, hash2);
    }

    @Test
    @DisplayName("Test clone")
    void testClone() {
        // Arrange - using flour

        // Act
        Ingredient clone = flour.clone();

        // Assert
        assertEquals(flour, clone);
        assertNotSame(flour, clone);
    }
}