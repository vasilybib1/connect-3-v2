package main;

import java.util.Scanner;

import ui.cli.RenderCli;
import ui.opengl.Render;
import model.Cell;
import model.Grid;
import model.Logic;
import persistence.JsonReader;
import persistence.JsonWriter;

public class Main {

  private static final String WHITE =  "\u001B[37m";
  private static final String RED =  "\u001B[31m";
  private static final int BOARD_SIZE = 5;
  public static final double[][] COLOR_SCHEME = {
    {1.0,0.0,0.0}, // red 
    {0.0,1.0,0.0}, // green
    {0.0,0.0,1.0}, // blue
    {1.0,1.0,0.0}  // purple
  };
  private static boolean loadedFromSave = false;

  public static Grid askToLoadOrNew() {
    System.out.println(WHITE + "Do you wish to load the old save (`Y` for yes | anything else for no)");
    try {
      JsonReader jr = new JsonReader("./data/save.json");
      System.out.println(WHITE + "Saved score: " + jr.readScore());
    } catch (Exception e) {
      System.err.println(e);
    }
    Scanner s = new Scanner(System.in);
    String ans = s.nextLine();
    Grid g;
    if (ans.equals("Y")) {
      loadedFromSave = true;
      JsonReader jr = new JsonReader("./data/save.json");
      try {
        g = jr.read();
      } catch (Exception e) {
        System.err.println(e);
        g = new Grid(BOARD_SIZE, BOARD_SIZE, COLOR_SCHEME);
        Logic.removeMatches(g);
      }
    } else {
      g = new Grid(BOARD_SIZE, BOARD_SIZE, COLOR_SCHEME);
      Logic.removeMatches(g);
    }
    return g;
  }

  public static void askToSave(Grid g, int score) {
    System.out.println(WHITE + "Do you wish to save the current game (last save will be overwritten\n"
      + "(`Y` for yes | anything else for no)");
    Scanner s = new Scanner(System.in);
    String ans = s.nextLine();
    if (ans.equals("Y")) {
      JsonWriter jw = new JsonWriter("./data/save.json");
      try {
        jw.open();
        jw.write(g, score);
        jw.close();
      } catch (Exception e) {
        System.err.println(e);
      }
    }
  }

  public static void main(String[] args) {
    Grid g = askToLoadOrNew();
    int score;
    if (loadedFromSave) {
      try {
        JsonReader jr = new JsonReader("./data/save.json");
        score = jr.readScore();
      } catch (Exception e) {
        System.err.println(e);
        score = 0;
      }
    } else {
      score = 0;
    }
    System.out.println(WHITE + "To play enter a cell to move `A1` and its location `A0` or `q` to quit");
    while (true) { // main game loop
      Scanner scannerUserInput;
      RenderCli.renderGrid(g);
      System.out.println(WHITE + "Score: " + score);
      scannerUserInput = new Scanner(System.in);
      String input = scannerUserInput.nextLine();
      try {
        score = processInput(input, g, score);
      } catch (Exception e) {
        askToSave(g, score);
        scannerUserInput.close();
        break;
      }
    }
  }

  private static int processInput(String input, Grid g, int score) throws Exception {
    if (input.equals("q")) {
      throw new Exception();
    } else if (input.contains(" ")) {
      int[] from = getCoords(input.split(" ")[0]);
      int[] to = getCoords(input.split(" ")[1]);
      if (from[1] >= 0 && to[1] >= 0
            && from[1] < g.getWidth() && to[1] < g.getWidth()
            && from[0] >= 0 && to[0] >= 0
            && from[0] < g.getHeight() && to[0] < g.getHeight()) {
        score = processMovedCell(g, from, to, score);
      } else {
        System.out.println(RED + "INVALID" + WHITE + " - coordinate out of range");
      }
    } else {
      System.out.println(RED + "INVALID" + WHITE + " - not a coordinate");
    }
    return score;
  }

  private static int processMovedCell(Grid g, int[] from, int[] to, int score) {
    if (Logic.checkMove(from[0], from[1], to[0], to[1])) {
      g.moveCell(from[0], from[1], to[0], to[1]);
      if (Logic.checkGrid(g)) {
        while (Logic.checkGrid(g)) {
          score += Logic.removeOneMatch(g);
          if (!Logic.checkGrid(g)) {
            break;
          }
          Scanner s = new Scanner(System.in);
          RenderCli.renderGrid(g);
          System.out.println(WHITE + "Score: " + score);
          System.out.println(WHITE + "press enter key");
          s.nextLine();
        }
      } else {
        g.moveCell(from[0], from[1], to[0], to[1]);
        System.out.println(RED + "INVALID" + WHITE + " - No match");
      }
    } else {
      System.out.println(RED + "INVALID" + WHITE + " - Not adjacent cell");
    }
    return score;
  }

  public static int getColorIdentifier(Cell c) {
    for (int i = 0; i < COLOR_SCHEME.length; i++) {
      if (c.getRed() == COLOR_SCHEME[i][0]
          && c.getGreen() == COLOR_SCHEME[i][1]
          && c.getBlue() == COLOR_SCHEME[i][2]) {
        return i + 1;
      }
    }
    return -1;
  }

  private static int[] getCoords(String code) {
    int[] returnCoords = new int[2];
    int row = code.substring(0,1).getBytes()[0] - 65;
    int col = Integer.valueOf(code.substring(1,2));
    returnCoords[0] = row;
    returnCoords[1] = col;
    return returnCoords;
  }
}
