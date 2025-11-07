package edu.kirkwood.controller;

import edu.kirkwood.model.ICalculation;
import edu.kirkwood.view.UserInput;

import static edu.kirkwood.view.Messages.investmentGoodbye;
import static edu.kirkwood.view.Messages.investmentGreet;

public class InvestmentCalculator {

    // starts the method
    public static void start() {
        investmentGreet();
        System.out.println("\n--- Investment Calculator ---");

        //Inputs
        double total = UserInput.getDouble("Enter total amount invested: ");
        double stockRate = UserInput.getDouble("Enter stock interest rate : ");
        double bondRate = UserInput.getDouble("Enter bond interest rate : ");
        double interest = UserInput.getDouble("Enter total annual interest income: ");


        double[] result = solveCalculation(total, stockRate, bondRate, interest);

        // Outputs
        if (result != null) {
            System.out.printf("Invested in stocks: $%.2f%n", result[0]);
            System.out.printf("Invested in bonds:  $%.2f%n", result[1]);
        }

        investmentGoodbye();
    }

    public static double[] solveCalculation(double total, double stockRate, double bondRate, double interest) {

        // Rate conversion
        double stockRatePercent = stockRate / 100.0;
        double bondRatePercent = bondRate / 100.0;

        try {
            ICalculation calc = new ICalculation(total, stockRatePercent, bondRatePercent, interest);
            return calc.solve();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
