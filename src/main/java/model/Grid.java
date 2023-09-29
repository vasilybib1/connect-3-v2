package model;

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
