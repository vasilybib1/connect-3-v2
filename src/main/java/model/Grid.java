package model;

public class Grid{
  private int width;
  private int height;

  private Cell[][] grid;
  //private double[][] colorScheme;
  
  private double[][] colorScheme = {
    {1.0,0.0,0.0},
    {0.0,1.0,0.0},
    {0.0,0.0,1.0}
  };

  public Grid(int width, int height){
    this.width = width;
    this.height = height;
    fillGrid();
  }

  private void fillGrid(){
    grid = new Cell[width][height];
    
    for(int x = 0; x < grid.length; x++){
      for(int y = 0; y < grid[x].length; y++){
        int rand = (int) ((colorScheme.length) * Math.random());
        grid[x][y] = new Cell(
            colorScheme[rand][0],
            colorScheme[rand][1],
            colorScheme[rand][2]);
      }
    }
    
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
