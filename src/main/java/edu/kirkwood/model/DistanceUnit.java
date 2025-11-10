/**
 * @file        DistanceUnit.java
 * @author      Robert Biggart
 * @date        2025/09/19
 * @description This file contains the model for distance units along with the logic to convert them. My reasoning for
 * this is due to how closely coupled it is. Logic relating to passing in fractions and mixed numbers will be handled
 * within DistanceUnitHandler.
 * MODIFICATION HISTORY:
 *  2025/09/26
 *  Slimmed down the logic for Unit conversion into just one method. I thought it would be better, but it looked messy
 *  and wasn't readable as I thought.
 *  2025/09/26
 *  Added micrometers, considered nautical miles, but I'd have to rewrite the logic within the calculator.
 */
package edu.kirkwood.model;

/**
 * The data model for Metric and Imperial units of distance.
 *
 */
public enum DistanceUnit {
    THOU("thou", "thou", 0.0000254),                    // 1 thou = 0.0000254 meters
    INCH("inch", "inches", 0.0254),                     // 1 inch = 0.0254 meters
    FOOT("foot", "feet", 0.3048),                       // 1 foot = 0.3048 meters
    YARD("yard", "yards", 0.9144),                      // 1 yard = 0.9144 meters
    MILE("mile", "miles", 1609.34),                     // 1 mile = 1609.344 meter

    MICROMETER("micrometer", "micrometers", 0.000001),  // 1 micrometer = 0.000001 meters
    MILLIMETER("millimeter", "millimeters", 0.001),     // 1 millimeter = 0.001 meters
    CENTIMETER("centimeter", "centimeters", 0.01),      // 1 centimeter = 0.001 meters
    METER("meter", "meters", 1.0),                      // 1 meter = 1 meter
    KILOMETER("kilometer", "kilometers", 1000.0);       // 1 kilometer = 1000 meters


    private final String singularUnitName;  // The singular variant of the unit name.
    private final String pluralUnitName;    // The plural variant of the unit name

    private final double toMeters;      // The Conversion factor of the unit to a meter


    /**
     * Constructs a DistanceUnit with its singular name, plural name,
     * and the conversion factor to meters.
     *
     * @param singularUnitName Singular variant of the unit name
     * @param pluralUnitName   Plural variant of the unit name
     * @param toMeters     Conversion factor to meters
     */
    DistanceUnit(String singularUnitName, String pluralUnitName, double toMeters) {
        this.singularUnitName = singularUnitName;
        this.pluralUnitName = pluralUnitName;
        this.toMeters = toMeters;
    }

    /**
     * Gets the non-plural variant of the unit name.
     * @return The name of the unit
     */
    public String getSingularUnitName() {
        return singularUnitName;
    }

    /**
     * Gets the plural variant of the unit name.
     * @return The name of the unit.
     */
    public String getPluralUnitName() {
        return pluralUnitName;
    }

    /**
     * Converts a string into the matching DistanceUnit enum.
     * Handles both singular and plural names (e.g., "mile" or "miles")
     *
     * @param unitStr The unit name entered by the user.
     * @return The corresponding DistanceUnit enum, or null if not found.
     */
    public static DistanceUnit parseUnit(String unitStr) {
        if (unitStr == null || unitStr.trim().isEmpty()) {

            // Catches null or empty inputs for the unit value
            throw new IllegalArgumentException("The Unit cannot be null or empty.");
        }

        String normalized = unitStr.trim().toLowerCase();

        for (DistanceUnit unit : DistanceUnit.values()) {
            // A null check on the getters would make this even more robust
            if (normalized.equals(unit.getSingularUnitName().toLowerCase()) ||
                    normalized.equals(unit.getPluralUnitName().toLowerCase())) {
                return unit;
            }
        }

        // Catches non-existent unit error if loop finishes.
        throw new IllegalArgumentException("Invalid unit: '" + unitStr + "'.");
    }


    /**
     * Formats a string output containing a numeric value and an associated unit.
     * @param value A numeric value to format
     * @return Returns a string with the value and the associated unit
     */
    public String formatValue(double value) {
        String name = (Math.abs(value) == 1.0) ? singularUnitName : pluralUnitName;
        return value + " " + name;
    }

    /**
     * Converts a value from one distance unit to another.
     * @param value The numeric value to convert
     * @param currentUnit The current unit
     * @param targetUnit The target unit of conversion
     * @return The converted value rounded to rounds the value to 4 decimal places
     * @throws IllegalArgumentException if current and target are the same
     */
    public static double convert(double value, DistanceUnit currentUnit, DistanceUnit targetUnit) {
        if (currentUnit == null || targetUnit == null) {
            throw new IllegalArgumentException("Source and target units must not be null.");
        }

        if (currentUnit == targetUnit) {
            throw new IllegalArgumentException(
                    "Cannot convert from " + currentUnit.getSingularUnitName() +
                            " to " + targetUnit.getSingularUnitName() + "."
            );
        }


        // Convert current value to meters
        double valueInMeters = value * currentUnit.toMeters;

        // Convert value in meters into the target unit
        double result = valueInMeters / targetUnit.toMeters;

        // Round to 4 decimal places (Got this suggestion from Stack Overflow as I forgot how the round method works)
        // https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
        return Math.round(result * 10000.0) / 10000.0;
    }
}