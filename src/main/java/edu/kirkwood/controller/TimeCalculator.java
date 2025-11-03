package edu.kirkwood.controller;

import edu.kirkwood.model.Time;

import static edu.kirkwood.view.Messages.timeGoodbye;
import static edu.kirkwood.view.Messages.timeGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class TimeCalculator {


    /**
     * the main loop of the time calculator
     */
    public  static void start(){
        timeGreet();
        Time time = null;
        while(true) {

            String value = getString("Enter your equation (or 'q' to quit)");
            if(value.equalsIgnoreCase("q") || value.equalsIgnoreCase("quit")) {
                break;
            }
            String[] input = null;
            try{
                input = splitCalculation(value);
            }
            catch (Exception ex){
                System.out.println("make sure your operand has a space on both sides");
                continue;
            }
            Time outputTime1 = null;
            Time time2 = null;
            int num = -1;
            String operation = input[1];
            try{
                time = parseTime(input[0]);
            } catch (IllegalArgumentException e){
                if(input[0].equalsIgnoreCase("answer") || input[0].equalsIgnoreCase("ans")|| input[0].equalsIgnoreCase("a")){
                    if(time == null) {
                        System.out.println("you can only use 'answer' when you have a valid answer");
                        continue;
                    }
                }
                else {
                    System.out.println("First unit is invalid");
                    continue;
                }
            }
            outputTime1 = new Time(time.getMilliseconds());
            if(input[2].contains(" ")){
                try{
                    time2 = parseTime(input[2]);
                } catch (IllegalArgumentException e){
                    System.out.println("Second unit is invalid");
                    time = null;
                    continue;
                }
            } else {
                try {
                    num = Integer.parseInt(input[2]);
                }catch (NumberFormatException e){
                    System.out.println("Invalid number");
                    time = null;
                    continue;
                }
            }

            // Perform mathematical operations
            switch (operation) {
                case "+" -> {
                    if (time2 == null) {
                        displayError("You can only add time to time");
                        time = null;
                        continue;
                    } else {
                        time.add(time2);
                    }
                }
                case "-" -> {
                    if (time2 == null) {
                        displayError("You can only subtract time from time");
                        time = null;
                        continue;
                    } else {
                        if (time.getMilliseconds() < time2.getMilliseconds()) {
                            displayError("You can not subtract a larger time from a smaller one");
                            time = null;
                            continue;
                        }
                        time.subtract(time2);
                    }
                }
                case "*" -> {
                    if (time2 != null) {
                        displayError("You can only multiply with an integer");
                        time = null;
                        continue;
                    } else {
                        time.multiply(num);
                    }
                }
                case "/" -> {
                    if (time2 == null) {
                        if (num > 0) {
                            time.divide(num);
                        } else {
                            displayError("you must provide a number greater than zero or time to divide by");
                            time = null;
                            continue;
                        }
                    } else {

                        System.out.println(outputTime1.toString() + " " + input[1] + " " + time2.toString() + " = " + time.divide(time2));
                        time = null;
                        continue;
                    }
                }
            }
            System.out.println(outputTime1.toString() + " " + input[1] + " " + (time2 != null? time2.toString() : num) + " = " + time.toString());
        }
        timeGoodbye();
        pressEnterToContinue();
    }

    /**
     * @param input the raw input from the user
     * @return it split into 3 parts
     */
    public static String[] splitCalculation(String input) {
        String operator = "";
        int operatorIndex = -1;

        if(input.contains(" + ")) {
            operator = "+";
            operatorIndex = input.indexOf(" + ");
        } else if(input.contains(" - ")) {
            operator = "-";
            operatorIndex = input.indexOf(" - ");
        } else if(input.contains(" * ")) {
            operator = "*";
            operatorIndex = input.indexOf(" * ");
        } else if(input.contains(" / ")) {
            operator = "/";
            operatorIndex = input.indexOf(" / ");
        }

        if(operatorIndex == -1) {
            throw new IllegalArgumentException("Invalid format. Ensure operator (+, -, *, /) has a space on both sides.");
        }

        String time1 = input.substring(0, operatorIndex).trim();
        String time2 = input.substring(operatorIndex + 3).trim();

        if(time1.isEmpty()) {
            throw new IllegalArgumentException("Missing Time 1.");
        }

        if(time2.isEmpty()) {
            throw new IllegalArgumentException("Missing Time 2.");
        }

        return new String[]{time1, operator, time2};
    }

    /**
     * @param str a string containing a time
     * @return a time object
     */
    public static Time parseTime(String str){
        Time result = null;
        if(str.contains(" ")){
            String[] parts = str.split(" ");
            if(parts.length != 2)
            {
                throw new IllegalArgumentException("incorrect quantity of units");
            }
            int num;
            try {
                num = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid whole number. '" + parts[0] + "' (the correct format is '[Number] [Unit]')");
            }
            if(num < 1){
                throw new IllegalArgumentException("number must be larger than zero");
            }
            String unit = parts[1];
            try{
                result = new Time(num,unit);
            } catch (IllegalArgumentException e)
            {
                throw e;
            }
        }
        else{
            throw new IllegalArgumentException("not a time");
        }
        return result;
    }
}
