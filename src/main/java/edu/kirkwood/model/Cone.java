package edu.kirkwood.model;

public class Cone {

    // Attributes
    private double radius;
    private double height;
    private double slantHeight;

    //toString
    @Override
    public String toString() {
        return "Cone{" +
                "radius=" + radius +
                ", height=" + height +
                ", slantHeight=" + slantHeight +
                '}';
    }

    //Constructor
    /**
     * @param radius Radius of the cone.
     * @param height Height of the cone.
     * @param slantHeight Slant height of the cone.
     */
    public Cone(double radius, double height, Double slantHeight) {
        this.radius = radius;
        this.height = height;
        this.slantHeight = slantHeight;
    }

    // Getters and Setters
    public double getSlantHeight() {
        return slantHeight;
    }

    public void setSlantHeight(double slantHeight) {
        this.slantHeight = slantHeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     *
     * @param height A Double height of the cone.
     * @param slantHeight a Double slantLength of the cone.
     * @return the length of the Radius of the cone.
     */
    // Helpers for the missing Dimension
    public static double calculateRadius(double height, double slantHeight) {
        return Math.sqrt((slantHeight * slantHeight) - (height * height));
        // radius is a or b in a^2 + b^2 = c^2
    }

    /**
     *
     * @param radius A double radius of the cone.
     * @param slantHeight A double slantHeight or Hypotenuse of the cone.
     * @return the length of the height if the cone.
     */
    public static double calculateHeight(double radius, double slantHeight) {
        return Math.sqrt(slantHeight * slantHeight - radius * radius);
        // height is a or b in a^2 + b^2 = c^2
    }

    /**
     *
     * @param radius A double radius of the cone.
     * @param height A double height of the cone.
     * @return the slantHeight or the Hypotenuse of the cone.
     */
    public static double calculateSlantHeight(double radius, double height) {
        return Math.sqrt(radius * radius + height * height);
        // slantHeight is c in a^2 + b^2 = c^2
    }

    /**
     * This calculates the volume of a cone
     * @return the volume of a cone
     */
    // volume of a cone
    public double getVolume() {
        return (1.0/3.0) * Math.PI * Math.pow(radius, 2) * height;
        // 1/3 * 3.14 * r^2 * h
    }

    /**
     * This calculates the curved surface are, or the lateral surface area.
     * @return the total lateral surface area of the cone.
     */
    // curved surface area (lateral surface area)
    public double getLateralSurfaceArea() {
        return Math.PI * radius * slantHeight;
        // 3.14 * r * l
    }

    /**
     * this calculates the total surface area.
     * @return total surface area of the cone.
     */
    // total surface area
    public double getTotalSurfaceArea() {
        return Math.PI * radius * (radius + slantHeight);
        // 3.14 * r * (r+l)
    }

}
