package edu.kirkwood.model;


/**
 * Converts temperatures from Celsius, Fahrenheit and Kelvin.
 */
public class RyanTemperature {

    /**
     *Converts Celsius to Fahrenheit
     *
     * @param celsius the temperature in degrees Celsius
     * @return the temperature in degrees Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    /**
     *Converts Fahrenheit to Celsius
     *
     * @param fahrenheit the temperature in degrees Fahrenheit
     * @return the temperature in degrees Celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    /**
     * Converts Celsius to Kelvin
     *
     * @param celsius The temperature being input in Celsius
     * @return The temperature in Kelvin
     */
    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    /**
     * Converts Kelvin to Celsius
     *
     * @param kelvin The temperature being input in Kelvin
     * @return The temperature in Celsius
     */
    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    /**
     * Converts Fahrenheit to Kelvin
     *
     * @param fahrenheit The temperature being input in Fahrenheit
     * @return The temperature in Kelvin
     */
    public static double fahrenheitToKelvin(double fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9) + 273.15;
    }

    /**
     * Converts Kelvin to Fahrenheit
     *
     * @param kelvin The temperature being input in Kelvin
     * @return The temperature in Fahrenheit
     */
    public static double kelvinToFahrenheit(double kelvin) {
        return ((kelvin - 273.15) * 9 / 5) + 32;
    }

    /**
     *
     * @param value the current temperature value
     * @return that value rounded to two decimal places
     */
    public static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
