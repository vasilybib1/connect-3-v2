package cli;

import main.Main;
import model.Grid;

public class RenderCli{
  
  // pre set colors due to limit of rgb rendering in the terminal
  // colorScheme in main cannot have more than 6 colors to use the cli
  private static String[] colorScheme = {
    "\u001B[30m", // black
    "\u001B[31m", // red
    "\u001B[32m", // green 
    "\u001B[34m", // blue
    "\u001B[35m", // purple
    "\u001B[33m", // yellow
    "\u001B[36m"  // cyan
  };

  public static void renderGrid(Grid g){
    // makes the first row of the grid
    System.out.print(colorScheme[0]+"+");
    for(int i = 0; i < g.getWidth(); i++){
      if(i == g.getWidth()-1){ System.out.println(colorScheme[0]+"-"+i+"-+"); }
      else{ System.out.print(colorScheme[0]+"-"+i+"-+"); }
    }
    
    // uses for loop to construct the rest of the grid by every row
    for(int i = 0; i < g.getHeight(); i++){
      renderRow(g, i);
    }
  }

  private static void renderRow(Grid g, int rowNum){
    // renders the line with colors
    char rowLetter = (char) (rowNum+65);
    System.out.print(colorScheme[0]+rowLetter+" ");
    for(int i = 0; i < g.getWidth(); i++){
      int color = Main.getColorIdentifier(g.getGrid()[i][rowNum]);
      
      if(i == g.getWidth()-1){ System.out.println(colorScheme[color]+color+colorScheme[0]+" |"); }
      else{ System.out.print(colorScheme[color]+color+colorScheme[0]+" | "); }
    }
    
    // renders the bottom line of a grid
    System.out.print(colorScheme[0]+"+");
    for(int i = 0; i < g.getWidth(); i++){
      if(i == g.getWidth()-1){ System.out.println(colorScheme[0]+"---+"); }
      else{ System.out.print(colorScheme[0]+"---+"+"\u001B[37m"); }
    }
  }
}
