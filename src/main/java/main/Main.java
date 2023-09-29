package main;

import java.util.Scanner;

import cli.RenderCli;
import model.Cell;
import model.Grid;
import model.Logic;

public class Main {

  private static String white =  "\u001B[37m";
  public static double[][] colorScheme = {
    {1.0,0.0,0.0}, // red 
    {0.0,1.0,0.0}, // green
    {0.0,0.0,1.0}, // blue
    {1.0,1.0,0.0}  // purple
  };

  public static void main(String[] args) {
    Scanner scanner;
    while(true){
      Grid g = new Grid(5, 5, colorScheme);
      RenderCli.renderGrid(g);
      System.out.println(Logic.checkGrid(g));
      scanner = new Scanner(System.in);
      System.out.println(white+"Enter a cell to move `A1` or `q` to quit");
      String input = scanner.nextLine();
      if(input.equals("q")){ break; }
      else{
        // TODO: what to do with the cell selection
      }
    }
    scanner.close();
  }

  public static int getColorIdentifier(Cell c){
    for(int i = 0; i < colorScheme.length; i++){
      if(c.getRed() == colorScheme[i][0] && 
          c.getGreen() == colorScheme[i][1] && 
          c.getBlue() == colorScheme[i][2]){
        return i+1;
      }
    }
    return -1;
  }

}
