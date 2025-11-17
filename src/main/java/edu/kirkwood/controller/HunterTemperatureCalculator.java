package edu.kirkwood.controller;

import edu.kirkwood.model.HunterTemperature;

import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getString;

public class HunterTemperatureCalculator {

    public static void start(){
        String[] valueArray = null;
        HunterTemperature temperature = null;
        double convertedTemperature = 0;

        displayMessage("Welcome to Hunter's Temperature Converter");
        System.out.println("Enter a conversion in the format: [value] [unit] to [unit]");
        System.out.println("Example: 32 Fahrenheit to Celsius");
        System.out.println("Example: 32 F to C\n");

        while(true){
            String value = getString("Enter your conversion (or 'q' to quit)");
            if(value.equalsIgnoreCase("q") || value.equalsIgnoreCase("quit")) {
                break;
            }

            try{
                valueArray = splitUserInput(value);
            }
            catch (IllegalArgumentException e){
                displayError(e.getMessage());
                continue;
            }
            try{
                temperature = parseTemperature(valueArray);
            }
            catch (IllegalArgumentException e){
                displayError(e.getMessage());
                continue;
            }

            if(valueArray[3].equals("f") || valueArray[3].equals("fahrenheit")) {
                convertedTemperature = temperature.convertToFahrenheit();
            }
            else if(valueArray[3].equals("c") || valueArray[3].equals("celsius")) {
                convertedTemperature = temperature.convertToCelsius();
            }
            else if(valueArray[3].equals("k") || valueArray[3].equals("kelvin")) {
                convertedTemperature = temperature.convertToKelvin();
            }

            System.out.println(convertedTemperature);
        }
        displayMessage("Thank you for using Hunter's Temperature Converter have a good day.");
        pressEnterToContinue();
    }

    /**
     * Splits the user's input into three sections and validates the input
     * @param input the string to split
     * @return a split string in the format {Type, Value, Type}
     */
    public static String[] splitUserInput(String input){
        String[] result = null;
        String[] array = input.toLowerCase().trim().split(" ");
        List<String> list = new ArrayList<String>();

        for(int i = 0; i < array.length; i++){
            if(!array[i].equals("")){
                list.add(array[i].trim());
            }
        }

        if(list.size() == 4 && containsTwoTemperatureTypes(list) && list.get(2).equals("to")){
            result = list.toArray(new String[list.size()]);
        }
        else if(list.size() != 4 || !list.get(2).equals("to")){
            throw new IllegalArgumentException("Invalid format please enter in the format " +
                    "[value] [type] to [type]");
        }
        else{
            throw new IllegalArgumentException("Invalid temperature type please enter F C or K");
        }
        return result;
    }

    /**
     * Checks if there are two temperature types
     * @param list List of strings the method is checking for temperature types
     * @return false by default and true if there are two temperature types exist
     */
    public static boolean containsTwoTemperatureTypes(List<String> list){
        boolean result = false;
        boolean firstValid = false;
        boolean secondValid = false;
        String[] array = {"fahrenheit","celsius","kelvin","f","c","k"};

        for(int i = 0; i < array.length; i++){
            if(list.get(1).toLowerCase().equals(array[i])){
                firstValid = true;
            }
            if(list.get(3).toLowerCase().equals(array[i])){
                secondValid =  true;
            }
        }

        if(firstValid && secondValid){
            result = true;
        }
        return  result;
    }

    /**
     * Takes in an array and converts the elements into
     * an object of type Temperature
     * @param array the Temperatures information
     * @return a Temperature object created from the array
     */
    public static HunterTemperature parseTemperature(String[] array){

        double temperatureValue = 0;
        String temperatureType = "";
        HunterTemperature result = null;

        try{
            temperatureValue = Double.parseDouble(array[0]);
            temperatureType = array[1];
            result = new HunterTemperature(temperatureValue, temperatureType);

        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid temperature value. " + array[0] + " (Exceeds the lower bound).)");
        }
        return result;
    }
}
