package ui.gui.components;

import ui.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseControl implements MouseListener {
    /* CLASS LEVEL COMMENT
    this is an overridden mouse listener that is connected to the canvas and handles all the mouse control and logic
    */

    // EFFECT: takes the current x and y and creates a selection
    //  if there is a previous selection alr made it will attempt to move the first selected cell to the new one
    //  if its possible it will do all the logic for a move to be processed and then it resets the current selection
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - 10;
        int y = e.getY() - 10;
        Main.getRG().makeSelectedCell(x, y);
        int convertedX = Main.getRG().convertMouseXToCellX(x);
        int convertedY  = Main.getRG().convertMouseYToCellY(y);
        Main.setSelection(convertedX, convertedY);
        if (Main.isTwoSelected()) {
            Main.getRG().hideSelection();
            try {
                Main.processInput(Main.convertCoordToString(convertedX, convertedY));
                Main.resetSelection();
                Main.getRG().renderGrid(Main.getGrid());
            } catch (Exception ee) {
                // this will never fire
            }
        }
    }

    // placeholders bellow

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
