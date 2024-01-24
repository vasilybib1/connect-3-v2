package ui.gui.components;

import java.awt.*;

public class SelectedCell {

    private int width;
    private int height;
    private int positionX;
    private int positionY;
    private Color color;

    public SelectedCell(int x, int y, int w, int h, double r, double g, double b) {
        this.positionX = x;
        this.positionY = y;
        this.width = w;
        this.height = h;
        color = new Color((float) r, (float) g, (float) b);
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
    }
}

