package cli;

import model.Grid;

public class RenderCli{

  private static String[] colorScheme = {
    "\u001B[30m", // black
    "\u001B[31m", // red
    "\u001B[32m", // green 
    "\u001B[34m"  // blue
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
      int color = 0;
      if(g.getGrid()[i][rowNum].toString().equals("1.0 0.0 0.0")){color = 1;}
      if(g.getGrid()[i][rowNum].toString().equals("0.0 1.0 0.0")){color = 2;}
      if(g.getGrid()[i][rowNum].toString().equals("0.0 0.0 1.0")){color = 3;}
      
      if(i == g.getWidth()-1){ System.out.println(colorScheme[color]+color+colorScheme[0]+" |"); }
      else{ System.out.print(colorScheme[color]+color+colorScheme[0]+" | "); }
    }
    
    // renders the bottom line of a grid
    System.out.print(colorScheme[0]+"+");
    for(int i = 0; i < g.getWidth(); i++){
      if(i == g.getWidth()-1){ System.out.println(colorScheme[0]+"---+"); }
      else{ System.out.print(colorScheme[0]+"---+"); }
    }
  }
}
