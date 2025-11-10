package edu.kirkwood.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Comprehensive unit tests for the core mathematical logic in ICalculation.
 */
class ICalculationTest {

    // Tolerance for comparing floating-point numbers
    private static final double DELTA = 0.01;

    // Helper method for cleaner test calls
    private double[] calculate(double total, double stockRate, double bondRate, double interest) {
        ICalculation calc = new ICalculation(total, stockRate, bondRate, interest);
        return calc.solve();
    }

    // --- TESTS DE CASOS DE ÉXITO (Happy Path) ---

    // 1. Caso Base: Inversiones iguales (50/50).
    @Test
    void test01_solve_returnsCorrectInvestments_50_50() {
        // ARRANGE: $10000 total, 8% (0.08) y 4% (0.04) rates. Interest: $600. Solución: 5000/5000
        double[] result = calculate(10000, 0.08, 0.04, 600);

        // ASSERT
        assertEquals(5000.0, result[0], DELTA, "1. Stock investment should be $5000.00");
        assertEquals(5000.0, result[1], DELTA, "1. Bond investment should be $5000.00");
    }

    // 2. Caso Asimétrico: Más en la tasa baja (Ej. 75/25).
    @Test
    void test02_solve_returnsAsymmetricInvestments() {
        // ARRANGE: $10000 total, 8% y 4% rates. Interest: $500. Solución: 2500/7500
        // (0.08 * 2500) + (0.04 * 7500) = 200 + 300 = 500
        double[] result = calculate(10000, 0.08, 0.04, 500);

        // ASSERT
        assertEquals(2500.0, result[0], DELTA, "2. Stock investment incorrect (Expected 2500)");
        assertEquals(7500.0, result[1], DELTA, "2. Bond investment incorrect (Expected 7500)");
    }

    // 3. Caso Límite: Todo invertido en el activo de mayor tasa.
    @Test
    void test03_solve_returnsBoundary_AllInHigherRate() {
        // ARRANGE: Max interest is 8% of 10000 = $800. Solución: 10000/0
        double[] result = calculate(10000, 0.08, 0.04, 800);

        // ASSERT
        assertEquals(10000.0, result[0], DELTA, "3. Stock investment should be $10000.00");
        assertEquals(0.0, result[1], DELTA, "3. Bond investment should be $0.00");
    }

    // 4. Caso Límite: Todo invertido en el activo de menor tasa.
    @Test
    void test04_solve_returnsBoundary_AllInLowerRate() {
        // ARRANGE: Min interest is 4% of 10000 = $400. Solución: 0/10000
        double[] result = calculate(10000, 0.08, 0.04, 400);

        // ASSERT
        assertEquals(0.0, result[0], DELTA, "4. Stock investment should be $0.00");
        assertEquals(10000.0, result[1], DELTA, "4. Bond investment should be $10000.00");
    }

    // 5. Caso Límite: Inversión total cero (pero con tasas válidas)
    @Test
    void test05_solve_boundary_ZeroTotal() {
        // ARRANGE: $0 total, $0 interest.
        double[] result = calculate(0.0, 0.08, 0.04, 0.0);

        // ASSERT
        assertEquals(0.0, result[0], DELTA, "5. Stock investment must be $0.00");
        assertEquals(0.0, result[1], DELTA, "5. Bond investment must be $0.00");
    }


    // --- TESTS DE CASOS DE ERROR (Boundary & Error Cases) ---

    // 6. Test de Error: Interés imposiblemente alto (Más alto que el máximo posible).
    @Test
    void test06_solve_throwsException_InterestTooHigh() {
        // ARRANGE: Máximo posible es $800. Pedimos $801.
        assertThrows(IllegalArgumentException.class, () -> {
            calculate(10000, 0.08, 0.04, 801.0);
        }, "6. Should throw an exception when interest is too high.");
    }

    // 7. Test de Error: Interés imposiblemente bajo (Menos que el mínimo posible).
    @Test
    void test07_solve_throwsException_InterestTooLow() {
        // ARRANGE: Mínimo posible es $400. Pedimos $399.
        // Esperamos el mensaje que indica que el resultado es negativo.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculate(10000, 0.08, 0.04, 399.0);
        });

        // El mensaje esperado es el que lanza ICalculation.java
        assertEquals("The total interest income CANNOT be negative.", exception.getMessage());
    }

    // 8. Test de Error: Tasas iguales (La excepción debe lanzarse en el constructor).
    @Test
    void test08_solve_throwsException_whenRatesAreEqual() {
        // CORRECCIÓN: La excepción debe capturarse al intentar crear la instancia.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ICalculation(10000, 0.05, 0.05, 500);
        });

        // Mensaje debe coincidir con el código de ICalculation.java
        assertEquals("Stock rate and bond rate cannot be equal for this calculation.", exception.getMessage());
    }
}