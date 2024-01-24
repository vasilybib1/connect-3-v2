package model;

public class Cell {
    /* CLASS LEVEL COMMENT
    cell class is responsible for storing the color data for its own cell
     */

    private double red;
    private double green;
    private double blue;

    // REQUIRE: a float value from 0.0 to 1.0 for all r, g and b input
    // MODIFIES: this
    // EFFECT: constructs a cell object with a color value
    public Cell(double r, double g, double b) {
        this.red = r;
        this.green = g;
        this.blue = b;
        Event e = new Event("New cell made with colors " + r + " " + g + " " + b);
        EventLog.getInstance().logEvent(e);
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    // debug method to show color value of the cell
    public String toString() {
        return red + " " + green + " " + blue;
    }
}