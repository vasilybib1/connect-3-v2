package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Grid{
  private int width;
  private int height;

  private Cell[][] grid;
  private double[][] colorScheme;

  public Grid(int width, int height, double[][] colorScheme){
    this.width = width;
    this.height = height;
    this.colorScheme = colorScheme;
    fillGrid();
  }

  public Grid(Cell[][] grid, double[][] colorScheme) {
    this.width = grid.length;
    this.height = grid[0].length;
    this.colorScheme = colorScheme;
    this.grid = grid;
  }

  private void fillGrid(){
    grid = new Cell[width][height];
    for(int x = 0; x < grid.length; x++){
      for(int y = 0; y < grid[x].length; y++){
        grid[x][y] = generateRandomCell();
      }
    }
  }

  public void moveCell(int rowFrom, int colFrom, int rowTo, int colTo){
    Cell tempCell = grid[colTo][rowTo];
    grid[colTo][rowTo] = grid[colFrom][rowFrom];
    grid[colFrom][rowFrom] = tempCell;
  }

  public void generateNewCell(int row, int col){
    grid[col][row] = generateRandomCell();
  }

  private Cell generateRandomCell(){
    int rand = (int) ((colorScheme.length) * Math.random());
    return new Cell(
        colorScheme[rand][0],
        colorScheme[rand][1],
        colorScheme[rand][2]);
  }

  public JSONObject toJson(int score) throws JSONException{
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

  public Cell[][] getGrid(){
    return grid;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
  }

}
