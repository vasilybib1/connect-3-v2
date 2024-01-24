package ui.gui;

import model.Cell;
import model.Grid;
import ui.Main;
import ui.gui.components.DrawingFrame;
import ui.gui.components.MouseControl;
import ui.gui.components.ObjectRectangle;
import ui.gui.components.SelectedCell;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RenderGui {
    /* CLASS LEVEL COMMENT
    this is the main gui class that controls the canvas, panel, frame
    almost all the render related code goes through this
    */

    private int width;
    private int height;

    private DrawingFrame canvas;
    private JFrame frame;
    private JPanel panel;

    private Grid grid;

    // constructor
    // REQUIRES: a title, width and height of the window and a valid grid
    // EFFECT: makes the jframe, jpanel, canvas and adds mouse control to the canvas
    public RenderGui(String title, int w, int h, Grid g) {
        this.width = w;
        this.height = h;
        this.grid = g;

        this.frame = new JFrame(title);
        //this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.close();
            }
        });

        this.canvas = new DrawingFrame();
        this.canvas.setSize(w, h);
        this.canvas.addMouseListener(new MouseControl());

        this.panel = new JPanel();
        this.panel.add(this.canvas);

        this.frame.add(this.panel);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    // REQUIRES: a valid grid
    // EFFECT: makes an array of rectangles from the grid, that are then passed into the canvas and are rendered
    public void renderGrid(Grid g) {
        ObjectRectangle[][] rectArray = new ObjectRectangle[g.getGrid().length][g.getGrid()[0].length];
        Cell[][] cellArray = g.getGrid();
        int widthEach = (width - 20) / cellArray.length;
        int heightEach = (height - 20) / cellArray[0].length;
        for (int i = 0; i < cellArray.length; i++) {
            for (int j = 0; j < cellArray[i].length; j++) {
                rectArray[i][j] = new ObjectRectangle(10 + (widthEach * i), 10 + (heightEach * j),
                        widthEach, heightEach,
                        cellArray[i][j].getRed(), cellArray[i][j].getGreen(), cellArray[i][j].getBlue());
            }
        }
        canvas.setRectArray(rectArray);
    }

    // EFFECT: sets the title
    public void setTitle(String title) {
        this.frame.setTitle(title);
    }

    // EFFECT: repaints (updates) the canvas
    public void repaint() {
        this.canvas.repaint();
    }

    // REQUIRES: x and y coordinates of the mouse
    // EFFECT: uses the mouse coordinates and creates a selected cell object that is then passed to the canvas
    //  and are then rendered
    public void makeSelectedCell(int x, int y) {
        Cell[][] cellArray = grid.getGrid();
        int widthEach = (width - 20) / cellArray.length;
        int heightEach = (height - 20) / cellArray[0].length;
        int cellX = x / widthEach;
        int cellY = y / heightEach;
        this.canvas.setSelCell(new SelectedCell(10 + (widthEach * cellX), 10 + (heightEach * cellY),
                widthEach, heightEach,0.0, 0.0, 0.0));
    }

    // REQUIRES: x coordinate of the mouse
    // EFFECT: returns the cell coordinate corresponding to the current mouse x
    public int convertMouseXToCellX(int x) {
        Cell[][] cellArray = grid.getGrid();
        int widthEach = (width - 20) / cellArray.length;
        int cellX = x / widthEach;
        return cellX;
    }

    // REQUIRES: y coordinate of the mouse
    // EFFECT: returns the cell coordinate corresponding to the current mouse y
    public int convertMouseYToCellY(int y) {
        Cell[][] cellArray = grid.getGrid();
        int heightEach = (height - 20) / cellArray[0].length;
        int cellY = y / heightEach;
        return cellY;
    }

    // EFFECT: sets the selected cell to null which hides the selected outline
    public void hideSelection() {
        this.canvas.setSelCell(null);
    }



}
