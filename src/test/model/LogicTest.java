package model;

import org.junit.jupiter.api.Test;
import ui.cli.RenderCli;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {
    public static final double[][] COLOR_SCHEME = {
            {1.0,0.0,0.0}, // red
            {0.0,1.0,0.0}, // green
            {0.0,0.0,1.0}, // blue
            {1.0,1.0,0.0}  // purple
    };

    @Test
    public void testCheckGridOneRowMatch() {
        Cell[][] cellArray1 = new Cell[3][3];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[0][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[2][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        assertTrue(Logic.checkGrid(g1));
    }

    @Test
    public void testCheckGridOneColumnMatch() {
        Cell[][] cellArray1 = new Cell[3][3];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[0][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        assertTrue(Logic.checkGrid(g1));
    }

    @Test
    public void testCheckGridNoMatches() {
        Cell[][] cellArray1 = new Cell[3][3];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[0][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[2][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        assertFalse(Logic.checkGrid(g1));
    }

    @Test
    public void testRemoveMatchesOneRow(){
        Cell[][] cellArray1 = new Cell[3][3];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[0][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[2][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        Logic.removeMatches(g1);
        assertFalse(Logic.checkGrid(g1));
    }

    @Test
    public void testRemoveMatchesOneColumn(){
        Cell[][] cellArray1 = new Cell[3][3];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[0][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        Logic.removeMatches(g1);
        assertFalse(Logic.checkGrid(g1));
    }

    @Test
    public void testCheckGridRandomLarge(){
        for (int i = 10; i < 10; i++) {
            Grid g = new Grid(5,5, COLOR_SCHEME);
            Logic.removeMatches(g);
            assertFalse(Logic.checkGrid(g));
        }
    }

    @Test
    public void testRowMatchEndStreak(){
        Cell[][] cellArray1 = new Cell[4][4];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[3][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);

        cellArray1[1][3] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[0][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[3][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[2][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[3][3] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        Logic.removeMatches(g1);
        assertFalse(Logic.checkGrid(g1));
    }

    @Test
    public void testColumnMatchEndStreak(){
        Cell[][] cellArray1 = new Cell[4][4];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[2][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);

        cellArray1[1][3] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[0][3] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[3][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[2][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[3][3] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        Logic.removeMatches(g1);
        assertFalse(Logic.checkGrid(g1));
    }

    @Test
    public void testNewCellsGeneratedAfterRemoval(){
        Cell[][] cellArray1 = new Cell[3][3];
        cellArray1 = fillCellArray(cellArray1);
        cellArray1[0][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[1][2] = new Cell(COLOR_SCHEME[2][0], COLOR_SCHEME[2][1], COLOR_SCHEME[2][2]);
        cellArray1[2][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[0][0] = new Cell(COLOR_SCHEME[2][0], COLOR_SCHEME[2][1], COLOR_SCHEME[2][2]);
        cellArray1[1][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray1[2][0] = new Cell(COLOR_SCHEME[2][0], COLOR_SCHEME[2][1], COLOR_SCHEME[2][2]);
        Grid g1 = new Grid(cellArray1, COLOR_SCHEME);
        Logic.removeMatches(g1);
        assertFalse(Logic.checkGrid(g1));

        Cell[][] cellArray2 = new Cell[3][3];
        cellArray2 = fillCellArray(cellArray2);
        cellArray2[0][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray2[1][2] = new Cell(COLOR_SCHEME[2][0], COLOR_SCHEME[2][1], COLOR_SCHEME[2][2]);
        cellArray2[2][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray2[0][0] = new Cell(COLOR_SCHEME[2][0], COLOR_SCHEME[2][1], COLOR_SCHEME[2][2]);
        cellArray2[1][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray2[2][0] = new Cell(COLOR_SCHEME[2][0], COLOR_SCHEME[2][1], COLOR_SCHEME[2][2]);

        Cell c0 = g1.getGrid()[0][1];
        assertTrue(cellsEqual(c0, cellArray2[0][0]));
        Cell c1 = g1.getGrid()[1][1];
        assertTrue(cellsEqual(c1, cellArray2[1][0]));
        Cell c2 = g1.getGrid()[2][1];
        assertTrue(cellsEqual(c2, cellArray2[2][0]));
    }

    @Test
    public void testCheckMove(){
        assertTrue(Logic.checkMove(1,1,1,2));
        assertTrue(Logic.checkMove(1,1,1,0));
        assertTrue(Logic.checkMove(1,1,2,1));
        assertTrue(Logic.checkMove(1,1,0,1));

        assertFalse(Logic.checkMove(1,1,1,3));
        assertFalse(Logic.checkMove(1,1,1,-1));
        assertFalse(Logic.checkMove(1,1,3,1));
        assertFalse(Logic.checkMove(1,1,-1,1));

        assertFalse(Logic.checkMove(1,1,2,2));
    }

    @Test
    public void testRemoveOneRowMatch() {
        Cell[][] cellArray = new Cell[3][3];
        cellArray = fillCellArray(cellArray);
        Grid g = new Grid(cellArray, COLOR_SCHEME);
        assertEquals(Logic.removeOneMatch(g), 3);
    }

    @Test
    public void testRemoveOneColumnMatch() {
        Cell[][] cellArray = new Cell[3][3];
        cellArray = fillCellArray(cellArray);
        cellArray[1][0] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray[1][2] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        cellArray[2][1] = new Cell(COLOR_SCHEME[1][0], COLOR_SCHEME[1][1], COLOR_SCHEME[1][2]);
        Grid g = new Grid(cellArray, COLOR_SCHEME);
        RenderCli.renderGrid(g);
        assertEquals(Logic.removeOneMatch(g), 3);
    }

    // helper function
    public Cell[][] fillCellArray(Cell[][] grid){
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = new Cell(COLOR_SCHEME[0][0], COLOR_SCHEME[0][1], COLOR_SCHEME[0][2]);
            }
        }
        return grid;
    }

    // helper function
    public boolean cellsEqual(Cell c1, Cell c2){
        return c1.getRed() == c2.getRed()
                && c1.getBlue() == c2.getBlue()
                && c1.getGreen() == c2.getGreen();
    }

    @Test
    public void testLogicClass() {
        Logic l = new Logic();
        assertTrue(l.getCreated());
    }

}
