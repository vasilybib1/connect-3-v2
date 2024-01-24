package ui.cli;

import model.Grid;
import ui.Main;

public class RenderCli {

    /* CLASS LEVEL COMMENT
    this class is responsible for rendering the grid to the console and coloring the cells to make it pretty :)
     */

    // pre-set colors
    // due to limit of rgb rendering in the terminal
    // colorScheme in main class cannot have more than 6 colors to use the cli rendering method
    private static final String[] COLOR_SCHEME = {
            "\u001B[30m", // black
            "\u001B[31m", // red
            "\u001B[32m", // green
            "\u001B[34m", // blue
            "\u001B[35m", // purple
            "\u001B[33m", // yellow
            "\u001B[36m"  // cyan
    };

    // REQUIRE: a correctly initiated grid
    //          and that initiated grid only used a color scheme with 6 colors
    // EFFECT: prints out the entire grid in its current state (with colors)
    public static void renderGrid(Grid g) {
        // creates the initial row with numbering
        System.out.print(COLOR_SCHEME[0] + "+");
        for (int i = 0; i < g.getWidth(); i++) {
            if (i == g.getWidth() - 1) {
                System.out.println(COLOR_SCHEME[0] + "-" + i + "-+");
            } else {
                System.out.print(COLOR_SCHEME[0] + "-" + i + "-+");
            }
        }

        // loops through entire grid and prints row by row
        for (int i = 0; i < g.getHeight(); i++) {
            renderRow(g, i);
        }
    }

    // REQUIRE: a correctly initiated grid and a valid row number (0 < grid height)
    // EFFECT: renders the row with a letter to indicate which row it is
    private static void renderRow(Grid g, int rowNum) {
        // gets character and prints the cell according to its color scheme id
        char rowLetter = (char) (rowNum + 65);
        System.out.print(COLOR_SCHEME[0] + rowLetter + " ");
        for (int i = 0; i < g.getWidth(); i++) {
            int color = Main.getColorIdentifier(g.getGrid()[i][rowNum]);

            if (i == g.getWidth() - 1) {
                System.out.println(COLOR_SCHEME[color] + color + COLOR_SCHEME[0] + " |");
            } else {
                System.out.print(COLOR_SCHEME[color] + color + COLOR_SCHEME[0] + " | ");
            }
        }

        // renders the bottom line of a grid (no letter character or cell to render)
        System.out.print(COLOR_SCHEME[0] + "+");
        for (int i = 0; i < g.getWidth(); i++) {
            if (i == g.getWidth() - 1) {
                System.out.println(COLOR_SCHEME[0] + "---+");
            } else {
                System.out.print(COLOR_SCHEME[0] + "---+");
            }
        }
    }


}
