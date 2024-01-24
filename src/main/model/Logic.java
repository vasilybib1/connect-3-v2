package model;

import ui.Main;

public class Logic {
    /* CLASS LEVEL COMMENT
    this entire class is STATIC (ie every function works as a function to modify grid things)
    this means you don't have to construct this class for it to work
    however there is a constructor here just for code coverage

    this class is responsible for controlling the logic inside the game such as:
     - making sure there are no current matches on the board
     - new matches are removed and new cells are generated to replace those that were removed
     - making sure a move that the user does is valid (it creates a match)
     - after a user does a move step through the chain of matches if there are any
     - add up the score from matches
    and so on
     */

    private static final boolean DEBUG = false;
    private boolean created = false;

    // REQUIRES: a correctly initiated grid (at least 3x3)
    // EFFECT: returns true if inside the grid there is currently a match, returns false otherwise
    public static boolean checkGrid(Grid g) {
        return checkRows(g) || checkColumns(g);
    }

    // REQUIRES: a correctly initiated grid (at least 3x3)
    // EFFECT: returns true if any of the rows has a match (3 or more in a row)
    private static boolean checkRows(Grid g) {
        for (int row = 0; row < g.getHeight(); row++) {
            int streak = 0;
            for (int col = 1; col < g.getWidth(); col++) {
                int num = Main.getColorIdentifier(g.getGrid()[col][row]);
                int prevNum = Main.getColorIdentifier(g.getGrid()[col - 1][row]);
                if (num == prevNum) {
                    streak++;
                } else {
                    streak = 0;
                }

                if (streak == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    // REQUIRES: a correctly initiated grid (at least 3x3)
    // EFFECT: returns true if any of the columns has a match (3 or more in a row)
    private static boolean checkColumns(Grid g) {
        for (int col = 0; col < g.getHeight(); col++) {
            int streak = 0;
            for (int row = 1; row < g.getWidth(); row++) {
                int num = Main.getColorIdentifier(g.getGrid()[col][row]);
                int prevNum = Main.getColorIdentifier(g.getGrid()[col][row - 1]);
                if (num == prevNum) {
                    streak++;
                } else {
                    streak = 0;
                }

                if (streak == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    // REQUIRES: a correctly initiated grid (at least 3x3)
    // EFFECT: searches from bottom to top and returns a string with the match information if one is found
    //         return empty string if nothing is found
    //         if there are multiple matches it returns the first one found from the bottom up (highest row number)
    //         example: "123 0" (column 1,2,3 were the same in row 0)
    //                  "0123 2" (column 0,1,2,3 were the same in row 2)
    @SuppressWarnings("methodlength")
    private static String rowMatch(Grid g) {
        String returnString = "";
        int rowMatch = -1;
        for (int row = g.getHeight() - 1; row >= 0; row--) {
            int streak = 0;
            for (int col = 1; col < g.getWidth(); col++) {
                int num = Main.getColorIdentifier(g.getGrid()[col][row]);
                int prevNum = Main.getColorIdentifier(g.getGrid()[col - 1][row]);
                if (num == prevNum) {
                    streak++;
                    rowMatch = row;
                    returnString = returnString + "" + col;
                    if (col == g.getWidth() - 1 && streak >= 2) {
                        return (Integer.valueOf(returnString.substring(0, 1)) - 1) + returnString + " " + rowMatch;
                    }
                } else {
                    if (streak >= 2) {
                        return (Integer.valueOf(returnString.substring(0, 1)) - 1) + returnString + " " + rowMatch;
                    } else {
                        returnString = "";
                        streak = 0;
                        rowMatch = -1;
                    }
                }
            }
            returnString = "";
            rowMatch = -1;
        }
        return "";
    }

    // REQUIRES: a correctly initiated grid (at least 3x3)
    // EFFECT: searches from left to right and returns a string with the match information if one is found
    //         return empty string if nothing is found
    //         if there are multiple matches it returns the first one found from the left (lowest column number)
    //         example: "123 0" (row 1,2,3 were the same in column 0)
    //                  "0123 2" (row 0,1,2,3 were the same in column 2)
    @SuppressWarnings("methodlength")
    private static String columnMatch(Grid g) {
        String returnString = "";
        int columnMatch = -1;
        for (int col = 0; col < g.getWidth(); col++) {
            int streak = 0;
            for (int row = 1; row < g.getHeight(); row++) {
                int num = Main.getColorIdentifier(g.getGrid()[col][row]);
                int prevNum = Main.getColorIdentifier(g.getGrid()[col][row - 1]);
                if (num == prevNum) {
                    streak++;
                    columnMatch = col;
                    returnString = returnString + "" + row;
                    if (row == g.getWidth() - 1 && streak >= 2) {
                        return (Integer.valueOf(returnString.substring(0, 1)) - 1) + returnString + " " + columnMatch;
                    }
                } else {
                    if (streak >= 2) {
                        return (Integer.valueOf(returnString.substring(0, 1)) - 1) + returnString + " " + columnMatch;
                    } else {
                        returnString = "";
                        streak = 0;
                        columnMatch = -1;
                    }
                }
            }
            returnString = "";
            columnMatch = -1;
        }
        return "";
    }

    // REQUIRES: a correctly initiated grid (at least 3x3)
    // MODIFIES: Grid g
    // EFFECT: keeps removing matches until none are found
    public static void removeMatches(Grid g) {
        Event e = new Event("removed all matches on the board");
        EventLog.getInstance().logEvent(e);
        while (checkGrid(g)) {
            String rowMatchCode = rowMatch(g);
            String columnMatchCode = columnMatch(g);
            if (!rowMatchCode.equals("")) {
                removeRowMatch(g, rowMatchCode);
            }
            if (!columnMatchCode.equals("")) {
                removeColumnMatch(g, columnMatchCode);
            }
        }
    }

    // REQUIRE: a correctly initiated grid (at least 3x3) and a non-empty code string from rowMatch()
    // MODIFIES: Grid g
    // EFFECT: removes a match given a code (ex. "123 0") and shifts the grid down to fill in the empty spaces
    //         the new spaces generated from removing a match are filled in at the top with a new random cell
    private static void removeRowMatch(Grid g, String code) {
        if (DEBUG) {
            System.out.println("row match:" + code);
        }
        String toRemove = code.split(" ")[0];
        int row = Integer.valueOf(code.split(" ")[1]);

        for (int i = 0; i < toRemove.length(); i++) {
            int cellToRemove = Integer.valueOf(toRemove.substring(i, i + 1));
            for (int j = 1; j <= row; j++) {
                g.moveCell(row - j, cellToRemove, row - j + 1, cellToRemove);
            }
            g.generateNewCell(0, cellToRemove);
        }
    }

    // REQUIRE: a correctly initiated grid (at least 3x3) and a non-empty code string from columnMatch()
    // MODIFIES: Grid g
    // EFFECT: removes a match given a code (ex. "123 0") and shifts the grid down to fill in the empty spaces
    //         the new spaces generated from removing a match are filled in at the top with a new random cell
    private static void removeColumnMatch(Grid g, String code) {
        if (DEBUG) {
            System.out.println("col match:" + code);
        }
        String toRemove = code.split(" ")[0];
        int col = Integer.valueOf(code.split(" ")[1]);

        for (int i = 0; i < toRemove.length(); i++) {
            int cellToRemove = Integer.valueOf(toRemove.substring(i, i + 1));
            for (int j = 1; j <= cellToRemove; j++) {
                g.moveCell(cellToRemove - j, col, cellToRemove - j + 1, col);
            }
            g.generateNewCell(0, col);
        }
    }

    // REQUIRE: x and y coordinates of the from and to cells
    // EFFECT: returns true if the move is valid (one cell movement from left to right or up or down)
    //         no diagonal allowed
    public static boolean checkMove(int rowFrom, int colFrom, int rowTo, int colTo) {
        if (rowFrom == rowTo) {
            return colFrom == colTo + 1 || colFrom == colTo - 1;
        } else if (colFrom == colTo) {
            return rowFrom == rowTo + 1 || rowFrom == rowTo - 1;
        } else {
            return false;
        }
    }

    // REQUIRE: a grid that has at least one match
    // MODIFIES: grid
    // EFFECT: removes a single match and returns the points gotten from that match
    public static int removeOneMatch(Grid g) {
        Event e = new Event("removed one match");
        EventLog.getInstance().logEvent(e);
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

    // EFFECT: created the logic class (the code runs perfectly fine without but for code coverage i need this
    public Logic() {
        this.created = true;
    }

    public boolean getCreated() {
        return created;
    }


}
