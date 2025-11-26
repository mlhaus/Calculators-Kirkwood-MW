package edu.kirkwood.controller;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Money$Calculator {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] COIN_NAMES = {"Dollars", "Half-Dollars", "Quarters", "Dimes", "Nickels", "Pennies"};
    private static final int[] COIN_VALUES = {100, 50, 25, 10, 5, 1};
    private static final Random rand = new Random();

    public static void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nAbe's Money Calculator");
            System.out.println("1) Classic Coin Conversion");
            System.out.println("2) Speed Mode");
            System.out.println("3) Hard Mode");
            System.out.println("4) Exit to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": playClassicMode(); break;
                case "2": playSpeedMode(); break;
                case "3": playHardMode(); break;
                case "4":
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // CLASSIC MODE
    private static void playClassicMode() {
        System.out.println("\nClassic Coin Conversion Mode");
        System.out.println("You can only Add or Subtract amounts.");
        System.out.println("Enter -1 at any prompt to return to Money Calculator menu.");
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nEnter first amount:");
            int[] amount1 = getCoinInputReturnOption();
            if (amount1 == null) return;

            System.out.println("Enter second amount:");
            int[] amount2 = getCoinInputReturnOption();
            if (amount2 == null) return;

            int choice = getClassicOperationChoice();
            if (choice == 0) return;

            int total1 = coinsToCents(amount1);
            int total2 = coinsToCents(amount2);
            int result = (choice == 1) ? total1 + total2 : total1 - total2;

            if (result < 0) {
                System.out.print("-"); // negative result
                result = Math.abs(result);
            }

            System.out.println("\nResult:");
            printCoins(result);
        }
    }

    private static int getClassicOperationChoice() {
        System.out.println("\nChoose operation:");
        System.out.println("1) Add");
        System.out.println("2) Subtract");
        System.out.println("0) Back to Menu");
        return getInt("Choice: ", 0, 2);
    }

    // SPEED MODE
    private static void playSpeedMode() {
        System.out.println("\nSpeed Mode: Solve the target amount quickly!");
        System.out.println("Enter -1 at any prompt to return to Money Calculator menu.");
        System.out.println("Press ENTER to start...");
        scanner.nextLine(); // wait for Enter

        int round = 1;
        int roundTime = 30; // starting time
        int incorrectCount = 0;

        while (true) {
            int[] targetCoins = generateRandomCoins();
            int targetCents = coinsToCents(targetCoins);

            System.out.printf("\n--- Round %d ---\n", round);
            System.out.printf("Target amount: $%.2f\n", targetCents / 100.0);
            System.out.printf("You have %d seconds to enter coins.\n", roundTime);

            AtomicBoolean timeUp = new AtomicBoolean(false);
            final int timeForThisRound = roundTime;
            Thread timer = new Thread(() -> {
                try {
                    for (int i = timeForThisRound; i >= 0; i--) {
                        Thread.sleep(1000);
                        if (i == 0) timeUp.set(true);
                    }
                } catch (InterruptedException ignored) {}
            });
            timer.start();

            int[] userCoins = getCoinInputReturnOption(timeUp);
            timer.interrupt();
            if (userCoins == null || timeUp.get()) {
                System.out.println("Returning to Money Calculator menu.");
                return;
            }

            int userCents = coinsToCents(userCoins);
            if (userCents == targetCents) {
                System.out.println("Correct!");
            } else {
                System.out.printf("Incorrect. The correct amount was $%.2f\n", targetCents / 100.0);
                incorrectCount++;
                if (incorrectCount >= 3) {
                    System.out.println("You've failed 3 times. Returning to Money Calculator menu.");
                    return;
                }
            }

            round++;
            roundTime = Math.max(10, roundTime - 5);
        }
    }

    // HARD MODE
    private static void playHardMode() {
        System.out.println("\nHard Mode: Limited coins, tricky amounts!");
        System.out.println("No dollars allowed, only 3 types of coins.");
        System.out.println("You must enter at least 2 coin types with non-zero values.");
        System.out.println("Enter -1 at any prompt to return to Money Calculator menu.");
        System.out.println("Press ENTER to start...");
        scanner.nextLine(); // wait for Enter

        int round = 1;
        int roundTime = 20;

        while (true) {
            int[] targetCoins = new int[6];
            // Choose 3 random coins (excluding dollars)
            int[] allowedIndexes = rand.ints(1, 6).distinct().limit(3).toArray();
            for (int idx : allowedIndexes) targetCoins[idx] = rand.nextInt(5) + 1;
            int targetCents = coinsToCents(targetCoins);

            System.out.printf("\n--- Hard Round %d ---\n", round);
            System.out.printf("Target amount: $%.2f\n", targetCents / 100.0);
            System.out.printf("You have %d seconds to enter coins.\n", roundTime);

            AtomicBoolean timeUp = new AtomicBoolean(false);
            final int timeForThisRound = roundTime;
            Thread timer = new Thread(() -> {
                try {
                    for (int i = timeForThisRound; i >= 0; i--) {
                        Thread.sleep(1000);
                        if (i == 0) timeUp.set(true);
                    }
                } catch (InterruptedException ignored) {}
            });
            timer.start();

            int[] userCoins = getCoinInputReturnOptionHard(timeUp, allowedIndexes);
            timer.interrupt();
            if (userCoins == null || timeUp.get()) {
                System.out.println("Returning to Money Calculator menu.");
                return;
            }

            int userCents = coinsToCents(userCoins);
            if (userCents == targetCents) {
                System.out.println("Correct!");
            } else {
                System.out.printf("Incorrect. The correct amount was $%.2f\n", targetCents / 100.0);
                System.out.println("You've failed Hard Mode. Returning to Money Calculator menu.");
                return;
            }

            round++;
            roundTime = Math.max(5, roundTime - 3);
        }
    }

    // HELPERS
    private static int[] getCoinInputReturnOption() {
        int[] coins = new int[6];
        for (int i = 0; i < 6; i++) {
            int val = getIntWithReturnOption("Enter " + COIN_NAMES[i] + ": ");
            if (val == -1) return null;
            coins[i] = val;
        }
        return coins;
    }

    private static int[] getCoinInputReturnOption(AtomicBoolean timeUp) {
        int[] coins = new int[6];
        for (int i = 0; i < 6; i++) {
            if (timeUp.get()) break;
            int val = getIntWithReturnOption("Enter " + COIN_NAMES[i] + ": ");
            if (val == -1) return null;
            coins[i] = val;
        }
        return coins;
    }

    // Hard mode: must enter at least 2 non-zero coins
    private static int[] getCoinInputReturnOptionHard(AtomicBoolean timeUp, int[] allowedIndexes) {
        int[] coins = new int[6];
        System.out.println("You can only enter values for the following coins:");
        for (int idx : allowedIndexes) System.out.println("- " + COIN_NAMES[idx]);

        while (true) {
            int nonZeroCount = 0;
            for (int idx : allowedIndexes) {
                if (timeUp.get()) break;
                int val = getIntWithReturnOption("Enter " + COIN_NAMES[idx] + ": ");
                if (val == -1) return null;
                coins[idx] = val;
                if (val != 0) nonZeroCount++;
            }
            if (nonZeroCount >= 2) break;
            System.out.println("You must enter at least 2 coin types with values greater than zero. Try again.");
        }
        return coins;
    }

    private static int getIntWithReturnOption(String prompt) {
        while (true) {
            System.out.print(prompt + " (or -1 to return): ");
            String line = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val >= -1) return val;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Try again.");
        }
    }

    private static int getInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Try again.");
        }
    }

    private static int coinsToCents(int[] coins) {
        int total = 0;
        for (int i = 0; i < coins.length; i++) total += coins[i] * COIN_VALUES[i];
        return total;
    }

    private static void printCoins(int totalCents) {
        if (totalCents < 0) {
            System.out.print("-");
            totalCents = Math.abs(totalCents);
        }
        int dollars = totalCents / 100; totalCents %= 100;
        int half = totalCents / 50; totalCents %= 50;
        int quarter = totalCents / 25; totalCents %= 25;
        int dime = totalCents / 10; totalCents %= 10;
        int nickel = totalCents / 5; totalCents %= 5;
        int penny = totalCents;

        System.out.printf("Dollars: %d, Half-Dollars: %d, Quarters: %d, Dimes: %d, Nickels: %d, Pennies: %d\n",
                dollars, half, quarter, dime, nickel, penny);
    }

    private static int[] generateRandomCoins() {
        int[] coins = new int[6];
        for (int i = 0; i < 6; i++) coins[i] = rand.nextInt(5);
        return coins;
    }
}
