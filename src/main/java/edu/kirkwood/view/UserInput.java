package edu.kirkwood.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static edu.kirkwood.view.Helpers.formatDateLong;
import static edu.kirkwood.view.Helpers.isValidString;
import static edu.kirkwood.view.UIUtility.displayError;


public class UserInput {

    private static Scanner scanner = new Scanner(System.in);

    // Lawson
    /**
     *
     * @param prompt A prompt that comes with the request
     * @return An int that has the constraints.
     */
    public static Integer getInt(String prompt) {
        return getInt(prompt, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Lawson
    /**
     *
     * @param prompt A prompt that comes with the request
     * @param required A boolean for whether the input is needed.
     * @return An int that has the constraints.
     */
    public static Integer getInt(String prompt, boolean required) {
        return getInt(prompt, required, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Lawson
    /**
     *
     * @param prompt A prompt that comes with the request
     * @param required A boolean for whether the input is needed.
     * @param min The minimum possible value an integer can be.
     * @return An int that has the constraints.
     */
    public static Integer getInt(String prompt, boolean required, int min) {
        return getInt(prompt, required, min, Integer.MAX_VALUE);
    }

    // Lawson
    /**
     *
     * @param prompt A prompt that comes with the request
     * @param required A boolean for whether the input is needed.
     * @param min The minimum possible value an integer can be.
     * @param max The maximum possible value an integer can be.
     * @return An int that has the constraints.
     */
    public static int getInt(String prompt, boolean required, int min, int max) {
        int value = 0;

        String minMax = "";
        // if min is set and max is not set
        if(min != Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
            minMax = String.format(" [minimum %d]", min);
        }
        // if min and max are both set
        if(min != Integer.MIN_VALUE && max != Integer.MAX_VALUE) {
            minMax = String.format(" [between %d and %d]", min, max);
        }

        while(true) {
            System.out.print(prompt + minMax + (required ? " (*)" : "") + ": ");
            String valueStr = scanner.nextLine();
            try {
                value = Integer.parseInt(valueStr);
            } catch (NumberFormatException e) {
                if(!required) {
                    return Integer.MIN_VALUE;
                } else {
                    displayError("Invalid integer");
                    continue;
                }
            }

            if(value < min) {
                displayError("Value too low");
            } else if(value > max) {
                displayError("Value too high");
            } else {
                break;
            }
        }
        return value;
    }

    public static String getString(String prompt) {
        return getString(prompt, true);
    }

    public static String getString(String prompt, boolean required) {
        String value = "";
        while(true) {
            System.out.print(prompt + (required ? " (*)" : "") + ": ");
            value = scanner.nextLine().trim();
            if(required && !isValidString(value)) {
                displayError("Input required");
            } else {
                break;
            }
        }
        return value;
    }

    // Blake
    /**
     * Prompts the user for boolean input. Displays the prompt to the user along with
     * [y/n], and accepts an input of yes, no, and upper or lower case y or n. Returns the converted boolean
     * input from the user.
     *
     * @param prompt String prompt for input from the user
     * @return Boolean response representing a yes or no from the user
     */
    public static boolean getBoolean(String prompt) {
        return getBoolean(prompt, true);
    }

    // Blake
    /**
     * Prompts the user for boolean input. Displays the prompt to the user along with
     * [y/n], and accepts an input of yes, no, and upper or lower case y or n. Returns the converted boolean
     * input from the user.
     *
     * @param prompt String prompt for input from the user
     * @param required Boolean indicates whether tbe response is required
     * @return Boolean response representing a yes or no from the user
     */
    public static boolean getBoolean(String prompt, boolean required) {
        boolean value = true;
        while(true) {
            String valueStr = getString(prompt + " [y/n]", required);
            if(required && !(valueStr.equalsIgnoreCase("y") ||
                    valueStr.equalsIgnoreCase("n") ||
                    valueStr.equalsIgnoreCase("yes") ||
                    valueStr.equalsIgnoreCase("no"))
            ) {
                displayError("Invalid input");
            } else {
                value = valueStr.equalsIgnoreCase("y") || valueStr.equalsIgnoreCase("yes");
                break;
            }
        }
        return value;
    }

    /**
     *
     * @param prompt A message prompt accompanying the input request
     * @return value A double containing the method constraints
     */
    public static double getDouble(String prompt) {
        return getDouble(prompt, true, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     *
     * @param prompt A message prompt accompanying the input request
     * @param required A boolean flag for whether the input is required
     * @return value A double containing the method constraints
     */
    public static double getDouble(String prompt, boolean required) {
        return getDouble(prompt, required, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     *
     * @param prompt A message prompt accompanying the input request
     * @param required A boolean flag for whether the input is required
     * @param min A double value for a numeric minimum
     * @return value A double containing the method constraints
     */
    public static double getDouble(String prompt, boolean required, int min) {
        return getDouble(prompt, required, min, Double.MAX_VALUE);
    }

    // Robert
    /**
     * Handles getting and validating user input for decimal values
     * @param prompt A message prompt accompanying the input request
     * @param required A boolean flag for whether the input is required
     * @param min A double value for a numeric minimum
     * @param max A double value for a numeric maximum
     * @return value A double containing the method constraints
     */
    public static double getDouble(String prompt, boolean required, double min, double max) {
        double value = 0;

        String minMax = "";
        // if min is set and max is not set
        if(min != -Double.MAX_VALUE && max == Double.MAX_VALUE) {
            minMax = String.format(" [minimum %.1f]", min);
        }
        // if min and max are both set
        if(min != -Double.MAX_VALUE && max != Double.MAX_VALUE) {
            minMax = String.format(" [between %.1f and %.1f]", min, max);
        }

        while(true) {
            System.out.print(prompt + minMax + (required ? " (*)" : "") + ": ");
            String valueStr = scanner.nextLine();
            try {
                value = Double.parseDouble(valueStr);
            } catch (NumberFormatException e) {
                if(!required) {
                    return -Double.MAX_VALUE;
                } else {
                    displayError("Invalid number");
                    continue;
                }
            }

            if(value < min) {
                displayError("Value too low");
            } else if(value > max) {
                displayError("Value too high");
            } else {
                break;
            }
        }
        return value;
    }

    // Abraham
    /**
     * Prompts the user to enter a date and returns it as a {@link java.time.LocalDate}.
     * <p>
     * This method calls {@code getDate(prompt, true)}, meaning the input is required.
     * The user will be repeatedly prompted until a valid date is entered.
     * The expected input format typically matches the system default (e.g., yyyy-MM-dd).
     *
     * @param prompt A prompt that comes with the request and is displayed before input.
     * @return A {@link java.time.LocalDate} object that represents the date entered by the user.
     */
    public static LocalDate getDate(String prompt) {
        return getDate(prompt, true);
    }

    public static LocalDate getDate(String prompt, boolean required) {
        LocalDate date = null;
        while(true) {
            String dateStr = getString(prompt + " [MM/DD/YYYY]", required);
            try {
                DateTimeFormatter dateFormatInput = DateTimeFormatter.ofPattern("M/d/yyyy");
                date = LocalDate.parse(dateStr, dateFormatInput);
                break;
            } catch(DateTimeParseException e) {
                if(!required) {
                    return LocalDate.MIN;
                } else {
                    displayError("Invalid date");
                }
            }
        }
        return date;
    }
}
