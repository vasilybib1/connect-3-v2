package persistence;

import model.Cell;
import model.Grid;
import org.junit.jupiter.api.Test;
import ui.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterReaderTest {

    @Test
    public void testWriterAndReaderColumns() {
        JsonWriter jw = new JsonWriter("./data/sample/testCol.json");
        int score = 10;
        Cell[][] cells = new Cell[3][3];
        cells = fillCellArrayColorColumns(cells);
        Grid grid = new Grid(cells, Main.COLOR_SCHEME);
        try {
            jw.open();
            jw.write(grid, score);
            jw.close();
        } catch (Exception e) {
            System.err.println(e);
            fail("souldnt have thrown exception when writing");
        }

        JsonReader jr = new JsonReader("./data/sample/testCol.json");
        try {
            assertEquals(10, jr.readScore());
        } catch (Exception e) {
            System.err.println(e);
            fail("shouldnt have thrown exception when reading the score");
        }

        try {
            Grid newGrid = jr.read();
            for(int i = 0; i < newGrid.getWidth(); i++) {
                for(int x = 0; x < newGrid.getHeight(); x++) {
                    assertEquals(Main.COLOR_SCHEME[i][0], newGrid.getGrid()[i][x].getRed());
                    assertEquals(Main.COLOR_SCHEME[i][1], newGrid.getGrid()[i][x].getGreen());
                    assertEquals(Main.COLOR_SCHEME[i][2], newGrid.getGrid()[i][x].getBlue());
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            fail("shouldnt have thrown exception when reading the grid");
        }
    }


    @Test
    public void fillCellArrayColorRows() {
        JsonWriter jw = new JsonWriter("./data/sample/testRows.json");
        int score = 10;
        Cell[][] cells = new Cell[3][3];
        cells = fillCellArrayColorRows(cells);
        Grid grid = new Grid(cells, Main.COLOR_SCHEME);
        try {
            jw.open();
            jw.write(grid, score);
            jw.close();
        } catch (Exception e) {
            System.err.println(e);
            fail("souldnt have thrown exception when writing");
        }

        JsonReader jr = new JsonReader("./data/sample/testRows.json");
        try {
            assertEquals(10, jr.readScore());
        } catch (Exception e) {
            System.err.println(e);
            fail("shouldnt have thrown exception when reading the score");
        }

        try {
            Grid newGrid = jr.read();
            for(int i = 0; i < newGrid.getWidth(); i++) {
                for(int x = 0; x < newGrid.getHeight(); x++) {
                    assertEquals(Main.COLOR_SCHEME[x][0], newGrid.getGrid()[i][x].getRed());
                    assertEquals(Main.COLOR_SCHEME[x][1], newGrid.getGrid()[i][x].getGreen());
                    assertEquals(Main.COLOR_SCHEME[x][2], newGrid.getGrid()[i][x].getBlue());
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            fail("shouldnt have thrown exception when reading the grid");
        }
    }

    // helper function
    public Cell[][] fillCellArrayColorColumns(Cell[][] grid){
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Cell(Main.COLOR_SCHEME[x][0], Main.COLOR_SCHEME[x][1], Main.COLOR_SCHEME[x][2]);
            }
        }
        return grid;
    }

    // helper function
    public Cell[][] fillCellArrayColorRows(Cell[][] grid){
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Cell(Main.COLOR_SCHEME[y][0], Main.COLOR_SCHEME[y][1], Main.COLOR_SCHEME[y][2]);
            }
        }
        return grid;
    }

}
