package ui;

import model.Event;
import model.EventLog;
import model.Grid;
import model.Cell;
import model.Logic;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.cli.RenderCli;
import ui.gui.RenderGui;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    /* CLASS LEVEL COMMENT
    this is the main class where everything is initiated and all the permanent variables are set
    such as color scheme for the cells and board size and so on
     */

    // what mode to run the game in (false for cli mode)
    public static final boolean GUIMODE = true;

    // just values that you can tweak
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    // this variable is temporary since im printing to the console with different colors
    // i need a value that sets the text back to white
    private static final String WHITE =  "\u001B[37m";
    private static final String RED =  "\u001B[31m";

    // this is the rgb color scheme array that takes in 0.0f to 1.0f for red green and blue
    // however colors do not correspond with the console version (due to color printing in some consoles)
    // this is for the ui part of the game later on
    public static final double[][] COLOR_SCHEME = {
            {1.0,0.0,0.0}, // red
            {0.0,1.0,0.0}, // green
            {0.0,0.0,1.0}, // blue
            {1.0,0.0,1.0}  // yellow
    };

    // self-explanatory just the size of the board
    private static final int BOARD_SIZE = 5;

    // to make sure the score is in sync with the current loaded state
    private static boolean loadedFromSave = false;

    // some global variable for the game
    private static RenderGui rg;
    private static int selectedX = -1;
    private static int selectedY = -1;
    private static int firstSelectionX;
    private static int firstSelectionY;
    private static int selection = 0;
    private static int score = 0;
    private static Grid g;
    private static boolean close = false;

    // main class that runs everything
    // EFFECT: runs the game :)
    @SuppressWarnings("methodlength")
    public static void main(String[] args) {
        if (GUIMODE) {
            ImageIcon img = new ImageIcon("./data/image/question.png");
            int reply = 100000; // dont ask
            try {
                JsonReader jr = new JsonReader("./data/save.json");
                reply = JOptionPane.showConfirmDialog(null, "Load? (Score: " + jr.readScore() + ")",
                        "option", JOptionPane.YES_NO_OPTION, 0, img);
            } catch (Exception e) {
                System.err.println(e);
            }

            if (reply == JOptionPane.YES_OPTION) { // should load
                g = loadFromFile();
                rg = new RenderGui("Connect 3", WIDTH, HEIGHT, g);
                try {
                    JsonReader jr = new JsonReader("./data/save.json");
                    score = jr.readScore();
                } catch (Exception e) {
                    System.err.println(e);
                }
                rg.renderGrid(g);
            } else { // shouldnt load
                g = new Grid(BOARD_SIZE, BOARD_SIZE, COLOR_SCHEME);
                rg = new RenderGui("Connect 3", WIDTH, HEIGHT, g);
                Logic.removeMatches(g);
                rg.renderGrid(g);
            }

            // once the grid has been decided - game loop
            while (true) {
                rg.setTitle("Connect 3  Score: " + score);
                rg.renderGrid(g);
                rg.repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (Exception e) {
                    System.err.println(e);
                }
                if (close) {
                    break;
                }
            }

            // saving after closing
            reply = 100000; // dont ask
            reply = JOptionPane.showConfirmDialog(null, "Save?",
                    "option", JOptionPane.YES_NO_OPTION, 0, img);
            if (reply == JOptionPane.YES_OPTION) {
                saveToFile();
            }
        // THE CLI MODE BELOW
        } else {
            g = askToLoadOrNew();
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
                    processInput(input);
                } catch (Exception e) {
                    askToSave(g, score);
                    scannerUserInput.close();
                    break;
                }
            }
        }

        for (Event e : EventLog.getInstance()) {
            System.out.print(e);
        }
        System.exit(0);
    }

    //=[GUI]============================================================================================================

    // EFFECT: loads the grid from the save file and returns it
    private static Grid loadFromFile() {
        try {
            JsonReader jr = new JsonReader("./data/save.json");
            g = jr.read();
        } catch (Exception e) {
            System.err.println(e);
            g = new Grid(BOARD_SIZE, BOARD_SIZE, COLOR_SCHEME);
            Logic.removeMatches(g);
        }
        return g;
    }

    // EFFECT: saves the current score and grid to the save file
    private static void saveToFile() {
        JsonWriter jw = new JsonWriter("./data/save.json");
        try {
            jw.open();
            jw.write(g, score);
            jw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    //=[CLI]============================================================================================================

    // EFFECT: asks the user if he wants to load an old save or no
    @SuppressWarnings("methodlength")
    private static Grid askToLoadOrNew() {
        System.out.println(WHITE + "Do you wish to load the old save (`Y` for yes | anything else for no)");
        try {
            JsonReader jr = new JsonReader("./data/save.json");
            System.out.println(WHITE + "Saved score: " + jr.readScore());
        } catch (Exception e) {
            System.err.println(e);
        }
        Scanner s = new Scanner(System.in);
        String ans = s.nextLine();
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

    // REQUIRE: some game state (initialized grid and score of some value
    // EFFECT: if user answers yes then quit the game and write the current state to the file
    private static void askToSave(Grid g, int score) {
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

    //=[OTHER]==========================================================================================================

    // REQUIRE: an input from the user, grid, and the current score
    // MODIFIES: grid, score
    // EFFECT: processes the given input and handles any of the error handling (invalid inputs)
    public static void processInput(String input) throws Exception {
        if (input.equals("q")) {
            throw new Exception();
        } else if (input.contains(" ")) {
            int[] from = getCoords(input.split(" ")[0]);
            int[] to = getCoords(input.split(" ")[1]);
            if (from[1] >= 0 && to[1] >= 0
                    && from[1] < g.getWidth() && to[1] < g.getWidth()
                    && from[0] >= 0 && to[0] >= 0
                    && from[0] < g.getHeight() && to[0] < g.getHeight()) {
                processMovedCell(from, to);
            } else {
                System.out.println(RED + "INVALID" + WHITE + " - coordinate out of range");
            }
        } else {
            System.out.println(RED + "INVALID" + WHITE + " - not a coordinate");
        }
    }

    // REQUIRE: grid, processed input (coordinates for the from and to cell in an int array) and score
    // MODIFIES: grid, score
    // EFFECT: if there are multiple matches it will go through them step by step making sure the user sees all the
    //         matches and handles invalid moves
    private static void processMovedCell(int[] from, int[] to) {
        if (Logic.checkMove(from[0], from[1], to[0], to[1])) {
            g.moveCell(from[0], from[1], to[0], to[1]);
            if (Logic.checkGrid(g)) {
                while (Logic.checkGrid(g)) {
                    score += Logic.removeOneMatch(g);
                    if (!Logic.checkGrid(g)) {
                        break;
                    }
                    if (!GUIMODE) {
                        Scanner s = new Scanner(System.in);
                        RenderCli.renderGrid(g);
                        System.out.println(WHITE + "Score: " + score);
                        System.out.println(WHITE + "press enter key");
                        s.nextLine();
                    }
                }
            } else {
                g.moveCell(from[0], from[1], to[0], to[1]);
                System.out.println(RED + "INVALID" + WHITE + " - No match");
            }
        } else {
            System.out.println(RED + "INVALID" + WHITE + " - Not adjacent cell");
        }
    }

    // REQUIRE: a correct location code (A0, B2, C2)
    // EFFECT: returns the an int array with index 0 being row number and index 1 being column number
    private static int[] getCoords(String code) {
        int[] returnCoords = new int[2];
        int row = code.substring(0,1).getBytes()[0] - 65;
        int col = Integer.valueOf(code.substring(1,2));
        returnCoords[0] = row;
        returnCoords[1] = col;
        return returnCoords;
    }

    // REQUIRES: a correctly declared Cell object
    // EFFECTS: returns the color id (array index of color scheme) for the given cell
    //          returns -1 if the colors of the cell don't match the color scheme (should never happen)
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

    // EFFECT: returns the render gui object
    public static RenderGui getRG() {
        return rg;
    }

    // REQUIRES: x and y coordinates on the grid
    // EFFECT: sets the current selected cell and moves the previous selection to storage
    public static void setSelection(int x, int y) {
        firstSelectionX = selectedX;
        firstSelectionY = selectedY;
        selectedX = x;
        selectedY = y;
        selection++;
    }

    // EFFECT: returns true if two cells were selected
    //  this is used in the mouse control class where it processes the input
    public static boolean isTwoSelected() {
        return selection == 2;
    }

    // REQUIRES: grid cell coordinates
    // EFFECT: returns the string version of it that can be piped into the process input function
    public static String convertCoordToString(int x, int y) {
        String rstr = "";
        char letter1 = (char) (selectedY + 65);
        rstr += letter1;
        rstr += "" + selectedX;

        rstr += " ";

        char letter2 = (char) (firstSelectionY + 65);
        rstr += letter2;
        rstr += "" + firstSelectionX;
        System.out.println(rstr);
        return rstr;
    }

    // getter method
    public static int getScore() {
        return score;
    }

    // getter method
    public static Grid getGrid() {
        return g;
    }

    // EFFECT: resets all the global selection variables
    //  (this is done after an input is entered and the cached selected cells need to be reset)
    public static void resetSelection() {
        selection = 0;
        selectedX = -1;
        selectedY = -1;
        firstSelectionX = -1;
        firstSelectionY = -1;
    }

    // EFFECT: sets the close variable to true and exits the game loop
    public static void close() {
        close = true;
    }

    // EFFECT: sets the current score
    public static void setScore(int s) {
        score = s;
    }
}
