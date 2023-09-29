package model;

import main.Main;

public class Logic{

  // debugging
  public static String checkGrid(Grid g){
    return checkRows(g)+" "+rowMatch(g)+"\n"+checkColumns(g)+" "+columnMatch(g);
  }

  // checks if any of the rows have a match
  private static boolean checkRows(Grid g){
    for(int row = 0; row < g.getHeight(); row++){
      int streak = 0;
      for(int col = 1; col < g.getWidth(); col++){
        int num = Main.getColorIdentifier(g.getGrid()[col][row]);
        int prevNum = Main.getColorIdentifier(g.getGrid()[col-1][row]);
        if(num == prevNum){
          streak++;
        }else if(num != prevNum){
          streak = 0;
        }
        
        if(streak == 2){
          return true;
        }
      }
    }
    return false;
  }

  // checks if any of the columns has a match
  private static boolean checkColumns(Grid g){
    for(int col = 0; col < g.getHeight(); col++){
      int streak = 0;
      for(int row = 1; row < g.getWidth(); row++){
        int num = Main.getColorIdentifier(g.getGrid()[col][row]);
        int prevNum = Main.getColorIdentifier(g.getGrid()[col][row-1]);
        if(num == prevNum){
          streak++;
        }else{
          streak = 0;
        }
        
        if(streak == 2){
          return true;
        }
      }
    }
    return false;
  }

  // returns a match if one is found 
  // the column id followed by the row it was found in
  // empty string returned if nothing is found
  // (searches from bottom to top)
  public static String rowMatch(Grid g){
    String rString = "";
    int rowMatch = -1;
    for(int row = g.getHeight()-1; row >= 0; row--){
      int streak = 0;
      for(int col = 1; col < g.getWidth(); col++){
        int num = Main.getColorIdentifier(g.getGrid()[col][row]);
        int prevNum = Main.getColorIdentifier(g.getGrid()[col-1][row]);
        if(num == prevNum){
          streak++;
          rowMatch = row;
          rString = rString+""+col;
          if(col == g.getWidth()-1 && streak >= 2){
            return (Integer.valueOf(rString.substring(0,1))-1)+rString+" "+rowMatch;
          }
        }else if(num != prevNum){
          if(streak >= 2){
            return (Integer.valueOf(rString.substring(0,1))-1)+rString+" "+rowMatch;
          }else{
            rString = "";
            streak = 0;
            rowMatch = -1;
          }
        }
      }
      rString = "";
      rowMatch = -1;
      streak = 0;
    }
    return "";
  }
  
  // returns a match if one is found 
  // the row id followed by the column it was found in
  // empty string returned if nothing is found
  // (searches from left to right)
  public static String columnMatch(Grid g){
    String rString = "";
    int columnMatch = -1;
    for(int col = 0; col < g.getWidth(); col++){
      int streak = 0;
      for(int row = 1; row < g.getWidth(); row++){
        int num = Main.getColorIdentifier(g.getGrid()[col][row]);
        int prevNum = Main.getColorIdentifier(g.getGrid()[col][row-1]);
        if(num == prevNum){
          streak++;
          columnMatch = col;
          rString = rString+""+row;
          if(row == g.getWidth()-1 && streak >= 2){
            return (Integer.valueOf(rString.substring(0,1))-1)+rString+" "+columnMatch;
          }
        }else if(num != prevNum){
          if(streak >= 2){
            return (Integer.valueOf(rString.substring(0,1))-1)+rString+" "+columnMatch;
          }else{
            rString = "";
            streak = 0;
            columnMatch = -1;
          }
        }
      }
      rString = "";
      columnMatch = -1;
      streak = 0;
    }
    return "";
  }

  // TODO: implement cleaning the board until there are no matches
  // (for new games)
}
