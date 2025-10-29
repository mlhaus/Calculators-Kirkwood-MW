package edu.kirkwood.model;

/**
 * Represents a temperature with a value and scale (Celsius, Fahrenheit, or Kelvin).
 */
public class EthansTemperature {
    private double value;
    private String scale;

    /**
     * Constructs a EthansTemperature object.
     *
     * @param value the numeric value of the temperature
     * @param scale the scale of the temperature ("C", "F", or "K")
     */
    public EthansTemperature(double value, String scale) {
        this.value = value;
        this.scale = scale.toUpperCase();
    }

    /**
     * Converts this temperature to Fahrenheit.
     *
     * @return the temperature value in Fahrenheit
     */
    public double toFahrenheit() {
        return switch (scale) {
            case "C" -> (value * 9 / 5) + 32;
            case "K" -> (value - 273.15) * 9 / 5 + 32;
            case "F" -> value;
            default -> throw new IllegalArgumentException("Invalid scale: " + scale);
        };
    }

    /**
     * Converts this temperature to Celsius.
     *
     * @return the temperature value in Celsius
     */
    public double toCelsius() {
        return switch (scale) {
            case "F" -> (value - 32) * 5 / 9;
            case "K" -> value - 273.15;
            case "C" -> value;
            default -> throw new IllegalArgumentException("Invalid scale: " + scale);
        };
    }

    /**
     * Converts this temperature to Kelvin.
     *
     * im not sure the specifics behind the enhanced switch statements
     * intellij suggested them and they work so i just left it
     *
     * @return the temperature value in Kelvin
     */
    public double toKelvin() {
        return switch (scale) {
            case "C" -> value + 273.15;
            case "F" -> (value - 32) * 5 / 9 + 273.15;
            case "K" -> value;
            default -> throw new IllegalArgumentException("Invalid scale: " + scale);
        };
    }

    public double getValue() {
        return value;
    }

    public String getScale() {
        return scale;
    }

    /**
     * Sets the scale of the temperature
     *
     * @param scale the new scale to use
     * @throws IllegalArgumentException if scale is invalid format
     */
    public void setScale(String scale) {
        if (scale.equals("C") || scale.equals("F") || scale.equals("K")) {
            this.scale = scale;
        }
        if (!scale.equals("C") && !scale.equals("F") && !scale.equals("K")) {
            throw new IllegalArgumentException("Invalid scale: " + scale);
        }
    }

    /**
     * Sets the scale of the temperature
     *
     * @param value the new value to use
     * @throws ArithmeticException if scale is invalid format
     */
    public void setValue(String value) {
        if (this.scale.equals("C") && Double.parseDouble(value) >= -273.15 ||
                this.scale.equals("K") && Double.parseDouble(value) >= 0 ||
                this.scale.equals("F") && Double.parseDouble(value) >= -459.67)
        {
            this.value = Double.parseDouble(value);
        }

        if (this.scale.equals("C") && Double.parseDouble(value) <= -273.15 ||
                this.scale.equals("K") && Double.parseDouble(value) <= 0 ||
                this.scale.equals("F") && Double.parseDouble(value) <= -459.67)
        {
            throw new ArithmeticException("EthansTemperature cannot be below absolute zero!");
        }
    }

    /**
     * Returns a string representation of the temperature in the format value then scale.
     *
     * @return a string representation of the temperature object
     */
    @Override
    public String toString() {return value + scale;}
}
