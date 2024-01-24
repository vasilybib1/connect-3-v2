package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    public static final double[][] COLOR_SCHEME = {
            {1.0,0.0,0.0}, // red
            {0.0,1.0,0.0}, // green
            {0.0,0.0,1.0}, // blue
            {1.0,1.0,0.0}  // purple
    };

    @Test
    public void testDiffSizesForGrid(){
        for (int i = 0; i < 10; i++) {
            int randWidth = (int) (100 * Math.random());
            int randHeight = (int) (100 * Math.random());
            Grid g = new Grid(randWidth+1, randHeight+1, COLOR_SCHEME);
            assertEquals(randWidth+1, g.getGrid().length);
            assertEquals(randHeight+1, g.getGrid()[0].length);
        }
    }

    @Test
    public void testMakeGrid(){
        Cell[][] cellArr = new Cell[2][2];
        for (int i = 0; i < cellArr.length; i++) {
            for (int x = 0; x < cellArr[i].length; x++) {
                cellArr[i][x] = new Cell(0.0, 0.0, 0.0);
            }
        }
        Grid g = new Grid(cellArr, COLOR_SCHEME);
        assertEquals(cellArr, g.getGrid());
        assertEquals(g.getHeight(), 2);
        assertEquals(g.getWidth(), 2);
    }

    @Test
    public void testMoveCell(){
        for (int i = 0; i < 10; i++) {
            Grid g = new Grid(2, 2, COLOR_SCHEME);
            Cell c1 = g.getGrid()[0][0];
            Cell cellFrom = new Cell(c1.getRed(),c1.getGreen(),c1.getBlue());
            Cell c2 = g.getGrid()[1][1];
            Cell cellTo = new Cell(c2.getRed(),c2.getGreen(),c2.getBlue());
            g.moveCell(0,0, 1, 1);
            assertTrue(cellsEqual(g.getGrid()[0][0], cellTo));
            assertTrue(cellsEqual(g.getGrid()[1][1], cellFrom));
        }
    }

    public boolean cellsEqual(Cell c1, Cell c2){
        return c1.getRed() == c2.getRed()
                && c1.getBlue() == c2.getBlue()
                && c1.getGreen() == c2.getGreen();
    }

    @Test
    public void testGenerateNewCell(){
        for (int i = 0; i < 10; i ++) {
            Grid g = new Grid(2, 2, COLOR_SCHEME);
            Cell cellOriginal = g.getGrid()[0][0];
            g.generateNewCell(0, 0);
            assertNotEquals(cellOriginal, g.getGrid()[0][0]);
        }
    }

    @Test
    public void testGetters(){
        Grid g = new Grid(1, 1, COLOR_SCHEME);
        assertEquals(g.getWidth(), 1);
        assertEquals(g.getHeight(), 1);
    }
}