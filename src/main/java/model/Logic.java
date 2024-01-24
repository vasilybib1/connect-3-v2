package model;

import main.Main;

public class Logic{

  private static final boolean DEBUG = false;

  // returns true if a match is found 
  // if no match is found return false
  public static boolean checkGrid(Grid g){
    return checkRows(g) || checkColumns(g);
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
  private static String rowMatch(Grid g){
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
  private static String columnMatch(Grid g){
    String rString = "";
    int columnMatch = -1;
    for(int col = 0; col < g.getWidth(); col++){
      int streak = 0;
      for(int row = 1; row < g.getHeight(); row++){
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
  
  // keeps cleaning the board until no more matches are on it 
  public static void removeMatches(Grid g){
    while(checkGrid(g)){
      String rowMatchCode = rowMatch(g);
      String columnMatchCode = columnMatch(g);
      if(!rowMatchCode.equals("")){
        removeRowMatch(g, rowMatchCode);
      }
      if(!columnMatchCode.equals("")){
        removeColumnMatch(g, columnMatchCode);
      }
    }
  }
  
  // removes the match and shifts the rest down and generates new cells at the top
  private static void removeRowMatch(Grid g, String code){
    if(DEBUG){System.out.println("row match:"+code);}
    String toRemove = code.split(" ")[0];
    int row = Integer.valueOf(code.split(" ")[1]);
    
    for(int i = 0; i < toRemove.length(); i++){
      int cellToRemove = Integer.valueOf(toRemove.substring(i,i+1));
      for(int j = 1; j <= row; j++){
        g.moveCell(row-j, cellToRemove, row-j+1, cellToRemove);
      }
      g.generateNewCell(0, cellToRemove);
    }
  }

  // removes the match and shifts the rest down and generates new cells at the top
  private static void removeColumnMatch(Grid g, String code){
    if(DEBUG){System.out.println("col match:"+code);}
    String toRemove = code.split(" ")[0];
    int col = Integer.valueOf(code.split(" ")[1]);
    
    for(int i = 0; i < toRemove.length(); i++){
      int cellToRemove = Integer.valueOf(toRemove.substring(i, i+1));
      for(int j = 1; j <= cellToRemove; j++){
        g.moveCell(cellToRemove-j, col, cellToRemove-j+1, col);
      }
      g.generateNewCell(0, col);
    }
  }

  public static boolean checkMove(int rowFrom, int colFrom, int rowTo, int colTo){
    if (rowFrom == rowTo) {
      return colFrom == colTo + 1 || colFrom == colTo - 1;
    } else if (colFrom == colTo) {
      return rowFrom == rowTo + 1 || rowFrom == rowTo - 1;
    } else {
      return false;
    }
  }

  public static int removeOneMatch(Grid g){
    String rowMatchCode = rowMatch(g);
    if (rowMatchCode != "") {
      removeRowMatch(g, rowMatchCode);
      return rowMatchCode.split(" ")[0].length();
    } else {
      String columnMatchCode = columnMatch(g);
      removeColumnMatch(g, columnMatchCode);
      return columnMatchCode.split(" ")[0].length();
    }
  }


}
