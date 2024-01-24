package model;

import org.json.JSONArray;
import org.json.JSONObject;

public class Grid {
    /* CLASS LEVEL COMMENT
    this class is responsible for creating and storing the current game state (grid)
    it creates a grid with given dimensions and generates new cells to fill out the grid
     */

    private int width;
    private int height;

    private Cell[][] grid;
    private double[][] colorScheme;

    // REQUIRES: if using a cli rendering method, colorscheme can not be larger than 6
    // MODIFIES: this
    // EFFECT: constructs a grid with given dimensions and populates it with cells with the given colorscheme
    public Grid(int width, int height, double[][] colorScheme) {
        this.width = width;
        this.height = height;
        this.colorScheme = colorScheme;
        fillGrid();
        Event e = new Event("New grid made from scratch");
        EventLog.getInstance().logEvent(e);
    }

    // REQUIRES: a defined cell array and a colorscheme
    // MODIFIES: this
    // EFFECT: initiates a grid with a given cell array
    //         this method is purely made to test the Logic class
    public Grid(Cell[][] grid, double[][] colorScheme) {
        this.width = grid.length;
        this.height = grid[0].length;
        this.colorScheme = colorScheme;
        this.grid = grid;
        Event e = new Event("New grid made from an array");
        EventLog.getInstance().logEvent(e);
    }

    // MODIFIES: this
    // EFFECT: fills in an empty grid with cells randomly colored
    private void fillGrid() {
        grid = new Cell[width][height];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = generateRandomCell();
            }
        }
    }

    // REQUIRE: for all row input (rowFrom, rowTo) to be between 0 < grid height
    //          and for all column inputs (colFrom, colTo) to be between 0 < grid width
    // MODIFIES: this
    // EFFECT: switches two cells positions in the grid
    public void moveCell(int rowFrom, int colFrom, int rowTo, int colTo) {
        Cell tempCell = grid[colTo][rowTo];
        grid[colTo][rowTo] = grid[colFrom][rowFrom];
        grid[colFrom][rowFrom] = tempCell;
    }

    // REQUIRE: row to be between 0 < grid height and col to be between 0 < grid width
    // EFFECT: generates a new cell randomly colored from the colorscheme at a given row and column index
    public void generateNewCell(int row, int col) {
        grid[col][row] = generateRandomCell();
    }

    // EFFECT: returns a new cell randomly colored from the colorscheme
    private Cell generateRandomCell() {
        int rand = (int) ((colorScheme.length) * Math.random());
        return new Cell(
                colorScheme[rand][0],
                colorScheme[rand][1],
                colorScheme[rand][2]);
    }

    // REQUIRE: the current score of the game
    // EFFECT: returns the jsonObject of the current grid state
    public JSONObject toJson(int score) {
        JSONObject jo = new JSONObject();
        JSONArray jaGrid = new JSONArray();
        for (Cell[] cellArr : grid) {
            JSONArray jaGridCol = new JSONArray();
            for (Cell c : cellArr) {
                JSONObject jc = new JSONObject();
                jc.put("red", c.getRed());
                jc.put("green", c.getGreen());
                jc.put("blue", c.getBlue());
                jaGridCol.put(jc);
            }
            jaGrid.put(jaGridCol);
        }
        jo.put("grid", jaGrid);
        jo.put("score", score);
        return jo;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
