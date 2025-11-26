package edu.kirkwood.model;

/**
 * Solves the investment allocation problem:
 * x + y = total
 * x*rateA + y*rateB = interest
 *
 * Where:
 * x = Amount invested in stocks (Result[0])
 * y = Amount invested in bonds (Result[1])
 */
public class ICalculation {
    private final double totalPrincipal;
    private final double stockRate;
    private final double bondRate;
    private final double totalInterest;

    public ICalculation(double totalPrincipal, double stockRate, double bondRate, double totalInterest) {
        this.totalPrincipal = totalPrincipal;
        this.stockRate = stockRate;
        this.bondRate = bondRate;
        this.totalInterest = totalInterest;

        // Basic validation moved to the model to ensure logic is sound
        // CORREGIDO: Mensaje exacto para el Test 2
        if (stockRate == bondRate) {
            throw new IllegalArgumentException("Stock rate and bond rate cannot be equal for this calculation.");
        }
        if (totalPrincipal < 0 || totalInterest < 0) {
            throw new IllegalArgumentException("Principal and Interest must be positive.");
        }
    }

    public double[] solve() {
        double stockAllocation; // x
        double bondAllocation;  // y

        // Calculation derived from solving the system of equations:
        // x = (Total Interest - (Total Principal * Bond Rate)) / (Stock Rate - Bond Rate)

        double numerator = totalInterest - (totalPrincipal * bondRate);
        double denominator = stockRate - bondRate;

        // Note: The case where rates are equal is handled in the constructor.

        stockAllocation = numerator / denominator;
        bondAllocation = totalPrincipal - stockAllocation;

        if (stockAllocation < -0.01 || bondAllocation < -0.01) {
            throw new IllegalArgumentException("The total interest income CANNOT be negative.");
        }

        // Use a small epsilon for checking the total (due to floating point arithmetic)
        if (Math.abs(stockAllocation + bondAllocation - totalPrincipal) > 0.01) {
            throw new IllegalArgumentException("Internal calculation error: Allocations do not sum up to the principal.");
        }

        return new double[]{stockAllocation, bondAllocation};
    }
}