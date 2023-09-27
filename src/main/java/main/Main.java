package main;

import cli.RenderCli;
import model.Cell;
import model.Grid;

public class Main {
  public static void main(String[] args) {
    
    Grid g = new Grid(5, 5);
    RenderCli.renderGrid(g);
  }
}
